package org.openjml.neuro.functions;

import java.io.Serializable;

/**
 * BipolarSigmoidFunction
 * Created by jgardona on 31/05/17.
 */
public class BipolarSigmoidFunction implements ActivationFunction, Serializable, Cloneable {
    private float alpha = 2;

    public BipolarSigmoidFunction() {
    }

    public BipolarSigmoidFunction(float alpha) {
        this.alpha = alpha;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    @Override
    public float function(float scalar) {
        return (float) ((2 / (1 + Math.exp(-alpha * scalar))) - 1);
    }

    @Override
    public float derivativeX(float scalar) {
        float y = function(scalar);

        return (alpha * (1 - y * y) / 2);
    }

    @Override
    public float derivativeY(float scalar) {
        return (alpha * (1 - scalar * scalar) / 2);
    }

    @Override
    public BipolarSigmoidFunction clone() throws CloneNotSupportedException {
        BipolarSigmoidFunction function = (BipolarSigmoidFunction) super.clone();
        function.setAlpha(alpha);
        return function;
    }
}
