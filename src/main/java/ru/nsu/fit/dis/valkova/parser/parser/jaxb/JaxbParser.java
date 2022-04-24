package ru.nsu.fit.dis.valkova.parser.parser.jaxb;

import generated.Node;
import generated.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.dis.valkova.parser.ParserToStatistics;
import ru.nsu.fit.dis.valkova.parser.data.DataBaseConnection;
import ru.nsu.fit.dis.valkova.parser.data.initialization.DataBaseInitializationFromFile;
import ru.nsu.fit.dis.valkova.parser.data.loader.*;
import ru.nsu.fit.dis.valkova.parser.input.stream.BZip2CompressorInputStreamGetter;
import ru.nsu.fit.dis.valkova.parser.statistics.StatisticsLogger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.xml.stream.XMLStreamConstants.START_ELEMENT;

public class JaxbParser implements ParserToStatistics {

    private final LoaderMode loaderMode;
    private static final String DDL_DATABASE_SQL = "src\\main\\resources\\ddl\\database.sql";
    private static final Logger logger = LogManager.getLogger(JaxbParser.class);

    private int nodeCount = 0;
    private int insertsCount = 0;
    private int insertsCountForBatch = 0;

    public JaxbParser(LoaderMode loaderMode) {
        this.loaderMode = loaderMode;
    }

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

            Map<LoaderMode, Loader> loaderMap = Map.of(
                    LoaderMode.STATEMENT, new StatementNodeLoader(connection),
                    LoaderMode.PREPARED, new PreparedNodeLoader(connection),
                    LoaderMode.BATCH, new BatchNodeLoader(connection)
            );

            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(inputStream);
            JAXBContext jaxbContext = JAXBContext.newInstance(Node.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            NodeLoader loader = (NodeLoader) loaderMap.get(loaderMode);

            double time = 0;
            double startTime;

            while (reader.hasNext()) {
                int event = reader.next();
                if (event == START_ELEMENT && reader.getLocalName().equals("node")) {
//                    if (nodeCount++ > 100000) break;
                    Node node = (Node) unmarshaller.unmarshal(reader);
                    List<Tag> tags = node.getTag();
                    insertsCount += tags.size() + 1;
                    for (Tag tag : tags) {
                        stat2.merge(tag.getK(), 1, Integer::sum);
                    }
                    stat1.merge(node.getUser(), 1, Integer::sum);
                    startTime = System.currentTimeMillis();
                    loader.load(node);
                    var curTime = System.currentTimeMillis() - startTime;
                    time += curTime;
                }
            }

            startTime = System.currentTimeMillis();
            loader.finalizeLoad();
            var curTime = System.currentTimeMillis() - startTime;
            time += curTime;

            double speed = 0;
            if (time != 0) {
                speed = (double) insertsCount * 1000 / time;
            }
            logger.info(loaderMode.toString() + " " + speed + " inserts/sec");
//            StatisticsLogger statisticsLogger = new StatisticsLogger();
//            statisticsLogger.invoke(stat1, "1. ");
//            statisticsLogger.invoke(stat2, "2. ");
        }
    }
}