package ru.nsu.fit.dis.valkova.parser.mapper;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.List;

public interface Mapper<T, K> {

    K dtoToEntity(T object);

    T entityToDto(K object) throws DatatypeConfigurationException;
}
