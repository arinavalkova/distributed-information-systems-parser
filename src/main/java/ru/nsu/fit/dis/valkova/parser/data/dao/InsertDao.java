package ru.nsu.fit.dis.valkova.parser.data.dao;

import java.sql.*;

public abstract class InsertDao<T> {

    private final Connection connection;

    protected InsertDao(Connection connection) {
        this.connection = connection;
    }

    abstract void statementInsert(T object) throws SQLException;

    public Connection getConnection() {
        return connection;
    }

    public abstract String getSqlStatement(T object);

    public abstract void preparedInsert(T object) throws SQLException;

    public abstract void batchInsert(T object) throws SQLException;

    public abstract void finalizeBatch() throws SQLException;
}
