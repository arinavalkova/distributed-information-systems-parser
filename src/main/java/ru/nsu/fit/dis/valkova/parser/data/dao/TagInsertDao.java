package ru.nsu.fit.dis.valkova.parser.data.dao;

import org.openstreetmap.osm._0.Tag;

import java.sql.Connection;
import java.util.List;

public class TagInsertDao extends InsertDao<Tag> {

    public TagInsertDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Tag> getList(int id) {
        return null;
    }

    @Override
    public void statementInsert(Tag object) {

    }

    @Override
    public void preparedInsert(Tag object) {

    }

    @Override
    public void batchInsert(List<Tag> list) {

    }
}
