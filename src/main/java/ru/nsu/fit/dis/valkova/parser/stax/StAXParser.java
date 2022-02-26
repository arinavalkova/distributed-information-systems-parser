package ru.nsu.fit.dis.valkova.parser.stax;

import ru.nsu.fit.dis.valkova.parser.ParserToStatistics;
import ru.nsu.fit.dis.valkova.parser.input.stream.BZip2CompressorInputStreamGetter;
import ru.nsu.fit.dis.valkova.parser.statistics.StatisticsLogger;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.Long.compare;

public class StAXParser implements ParserToStatistics {

    @Override
    public void parse(String inPath, String outPath) throws XMLStreamException, IOException {
        try (var inputStream = new BZip2CompressorInputStreamGetter().get(inPath, outPath)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
            Map<String, Map<BigInteger, Integer>> stat = new HashMap<>();
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement element = (StartElement) event;
                    Iterator<Attribute> iterator = element.getAttributes();
                    Map<BigInteger, Integer> userStat = null;
                    while (iterator.hasNext()) {
                        Attribute attribute = iterator.next();
                        QName name = attribute.getName();
                        String value = attribute.getValue();
                        if (name.toString().equals("user")) {
                            userStat = stat.computeIfAbsent(value, k -> new HashMap<>());
                        } else if (name.toString().equals("changeset")) {
                            userStat.merge(new BigInteger(value), 1, Integer::sum);
                        }
                    }
                }
            }
            new StatisticsLogger().invoke(stat);
        }
    }
}
