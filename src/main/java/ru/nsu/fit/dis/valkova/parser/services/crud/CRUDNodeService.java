package ru.nsu.fit.dis.valkova.parser.services.crud;

import ru.nsu.fit.dis.valkova.parser.dao.Node;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import ru.nsu.fit.dis.valkova.parser.entities.NodeEntity;
import ru.nsu.fit.dis.valkova.parser.mapper.NodeMapper;
import ru.nsu.fit.dis.valkova.parser.repository.NodeRepository;
import ru.nsu.fit.dis.valkova.parser.repository.TagRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import java.math.BigInteger;

@Service
@RequiredArgsConstructor
public class CRUDNodeService implements CRUDNode {

    private final NodeRepository nodeRepository;
    private final TagRepository tagRepository;
    private final NodeMapper nodeMapper;

    @Override
    public Node create(Node node) throws DatatypeConfigurationException {
        NodeEntity nodeEntity = nodeMapper.dtoToEntity(node);
        return nodeMapper.entityToDto(nodeRepository.save(nodeEntity));
    }

    @Override
    public Node get(BigInteger nodeId) throws DatatypeConfigurationException {
        if (nodeRepository.findById(nodeId).isPresent()) {
            return nodeMapper.entityToDto(nodeRepository.findById(nodeId).get());
        } else return null;
    }

    @Override
    public Node update(BigInteger nodeId, Node node) throws DatatypeConfigurationException {
        delete(nodeId);
        node.setId(nodeId);
        return create(node);
    }

    @Override
    public boolean delete(BigInteger nodeId) {
        if (nodeRepository.findById(nodeId).isPresent()) {
            NodeEntity existingNodeEntity = nodeRepository.findById(nodeId).get();
            nodeRepository.delete(existingNodeEntity);
            return true;
        }
        return false;
    }
}
