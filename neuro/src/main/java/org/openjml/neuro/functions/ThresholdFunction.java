package org.openjml.neuro.functions;

import java.io.Serializable;

/**
 * ThresholdFunction
 * Created by jgardona on 31/05/17.
 */
public class ThresholdFunction implements ActivationFunction, Serializable, Cloneable {
    @Override
    public float function(float scalar) {
        return scalar >= 0 ? 1 : 0;
    }

    @Override
    public float derivativeX(float scalar) {
        return 0;
    }

    @Override
    public float derivativeY(float scalar) {
        return 0;
    }

    @Override
    public ThresholdFunction clone() throws CloneNotSupportedException {
        return (ThresholdFunction) super.clone();
    }
}
