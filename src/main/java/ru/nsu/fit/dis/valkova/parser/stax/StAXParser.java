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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class StAXParser implements ParserToStatistics {

    @Override
    public void parse(String inPath, String outPath) throws XMLStreamException, IOException {
        try (var inputStream = new BZip2CompressorInputStreamGetter().get(inPath, outPath)) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
            Map<String, Integer> stat1 = new HashMap<>();
            Map<String, Integer> stat2 = new HashMap<>();

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement element = (StartElement) event;
                    if (element.getName().toString().equals("node")) {
                        Iterator<Attribute> iterator = element.getAttributes();
                        while (iterator.hasNext()) {
                            Attribute attribute = iterator.next();
                            QName name = attribute.getName();
                            String value = attribute.getValue();
                            if (name.toString().equals("user")) {
                                stat1.merge(value, 1, Integer::sum);
                            }
                        }
                    } else if (element.getName().toString().equals("tag")) {
                        Iterator<Attribute> iterator = element.getAttributes();
                        while (iterator.hasNext()) {
                            Attribute attribute = iterator.next();
                            QName name = attribute.getName();
                            String value = attribute.getValue();
                            if (name.toString().equals("k")) {
                                stat2.merge(value, 1, Integer::sum);
                            }
                        }
                    }
                }
            }
            StatisticsLogger statisticsLogger = new StatisticsLogger();
            statisticsLogger.invoke(stat1, "1. ");
            statisticsLogger.invoke(stat2, "2. ");
        }
    }
}
