package ru.nsu.fit.dis.valkova.parser.data.dao;

import generated.Tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TagInsertDao extends InsertDao<Tag> {

    private static final String preparedInsertLine =
            "insert into tags (" +
                    "node_id, key, value" +
                    ") values (?, ?, ?)";

    private final PreparedStatement preparedStatement;
    private final PreparedStatement batch;

    public TagInsertDao(Connection connection) throws SQLException {
        super(connection);
        preparedStatement = connection.prepareStatement(preparedInsertLine);
        batch = connection.prepareStatement(preparedInsertLine);
    }

    @Override
    public void statementInsert(Tag tag) throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(getSqlStatement(tag));
        }
        getConnection().commit();
    }

    @Override
    public void preparedInsert(Tag tag) throws SQLException {
        addToPrepared(preparedStatement, tag);

        preparedStatement.execute();
        getConnection().commit();
    }

    @Override
    public void batchInsert(Tag tag) throws SQLException {
        addToPrepared(batch, tag);
        batch.addBatch();
    }

    @Override
    public void finalizeBatch() throws SQLException {
        batch.executeBatch();
    }

    @Override
    public String getSqlStatement(Tag tag) {
        return "insert into tags (" +
                "node_id, key, value" +
                ") values (" +
                tag.get() + ", '" +
                tag.getK().replace('\'', '\\') + "', '" +
                tag.getV().replace('\'', '\\') + "')";
    }

    public void addToPrepared(PreparedStatement preparedStatement, TagDTO tag) throws SQLException {
        preparedStatement.setLong(1, tag.getId());
        preparedStatement.setString(2, tag.getKey());
        preparedStatement.setString(3, tag.getValue());
    }
}
