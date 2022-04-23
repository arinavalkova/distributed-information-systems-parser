package ru.nsu.fit.dis.valkova.parser.services.crud;

import generated.Node;

import java.math.BigInteger;

public interface CRUDNode {
    Node create(Node nodeDTO);
    Node get(BigInteger nodeId);
    Node update(BigInteger nodeId, Node node);
    void delete(BigInteger nodeId);
}