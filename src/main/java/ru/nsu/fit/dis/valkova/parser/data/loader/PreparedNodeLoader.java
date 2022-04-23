package ru.nsu.fit.dis.valkova.parser.data.loader;

import ru.nsu.fit.dis.valkova.parser.dao.Node;
import ru.nsu.fit.dis.valkova.parser.dao.Tag;

import java.sql.Connection;
import java.sql.SQLException;

public class PreparedNodeLoader extends NodeLoader {

    public PreparedNodeLoader(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void load(Object object) throws SQLException {
        Node node = (Node) object;
        getNodeInsertDao().preparedInsert(node);
        for (Tag tag : node.getTag()) {
            getTagInsertDao().preparedInsert(new Tag(node.getId(), tag.getK(), tag.getV()));
        }
    }

    @Override
    public void finalizeLoad() throws SQLException {

    }
}
