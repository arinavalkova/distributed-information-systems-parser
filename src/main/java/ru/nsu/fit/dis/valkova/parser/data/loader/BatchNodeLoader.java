package ru.nsu.fit.dis.valkova.parser.data.loader;

import generated.Node;
import generated.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.fit.dis.valkova.parser.data.dao.InsertDao;
import ru.nsu.fit.dis.valkova.parser.data.dao.NodeInsertDao;
import ru.nsu.fit.dis.valkova.parser.data.dao.TagInsertDao;
import ru.nsu.fit.dis.valkova.parser.data.entity.TagEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class BatchNodeLoader extends NodeLoader {

    private static final String BATCH = "BATCH";
    private static final Logger logger = LogManager.getLogger(BatchNodeLoader.class);

    private int nodeBatchCount = 0;

    public BatchNodeLoader(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void load(Object object) throws SQLException {
        Node node = (Node) object;
        NodeInsertDao nodeInsertDao = getNodeInsertDao();
        TagInsertDao tagInsertDao = getTagInsertDao();

        nodeInsertDao.batchInsert(node);
        nodeBatchCount = getBatchCount(nodeBatchCount, nodeInsertDao, tagInsertDao);
        for (Tag tag : node.getTag()) {
            tagInsertDao.batchInsert(new TagEntity(node.getId(), tag.getK(), tag.getV()));
        }
    }

    public int getBatchCount(int batchCount,
                             NodeInsertDao nodeInsertDao,
                             TagInsertDao tagInsertDao) throws SQLException {
        batchCount++;
        if (batchCount > 5000) {
            nodeInsertDao.finalizeBatch();
            getConnection().commit();
            tagInsertDao.finalizeBatch();
            logger.info(BATCH);
            getConnection().commit();
            return 0;
        }
        return batchCount;
    }

    @Override
    public void finalizeLoad() throws SQLException {
        getNodeInsertDao().finalizeBatch();
        logger.info(BATCH);
        getConnection().commit();
        getTagInsertDao().finalizeBatch();
        logger.info(BATCH);
        getConnection().commit();
    }
}
