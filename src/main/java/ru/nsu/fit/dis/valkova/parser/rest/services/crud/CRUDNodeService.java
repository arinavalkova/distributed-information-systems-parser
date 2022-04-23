package ru.nsu.fit.dis.valkova.parser.rest.services.crud;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;
import ru.nsu.fit.dis.valkova.parser.rest.repository.NodeRepository;

@Service
@RequiredArgsConstructor
public class CRUDNodeService implements CRUDNode {

    private final NodeRepository nodeRepository;

    @Override
    public NodeEntity create(NodeEntity nodeEntity) {
        return nodeRepository.save(nodeEntity);
    }

    @Override
    public NodeEntity get(Long nodeId) {
        if (nodeRepository.findById(nodeId).isPresent()) {
            return nodeRepository.findById(nodeId).get();
        }
        return null;
    }

    @Override
    public NodeEntity update(NodeEntity nodeEntity) {
        return nodeRepository.save(nodeEntity);
    }

    @Override
    public void delete(Long nodeId) {
        nodeRepository.deleteById(nodeId);
    }
}
