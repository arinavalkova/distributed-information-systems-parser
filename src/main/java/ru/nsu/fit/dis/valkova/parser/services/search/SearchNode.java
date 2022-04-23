package ru.nsu.fit.dis.valkova.parser.services.search;

import generated.Node;

import java.util.List;

public interface SearchNode {
    List<Node> search(Double latitude, Double longitude, Double radius);
}