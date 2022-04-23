package ru.nsu.fit.dis.valkova.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.dis.valkova.parser.entities.NodeEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, BigInteger> {
    @Query(value = "select * from nodes " +
            "where (acos(sin(pi() * ?1 / 180.0) * sin(pi() * nodes.latitude / 180.0) " +
            "+ cos(pi()* ?1 / 180.0) * cos(pi() * nodes.latitude / 180.0) " +
            "* cos(pi() * nodes.longitude / 180.0 - pi() * ?2 / 180.0)) * 6371) " +
            "* 1000 < ?3", nativeQuery = true)
    List<NodeEntity> getNearNodes(double latitude, double longitude, double radius);
}