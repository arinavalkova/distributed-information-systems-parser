package ru.nsu.fit.dis.valkova.parser.data.loader;

import org.openstreetmap.osm._0.Node;
import org.openstreetmap.osm._0.Tag;

import java.sql.Connection;
import java.sql.SQLException;

public class StatementNodeLoader extends NodeLoader {

    public StatementNodeLoader(Connection connection) {
        super(connection);
    }

    @Override
    public void load(Object object) throws SQLException {
        Node node = (Node) object;
        getNodeInsertDao().statementInsert(node);
        for (Tag tag : node.getTag()) {
            getTagInsertDao().statementInsert(tag);
        }
    }
}
