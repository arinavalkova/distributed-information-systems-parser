package ru.nsu.fit.dis.valkova.parser.data.dao;

import java.util.List;

public interface InsertDao<T> {

    List<T> getList(int id);

    void statementInsert(T object);

    void preparedInsert(T object);

    void batchInsert(List<T> list);
}
