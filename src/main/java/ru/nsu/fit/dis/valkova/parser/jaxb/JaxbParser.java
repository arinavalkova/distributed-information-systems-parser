package ru.nsu.fit.dis.valkova.parser.jaxb;

import ru.nsu.fit.dis.valkova.parser.ParserToStatistics;
import ru.nsu.fit.dis.valkova.parser.input.stream.BZip2CompressorInputStreamGetter;
import ru.nsu.fit.dis.valkova.parser.jaxb.attributes.Node;
import ru.nsu.fit.dis.valkova.parser.jaxb.attributes.Tag;
import ru.nsu.fit.dis.valkova.parser.statistics.StatisticsLogger;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JaxbParser implements ParserToStatistics {

    @Override
    public void parse(String inPath, String outPath) throws IOException, XMLStreamException, JAXBException {
        try (var inputStream = new BZip2CompressorInputStreamGetter().get(inPath, outPath)) {
            Map<String, Integer> stat1 = new HashMap<>();
            Map<String, Integer> stat2 = new HashMap<>();

            var nodeReader1 = new PartialUnmarshaller<>(inputStream, Node.class);
            for (Node node : nodeReader1) {
                List<Tag> tags = node.getTag();
                for (Tag tag : tags) {
                    stat1.merge(tag.getK(), 1, Integer::sum);
                }
                stat1.merge(node.getUser(), 1, Integer::sum);
            }

            StatisticsLogger statisticsLogger = new StatisticsLogger();
            statisticsLogger.invoke(stat1, "1. ");
            statisticsLogger.invoke(stat2, "2. ");
        }
    }
}