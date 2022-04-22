package ru.nsu.fit.dis.valkova.parser.data.dao;

import generated.Node;

import java.sql.*;

public class NodeInsertDao extends InsertDao<Node> {

    private static final String preparedInsertLine =
            "insert into nodes (" +
                    "id, latitude, longitude, username, uid, version, changeset, timestamp" +
                    ") values (?, ?, ?, ?, ?, ?, ?, ?)";

    private final PreparedStatement preparedStatement;

    public NodeInsertDao(Connection connection, Statement batch) throws SQLException {
        super(connection, batch);
        preparedStatement = connection.prepareStatement(preparedInsertLine);
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
        preparedStatement.setInt(1, node.getId().intValue());
        preparedStatement.setDouble(2, node.getLat());
        preparedStatement.setDouble(3, node.getLon());
        preparedStatement.setString(4, node.getUser());
        preparedStatement.setInt(5, node.getUid().intValue());
        preparedStatement.setInt(6, node.getVersion().intValue());
        preparedStatement.setInt(7, node.getChangeset().intValue());
        preparedStatement.setTimestamp(8, Timestamp.from(node.getTimestamp().toGregorianCalendar().toInstant()));

        preparedStatement.execute();
        getConnection().commit();
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
}
