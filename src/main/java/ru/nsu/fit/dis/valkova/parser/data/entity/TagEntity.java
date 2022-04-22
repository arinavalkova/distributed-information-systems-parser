package ru.nsu.fit.dis.valkova.parser.data.entity;

import java.math.BigInteger;

public class TagEntity {

    public final BigInteger id;
    public final String k;
    public final String v;

    public TagEntity(BigInteger id, String k, String v) {
        this.id = id;
        this.k = k;
        this.v = v;
    }

    public BigInteger getId() {
        return id;
    }

    public String getK() {
        return k;
    }

    public String getV() {
        return v;
    }
}
