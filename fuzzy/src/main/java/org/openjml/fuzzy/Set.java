package org.openjml.fuzzy;

import org.openjml.fuzzy.functions.Function;

/**
 * This class represents a fuzzy set
 * Created by jgardona on 15/05/17.
 */
public class Set {
    private String name;
    private Function function;

    public Set(String name, Function function) {
        this.name = name;
        this.function = function;
    }

    public String getName() {
        return name;
    }

    public float leftLimit() {
        return function.leftLimit();
    }

    public float rightLimit() {
        return function.rightLimit();
    }

    public float membership(float scalar) {
        return function.membership(scalar);
    }
}
