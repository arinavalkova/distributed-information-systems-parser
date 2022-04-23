package ru.nsu.fit.dis.valkova.parser.services.search;

import generated.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.dis.valkova.parser.repository.NodeRepository;

import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class SearchNodeService implements SearchNode {
//    private final NodeRepository nodeRepository;
//    private final NodeMapper nodeMapper;
//
//    @Override
//    public List<Node> search(Double latitude, Double longitude, Double radius) {
//        return nodeMapper.toDtos(nodeRepository.getNearNodes(latitude, longitude, radius));
//    }
//}