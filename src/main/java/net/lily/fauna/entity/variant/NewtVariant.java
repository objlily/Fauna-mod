package net.lily.fauna.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum NewtVariant {
    ALPINE(0),
    ORANGE(1),
    SPOTTED(2),
    ROUGH(3);

    private static final NewtVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(NewtVariant::getId)).toArray(NewtVariant[]::new);
    private final int id;

    NewtVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static NewtVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}