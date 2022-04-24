package ru.nsu.fit.dis.valkova.parser.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.dis.valkova.parser.rest.entities.NodeEntity;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface NodeRepository extends JpaRepository<NodeEntity, Long> {
    @Query(value = "SELECT * FROM nodes " +
            "WHERE earth_box(ll_to_earth(:lat, :lon), :rad) @> ll_to_earth(latitude, longitude) and " +
            "earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(latitude, longitude)) < :rad " +
            "ORDER BY earth_distance(ll_to_earth(:lat, :lon), ll_to_earth(latitude, longitude)) ASC",
            nativeQuery = true)
    List<NodeEntity> getNearNodes(@Param("lat") Double latitude,
                                  @Param("lon") Double longitude,
                                  @Param("rad") Double radius);

}