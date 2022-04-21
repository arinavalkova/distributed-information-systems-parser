package ru.nsu.fit.dis.valkova.parser.data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {

    private static final Logger log = LogManager.getLogger(DataBaseConnection.class);

    private Connection connection;

    public Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            return createConnection();
        } else {
            return connection;
        }
    }

    private Connection createConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        properties.load(DataBaseConnection.class.getResourceAsStream("/application.properties"));
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        connection.setAutoCommit(false);
        log.info("New database connection created");
        return connection;
    }
}
