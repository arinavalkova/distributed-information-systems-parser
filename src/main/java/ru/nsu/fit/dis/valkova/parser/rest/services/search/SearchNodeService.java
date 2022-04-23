package ru.nsu.fit.dis.valkova.parser.rest.services.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;
import ru.nsu.fit.dis.valkova.parser.rest.repository.NodeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchNodeService implements SearchNode {
    private final NodeRepository nodeRepository;

    @Override
    public List<NodeEntity> search(Double latitude, Double longitude, Double radius) {
        return nodeRepository.getNearNodes(latitude, longitude, radius);
    }
}