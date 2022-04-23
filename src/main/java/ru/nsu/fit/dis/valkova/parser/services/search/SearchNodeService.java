package ru.nsu.fit.dis.valkova.parser.services.search;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.fit.dis.valkova.parser.repository.NodeRepository;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

//@Service
//@RequiredArgsConstructor
//public class SearchNodeService implements SearchNode {
//    private final NodeRepository nodeRepository;
//    private final NodeMapper nodeMapper;
//
//    @Override
//    public List<Node> search(Double latitude, Double longitude, Double radius) throws DatatypeConfigurationException {
//        return nodeMapper.entityListToDtoList(nodeRepository.getNearNodes(latitude, longitude, radius));
//    }
//}