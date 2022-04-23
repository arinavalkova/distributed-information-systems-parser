package ru.nsu.fit.dis.valkova.parser.data.dao;

import ru.nsu.fit.dis.valkova.parser.data.entity.TagEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TagInsertDao extends InsertDao<TagEntity> {

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
    public void statementInsert(TagEntity tagEntity) throws SQLException {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(getSqlStatement(tagEntity));
        }
        getConnection().commit();
    }

    @Override
    public void preparedInsert(TagEntity tagEntity) throws SQLException {
        addToPrepared(preparedStatement, tagEntity);

        preparedStatement.execute();
        getConnection().commit();
    }

    @Override
    public void batchInsert(TagEntity tagEntity) throws SQLException {
        addToPrepared(batch, tagEntity);
        batch.addBatch();
    }

    @Override
    public void finalizeBatch() throws SQLException {
        batch.executeBatch();
    }

    @Override
    public String getSqlStatement(TagEntity tagEntity) {
        return "insert into tags (" +
                "node_id, key, value" +
                ") values (" +
                tagEntity.getId() + ", '" +
                tagEntity.getK().replace('\'', '\\') + "', '" +
                tagEntity.getV().replace('\'', '\\') + "')";
    }

    public void addToPrepared(PreparedStatement preparedStatement, TagEntity tagEntity) throws SQLException {
        preparedStatement.setInt(1, tagEntity.getId().intValue());
        preparedStatement.setString(2, tagEntity.getK());
        preparedStatement.setString(3, tagEntity.getV());
    }
}
