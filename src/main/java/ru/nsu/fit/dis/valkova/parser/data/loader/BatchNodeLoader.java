package ru.nsu.fit.dis.valkova.parser.data.loader;

import ru.nsu.fit.dis.valkova.parser.dao.Node;
import ru.nsu.fit.dis.valkova.parser.dao.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.dis.valkova.parser.data.dao.InsertDao;
import ru.nsu.fit.dis.valkova.parser.data.dao.NodeInsertDao;
import ru.nsu.fit.dis.valkova.parser.data.dao.TagInsertDao;

import java.sql.Connection;
import java.sql.SQLException;

public class BatchNodeLoader extends NodeLoader {

    private static final String BATCH = "BATCH";
    private static final Logger logger = LogManager.getLogger(BatchNodeLoader.class);

    private int nodeBatchCount = 0;
    private int tagBatchCount = 0;

    public BatchNodeLoader(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void load(Object object) throws SQLException {
        Node node = (Node) object;
        NodeInsertDao nodeInsertDao = getNodeInsertDao();
        TagInsertDao tagInsertDao = getTagInsertDao();

        nodeInsertDao.batchInsert(node);
        nodeBatchCount = getBatchCount(nodeBatchCount, nodeInsertDao);
        for (Tag tag : node.getTag()) {
            tagInsertDao.batchInsert(new Tag(node.getId(), tag.getK(), tag.getV()));
            tagBatchCount = getBatchCount(tagBatchCount, tagInsertDao);
        }
    }

    public int getBatchCount(int batchCount, InsertDao insertDao) throws SQLException {
        batchCount++;
        if (batchCount > 5000) {
            insertDao.finalizeBatch();
            logger.info(BATCH);
            getConnection().commit();
            return 0;
        }
        return batchCount;
    }

    @Override
    public void finalizeLoad() throws SQLException {
        if (nodeBatchCount > 0) {
            getNodeInsertDao().finalizeBatch();
            logger.info(BATCH);
            getConnection().commit();
        }
        if (tagBatchCount > 0) {
            getTagInsertDao().finalizeBatch();
            logger.info(BATCH);
            getConnection().commit();
        }
    }
}
