package ru.nsu.fit.dis.valkova.parser.data.dao;

import generated.Node;

import java.sql.*;

public class NodeInsertDao extends InsertDao<Node> {

    private static final String preparedInsertLine =
            "insert into nodes (" +
                    "id, latitude, longitude, username, uid, version, changeset, timestamp" +
                    ") values (?, ?, ?, ?, ?, ?, ?, ?)";

    private final PreparedStatement preparedStatement;
    private final PreparedStatement batch;

    public NodeInsertDao(Connection connection) throws SQLException {
        super(connection);
        preparedStatement = connection.prepareStatement(preparedInsertLine);
        batch = connection.prepareStatement(preparedInsertLine);
    }

    @Override
    public void statementInsert(Node node) throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(getSqlStatement(node));
        }
        getConnection().commit();
    }

    @Override
    public void preparedInsert(Node node) throws SQLException {
       addToPrepared(preparedStatement, node);

        preparedStatement.execute();
        getConnection().commit();
    }

    @Override
    public void batchInsert(Node node) throws SQLException {
        addToPrepared(batch, node);
        batch.addBatch();
    }

    @Override
    public void finalizeBatch() throws SQLException {
        batch.executeBatch();
    }

    @Override
    public String getSqlStatement(Node node) {
        return "insert into nodes (" +
                "id, latitude, longitude, username, uid, version, changeset, timestamp" +
                ") values (" +
                node.getId() + ", " +
                node.getLat() + ", " +
                node.getLon() + ", '" +
                node.getUser().replace('\'', '\\') + "', " +
                node.getUid() + ", " +
                node.getVersion() + ", " +
                node.getChangeset() + ", " +
                "timestamp '" +
                Timestamp.from(node.getTimestamp().toGregorianCalendar().toInstant()) + "')";
    }

    public void addToPrepared(PreparedStatement preparedStatement, Node node) throws SQLException {
        preparedStatement.setLong(1, node.getId().longValue());
        preparedStatement.setDouble(2, node.getLat());
        preparedStatement.setDouble(3, node.getLon());
        preparedStatement.setString(4, node.getUser());
        preparedStatement.setLong(5, node.getUid().longValue());
        preparedStatement.setLong(6, node.getVersion().longValue());
        preparedStatement.setLong(7, node.getChangeset().longValue());
        preparedStatement.setTimestamp(8, Timestamp.from(node.getTimestamp().toGregorianCalendar().toInstant()));
    }
}
