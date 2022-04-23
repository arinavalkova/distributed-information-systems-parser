package ru.nsu.fit.dis.valkova.parser.rest.services.crud;

import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;

public interface CRUDNode {
    NodeEntity create(NodeEntity nodeEntity);
    NodeEntity get(Long nodeId);
    NodeEntity update(NodeEntity nodeEntity);
    void delete(Long nodeId);
}