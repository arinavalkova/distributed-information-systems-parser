package ru.nsu.fit.dis.valkova.parser.data.loader;

import java.sql.Connection;

public class BatchNodeLoader extends NodeLoader {

    public BatchNodeLoader(Connection connection) {
        super(connection);
    }

    @Override
    public void load(Object object) {
    }
}
