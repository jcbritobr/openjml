package org.openjml.neuro.functions;

/**
 * ActivationFunction represents a rna activation function.
 * Created by jgardona on 31/05/17.
 */
public interface ActivationFunction {
    float function(float scalar);

    float derivativeX(float scalar);

    float derivativeY(float scalar);

}
