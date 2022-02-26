package ru.nsu.fit.dis.valkova.parser.statistics;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.Map;

import static java.lang.Long.compare;

public class StatisticsLogger {

    private static final Logger logger = LogManager.getLogger(StatisticsLogger.class);

    public void invoke(Map<String, Map<BigInteger, Integer>> stat) {
        stat.entrySet().stream()
                .sorted((a, b) -> compare(b.getValue().size(), a.getValue().size()))
                .forEach(e -> logger.info(e.getKey() + " " + e.getValue().size() + " " +
                        e.getValue().values().stream().mapToInt(Integer::intValue).sum()));
    }
}
