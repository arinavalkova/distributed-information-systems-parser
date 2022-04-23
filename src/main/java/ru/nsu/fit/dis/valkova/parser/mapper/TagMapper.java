package ru.nsu.fit.dis.valkova.parser.mapper;

import ru.nsu.fit.dis.valkova.parser.dao.Tag;
import ru.nsu.fit.dis.valkova.parser.entities.TagEntity;

import java.math.BigInteger;

public class TagMapper implements Mapper<Tag, TagEntity> {

    @Override
    public TagEntity dtoToEntity(Tag tag) {
        if (tag == null) return null;
        return new TagEntity(tag.getId().longValue(), tag.getK(), tag.getV());
    }

    @Override
    public Tag entityToDto(TagEntity tagEntity) {
        if (tagEntity == null) return null;
        return new Tag(BigInteger.valueOf(tagEntity.getId()), tagEntity.getKey(), tagEntity.getValue());
    }
}
