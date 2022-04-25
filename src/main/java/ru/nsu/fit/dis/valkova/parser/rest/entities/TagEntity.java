package ru.nsu.fit.dis.valkova.parser.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(TagId.class)
@Table(name = "tags")
public class TagEntity implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinTable(name = "nodes")
    @Column(nullable = false, updatable = false, name = "node_id")
    private Long nodeId;

    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}