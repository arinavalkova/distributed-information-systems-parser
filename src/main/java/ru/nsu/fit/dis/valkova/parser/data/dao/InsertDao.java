package ru.nsu.fit.dis.valkova.parser.data.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class InsertDao<T> {

    private final Connection connection;
    private final Statement batch;

    protected InsertDao(Connection connection, Statement batch) {
        this.connection = connection;
        this.batch = batch;
    }

    abstract void statementInsert(T object) throws SQLException;

    public Connection getConnection() {
        return connection;
    }

    public abstract String getSqlStatement(T object);

    public abstract void preparedInsert(T object) throws SQLException;

    public void batchInsert(T object) throws SQLException {
        batch.addBatch(getSqlStatement(object));
    }
}
