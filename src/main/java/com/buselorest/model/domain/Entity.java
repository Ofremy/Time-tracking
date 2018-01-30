package com.buselorest.model.domain;

import java.io.Serializable;

public class Entity implements Serializable, Cloneable {
    private int id;

    /**
     * Constructor for creating a new object without parameters
     * @see Entity#Entity()
     */
    Entity() {
    }

    /**
     * Constructor for creating a new object
     * @param id - id
     * @see Entity#Entity(int)
     */
    Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entity entity = (Entity) o;

        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
