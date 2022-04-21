package ru.nsu.fit.dis.valkova.parser.data.dao;

import org.openstreetmap.osm._0.Node;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

public class NodeInsertDao extends InsertDao<Node> {

    public NodeInsertDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Node> getList(int id) {
        return null;
    }

    @Override
    public void statementInsert(Node object) throws SQLException {
        Statement statement = getConnection().createStatement();
        String sql =
                "insert into nodes (" +
                        "id, latitude, longitude, username, uid, version, changeset, timestamp" +
                        ") values (" +
                        object.getId() + ", " +
                        object.getLat() + ", " +
                        object.getLon() + ", '" +
                        object.getUser().replace('\'', '\\') + "', " +
                        object.getUid() + ", " +
                        object.getVersion() + ", " +
                        object.getChangeset() + ", " +
                        "timestamp '" +
                        Timestamp.from(object.getTimestamp().toGregorianCalendar().toInstant()) + "')";
        statement.execute(sql);
        getConnection().commit();
    }

    @Override
    public void preparedInsert(Node object) {

    }

    @Override
    public void batchInsert(List<Node> list) {

    }
}
