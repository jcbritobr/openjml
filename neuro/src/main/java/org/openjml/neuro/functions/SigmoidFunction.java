package org.openjml.neuro.functions;

import java.io.Serializable;

/**
 * Created by jgardona on 31/05/17.
 */
public class SigmoidFunction implements ActivationFunction, Serializable {

    private float alpha = 2.0f;

    public SigmoidFunction() {}

    public SigmoidFunction(float alpha) {
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    @Override
    public float function(float scalar) {
        return (float) (1.0f / (1.0f + Math.exp(-alpha * scalar)));
    }

    @Override
    public float derivativeX(float scalar) {
        float y = function(scalar);
        return (alpha * y * (1 - y));
    }

    @Override
    public float derivativeY(float scalar) {
        return (alpha * scalar * (1 - scalar));
    }

    @Override
    public SigmoidFunction clone() {
        return new SigmoidFunction(alpha);
    }
}
