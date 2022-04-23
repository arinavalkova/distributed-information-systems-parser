package ru.nsu.fit.dis.valkova.parser.services.crud;

import generated.Node;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.nsu.fit.dis.valkova.parser.entities.NodeEntity;
import ru.nsu.fit.dis.valkova.parser.repository.NodeRepository;
import ru.nsu.fit.dis.valkova.parser.repository.TagRepository;

import java.math.BigInteger;

//@Service
//@RequiredArgsConstructor
//public class CRUDNodeService implements CRUDNode {
//
//    private final NodeRepository nodeRepository;
//    private final TagRepository tagRepository;
//    private final NodeMapper nodeMapper;
//
//    @Override
//    public Node create(Node node) {
//        NodeEntity nodeEntity = nodeMapper.toEntity(node);
//
//        return nodeMapper.toDto(nodeRepository.save(nodeEntity));
//    }
//
//    @Override
//    public Node get(BigInteger nodeId) {
//        return nodeMapper.toDto(nodeRepository.findById(nodeId).orElseThrow(() -> new NoNodeException(nodeId)));
//    }
//
//    @Override
//    public Node update(BigInteger nodeId, Node node) {
//        delete(nodeId);
//        node.setId(nodeId);
//
//        return create(node;
//    }
//
//    @Override
//    public void delete(BigInteger nodeId) {
//        NodeEntity existingNodeEntity = nodeRepository.findById(nodeId).orElseThrow(() -> new NoNodeException(nodeId));
//
//        nodeRepository.delete(existingNodeEntity);
//    }
//}
