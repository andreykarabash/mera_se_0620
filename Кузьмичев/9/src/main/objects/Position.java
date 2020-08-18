/*********************************************************
 * File: Position.java
 * Defines the position and its features
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ********************************************************/

package main.objects;

import main.objects.position.effects.Effect;
import main.objects.position.types.PositionType;

import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Position {
    private static Integer MIN_POSITION_NUMBER = 1;
    private static Integer MAX_POSITION_NUMBER = Integer.MAX_VALUE - 1;
    public static PositionType DEFAULT_POSITION_TYPE = null;
    public static Effect DEFAULT_EFFECT = null;

    private static HashSet<Integer> idPool;

    private Integer id;
    private Integer position;
    private PositionType positionType;
    private Effect effect;

    /**
     * Empty constructor for implementation game save
     */
    Position() {
    }

    public Position(Integer position) {
        this(position, DEFAULT_POSITION_TYPE, DEFAULT_EFFECT);
    }

    public Position(Integer position, PositionType positionType) {
        this(position, positionType, DEFAULT_EFFECT);
    }

    public Position(Integer position, PositionType positionType, Effect effect) {
        if (position < MIN_POSITION_NUMBER) {
            throw new IllegalStateException("Position number less or equals zero");
        }

        if (idPool == null) {
            idPool = new HashSet<>();
        }

        this.id = ThreadLocalRandom.current().nextInt(MIN_POSITION_NUMBER, MAX_POSITION_NUMBER);

        while (idPool.contains(this.id)) {
            this.id = ThreadLocalRandom.current().nextInt(MIN_POSITION_NUMBER, MAX_POSITION_NUMBER);
        }

        idPool.add(this.id);

        this.position = position;
        this.positionType = positionType;
        this.effect = effect;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPosition() {
        return position;
    }

    void setPosition(Integer position) {
        this.position = position;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public Effect getEffect() {
        return effect;
    }

    void setEffect(Effect effect) {
        this.effect = effect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}