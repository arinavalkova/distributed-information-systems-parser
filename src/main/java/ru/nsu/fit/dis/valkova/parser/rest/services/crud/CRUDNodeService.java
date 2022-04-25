package ru.nsu.fit.dis.valkova.parser.rest.services.crud;


import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;
import ru.nsu.fit.dis.valkova.parser.rest.repository.NodeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CRUDNodeService implements CRUDNode {

    private final NodeRepository nodeRepository;

    @Override
    public NodeEntity create(NodeEntity nodeEntity) {
        return nodeRepository.save(nodeEntity);
    }

    @Override
    public NodeEntity get(Long nodeId) {
        return nodeRepository.findById(nodeId).orElse(null);
    }

    @Override
    public NodeEntity update(NodeEntity nodeEntity) {
        System.out.println(nodeEntity);
        delete(nodeEntity.getId());
        return nodeRepository.save(nodeEntity);
    }

    @Override
    public void delete(Long nodeId) {
        nodeRepository.deleteById(nodeId);
    }
}
