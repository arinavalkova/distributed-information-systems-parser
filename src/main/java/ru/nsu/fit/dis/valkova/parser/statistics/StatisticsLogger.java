package ru.nsu.fit.dis.valkova.parser.statistics;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static java.lang.Long.compare;

public class StatisticsLogger {

    private static final Logger logger = LogManager.getLogger(StatisticsLogger.class);

    public void invoke(Map<String,Integer> stat, String text) {
        stat.entrySet().stream()
                .sorted((a, b) -> compare(b.getValue(), a.getValue()))
                .forEach(e -> logger.info(text + e.getKey() + " " + e.getValue()));

    }
}
