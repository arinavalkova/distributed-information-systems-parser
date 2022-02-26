package ru.nsu.fit.dis.valkova.parser.jaxb;

import org.openstreetmap.osm._0.Node;
import ru.nsu.fit.dis.valkova.parser.ParserToStatistics;
import ru.nsu.fit.dis.valkova.parser.input.stream.BZip2CompressorInputStreamGetter;
import ru.nsu.fit.dis.valkova.parser.statistics.StatisticsLogger;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class JaxbParser implements ParserToStatistics {

    @Override
    public void parse(String inPath, String outPath) throws IOException, XMLStreamException, JAXBException {
        try (var inputStream = new BZip2CompressorInputStreamGetter().get(inPath, outPath)) {
            Map<String, Map<BigInteger, Integer>> stat = new HashMap<>();
            var nodeReader = new PartialUnmarshaller<>(inputStream, Node.class);
            for (Node node : nodeReader) {
                var userStat = stat.computeIfAbsent(node.getUser(), k -> new HashMap<>());
                userStat.merge(node.getChangeset(), 1, Integer::sum);
            }
            new StatisticsLogger().invoke(stat);
        }
    }
}