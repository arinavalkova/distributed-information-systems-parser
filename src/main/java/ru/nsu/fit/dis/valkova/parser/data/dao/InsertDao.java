package ru.nsu.fit.dis.valkova.parser.data.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class InsertDao<T> {

    private final Connection connection;

    protected InsertDao(Connection connection) {
        this.connection = connection;
    }

    abstract List<T> getList(int id);

    abstract void statementInsert(T object) throws SQLException;

    abstract void preparedInsert(T object);

    abstract void batchInsert(List<T> list);

    public Connection getConnection() {
        return connection;
    }
}
