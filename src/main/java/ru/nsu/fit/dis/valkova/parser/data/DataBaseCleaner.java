package ru.nsu.fit.dis.valkova.parser.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseCleaner {

    private static final Logger log = LogManager.getLogger(DataBaseCleaner.class);

    public static void cleanTables(Connection connection) {
        try {
            connection.prepareStatement(
                    "truncate table tags cascade;" +
                            "truncate table nodes cascade;").execute();
            connection.commit();
            log.info("data base cleaned");
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }
}
