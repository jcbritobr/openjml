package org.openjml.neuro.neurons;

import org.openjml.neuro.functions.ActivationFunction;

import java.io.Serializable;

/**
 * ActivationNeuron represents a ann activation neuron.
 * Created by jgardona on 31/05/17.
 */
public class ActivationNeuron extends Neuron {
    protected float threshold = 0.0f;
    protected ActivationFunction function;

    public ActivationNeuron(int inputs, ActivationFunction function) {
        super(inputs);
        this.function = function;
    }

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    public void setFunction(ActivationFunction function) {
        this.function = function;
    }

    public ActivationFunction getFunction() {
        return function;
    }

    @Override
    public void randomize() {
        super.randomize();
        threshold = concurrentRandom.nextFloat() * (randRange.length()) + randRange.getMin();
    }

    @Override
    public float compute(float[] input) {
        if (input.length != inputCount) {
            throw new RuntimeException("Wrong length of the input vector");
        }

        float sum = 0.0f;

        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * input[i];
        }

        sum += threshold;
        float out = function.function(sum);
        output = out;

        return output;
    }
}
