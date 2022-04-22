package ru.nsu.fit.dis.valkova.parser.data.loader;

import generated.Node;
import generated.Tag;
import ru.nsu.fit.dis.valkova.parser.data.entity.TagEntity;

import java.sql.Connection;
import java.sql.SQLException;

public class BatchNodeLoader extends NodeLoader {

    public BatchNodeLoader(Connection connection) throws SQLException {
        super(connection);
    }

    @Override
    public void load(Object object) throws SQLException {
        Node node = (Node) object;
        getNodeInsertDao().batchInsert(node);
        for (Tag tag : node.getTag()) {
            getTagInsertDao().batchInsert(new TagEntity(node.getId(), tag.getK(), tag.getV()));
        }
    }
}
