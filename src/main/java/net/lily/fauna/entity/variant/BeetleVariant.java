package net.lily.fauna.entity.variant;

import java.util.Arrays;
import java.util.Comparator;

public enum BeetleVariant {
    HERACROSS(0),
    STAG(1),
    HORNED(2),

    RHINO(3);

    private static final BeetleVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.
            comparingInt(BeetleVariant::getId)).toArray(BeetleVariant[]::new);
    private final int id;

    BeetleVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BeetleVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }

}