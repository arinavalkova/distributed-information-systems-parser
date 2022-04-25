package ru.nsu.fit.dis.valkova.parser.rest.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagId implements Serializable {

    private  Long nodeId;

    private String key;
}