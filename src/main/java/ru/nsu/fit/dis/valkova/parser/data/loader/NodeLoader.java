package ru.nsu.fit.dis.valkova.parser.data.loader;

import ru.nsu.fit.dis.valkova.parser.data.dao.NodeInsertDao;
import ru.nsu.fit.dis.valkova.parser.data.dao.TagInsertDao;

import java.sql.Connection;

public abstract class NodeLoader implements Loader {

    private final NodeInsertDao nodeInsertDao;
    private final TagInsertDao tagInsertDao;

    protected NodeLoader(Connection connection) {
        this.nodeInsertDao = new NodeInsertDao(connection);
        this.tagInsertDao = new TagInsertDao(connection);
    }

    public NodeInsertDao getNodeInsertDao() {
        return nodeInsertDao;
    }

    public TagInsertDao getTagInsertDao() {
        return tagInsertDao;
    }
}
