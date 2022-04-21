package ru.nsu.fit.dis.valkova.parser.data.initialization;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseInitializationFromFile implements DataBaseInitialization {

    private static final Logger logger = LogManager.getLogger(DataBaseInitializationFromFile.class);

    public void initializeFromFile(String fileName, Connection connection) throws SQLException, IOException {
        File file = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder stringBuilder = new StringBuilder("");
            while (reader.ready()) {
                stringBuilder.append(reader.readLine());
            }
            try (Statement statement = connection.createStatement()) {
                statement.execute(new String(stringBuilder));
                connection.commit();
                logger.info("database initialized");
            }
        }
    }
}
