package ru.nsu.fit.dis.valkova.parser.data.loader;

import java.sql.SQLException;

public interface Loader {
    void load(Object object) throws SQLException;
    void finalizeLoad() throws SQLException;
}
