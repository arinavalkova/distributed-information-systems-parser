package ru.nsu.fit.dis.valkova.parser.mapper;

//import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;
//import ru.nsu.fit.dis.valkova.parser.rest.entities.TagEntity;
//
//import javax.xml.datatype.DatatypeConfigurationException;
//import java.util.LinkedList;
//import java.util.List;
//
//public class NodeMapper implements Mapper<Node, NodeEntity> {
//
//    @Override
//    public NodeEntity dtoToEntity(Node node) {
//        return null;
//    }
//
//    @Override
//    public Node entityToDto(NodeEntity nodeEntity) throws DatatypeConfigurationException {
//        if (nodeEntity == null) return null;
//
//        TagMapper tagMapper = new TagMapper();
//        List<Tag> tags = new LinkedList<>();
//
//        if (nodeEntity.getTags() != null) {
//            for (TagEntity tag : nodeEntity.getTags()) {
//                tags.add(tagMapper.entityToDto(tag));
//            }
//        }
//        return new Node(
//                nodeEntity.getId(),
//                nodeEntity.getLatitude(),
//                nodeEntity.getLongitude(),
//                nodeEntity.getUsername(),
//                nodeEntity.getUid(),
//                nodeEntity.getVersion(),
//                nodeEntity.getChangeset(),
//                nodeEntity.getTimestamp(),
//                tags
//        );
//    }
//
//    public List<Node> entityListToDtoList(List<NodeEntity> entities) throws DatatypeConfigurationException {
//        List<Node> result = new LinkedList<>();
//        if (entities != null) {
//            for (NodeEntity entity: entities) {
//                result.add(entityToDto(entity));
//            }
//        }
//        return result;
//    }
//}
