package ru.nsu.fit.dis.valkova.parser.rest.services.search;


import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;

import java.util.List;

public interface SearchNode {
    List<NodeEntity> search(Double latitude, Double longitude, Double radius);
}