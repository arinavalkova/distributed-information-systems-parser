package ru.nsu.fit.dis.valkova.parser.services.crud;

import ru.nsu.fit.dis.valkova.parser.dao.Node;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigInteger;

public interface CRUDNode {
    Node create(Node nodeDTO) throws DatatypeConfigurationException;
    Node get(BigInteger nodeId) throws DatatypeConfigurationException;
    Node update(BigInteger nodeId, Node node) throws DatatypeConfigurationException;
    boolean delete(BigInteger nodeId);
}