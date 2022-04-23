package ru.nsu.fit.dis.valkova.parser.services.search;

import ru.nsu.fit.dis.valkova.parser.dao.Node;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

public interface SearchNode {
    List<Node> search(Double latitude, Double longitude, Double radius) throws DatatypeConfigurationException;
}