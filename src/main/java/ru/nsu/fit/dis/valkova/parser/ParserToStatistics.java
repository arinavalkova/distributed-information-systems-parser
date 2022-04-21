package ru.nsu.fit.dis.valkova.parser;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.sql.SQLException;

public interface ParserToStatistics {
    void parse(String inPath, String outPath) throws IOException, JAXBException, XMLStreamException, SQLException;
}
