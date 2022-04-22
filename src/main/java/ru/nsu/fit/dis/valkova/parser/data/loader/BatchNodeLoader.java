package ru.nsu.fit.dis.valkova.parser.data.loader;

import generated.Node;
import generated.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.dis.valkova.parser.data.entity.TagEntity;
import ru.nsu.fit.dis.valkova.parser.statistics.StatisticsLogger;

import java.sql.Connection;
import java.sql.SQLException;

public class BatchNodeLoader extends NodeLoader {

    private static final Logger logger = LogManager.getLogger(BatchNodeLoader.class);

    public BatchNodeLoader(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void load(Object object) throws SQLException {
        Node node = (Node) object;
        getNodeInsertDao().batchInsert(node);
        for (Tag tag : node.getTag()) {
            getTagInsertDao().batchInsert(new TagEntity(node.getId(), tag.getK(), tag.getV()));
        }
    }

    @Override
    public void finalizeLoad() throws SQLException {
        getBatch().executeBatch();
        logger.info("BATCH");
        getConnection().commit();
    }
}
