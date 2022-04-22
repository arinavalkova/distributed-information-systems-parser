package ru.nsu.fit.dis.valkova.parser.data.loader;

import ru.nsu.fit.dis.valkova.parser.data.dao.NodeInsertDao;
import ru.nsu.fit.dis.valkova.parser.data.dao.TagInsertDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class NodeLoader implements Loader {

    private final NodeInsertDao nodeInsertDao;
    private final TagInsertDao tagInsertDao;
    private final Statement batch;
    private final Connection connection;

    protected NodeLoader(Connection connection) throws SQLException {
        this.connection = connection;
        batch = connection.createStatement();
        this.nodeInsertDao = new NodeInsertDao(connection, batch);
        this.tagInsertDao = new TagInsertDao(connection, batch);
    }

    public NodeInsertDao getNodeInsertDao() {
        return nodeInsertDao;
    }

    public Statement getBatch() {
        return batch;
    }

    public TagInsertDao getTagInsertDao() {
        return tagInsertDao;
    }

    public Connection getConnection() {
        return connection;
    }
}
