package ru.nsu.fit.dis.valkova.parser.parser.jaxb;

import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;
import ru.nsu.fit.dis.valkova.parser.ParseMode;
import ru.nsu.fit.dis.valkova.parser.ParserToStatistics;
import ru.nsu.fit.dis.valkova.parser.data.DataBaseConnection;
import ru.nsu.fit.dis.valkova.parser.data.initialization.DataBaseInitializationFromFile;
import ru.nsu.fit.dis.valkova.parser.data.loader.*;
import ru.nsu.fit.dis.valkova.parser.input.stream.BZip2CompressorInputStreamGetter;
import ru.nsu.fit.dis.valkova.parser.parser.stax.StAXParser;
import ru.nsu.fit.dis.valkova.parser.statistics.StatisticsLogger;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JaxbParser implements ParserToStatistics {

    private static final LoaderMode loaderMode = LoaderMode.STATEMENT;
    private static final String DDL_DATABASE_SQL = "src\\main\\resources\\ddl\\database.sql";

    private Map<LoaderMode, Loader> loaderMap;

    @Override
    public void parse(String inPath, String outPath)
            throws IOException, XMLStreamException, JAXBException, SQLException {
        try (var inputStream =
                     new BZip2CompressorInputStreamGetter().get(inPath, outPath)) {
            Map<String, Integer> stat1 = new HashMap<>();
            Map<String, Integer> stat2 = new HashMap<>();
            Connection connection = new DataBaseConnection().getConnection();
            new DataBaseInitializationFromFile()
                    .initializeFromFile(DDL_DATABASE_SQL, connection);

            loaderMap = Map.of(
                    LoaderMode.STATEMENT, new StatementNodeLoader(connection),
                    LoaderMode.PREPARED, new PreparedNodeLoader(connection),
                    LoaderMode.BATCH, new BatchNodeLoader(connection)
            );

            var nodeReader1 = new PartialUnmarshaller<>(inputStream, Node.class);
            for (Node node : nodeReader1) {
                List<Tag> tags = node.getTag();
                for (Tag tag : tags) {
                    stat1.merge(tag.getK(), 1, Integer::sum);
                }
                stat1.merge(node.getUser(), 1, Integer::sum);
                loaderMap.get(loaderMode).load(node);
            }

            StatisticsLogger statisticsLogger = new StatisticsLogger();
            statisticsLogger.invoke(stat1, "1. ");
            statisticsLogger.invoke(stat2, "2. ");
        }
    }
}