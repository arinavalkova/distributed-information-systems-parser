package ru.nsu.fit.dis.valkova.parser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.fit.dis.valkova.parser.entities.TagEntity;

import java.math.BigInteger;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, BigInteger> {}