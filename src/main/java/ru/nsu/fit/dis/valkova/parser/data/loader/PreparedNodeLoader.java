package ru.nsu.fit.dis.valkova.parser.data.loader;

import java.sql.Connection;

public class PreparedNodeLoader extends NodeLoader {

    public PreparedNodeLoader(Connection connection) {
        super(connection);
    }

    @Override
    public void load(Object object) {

    }
}
