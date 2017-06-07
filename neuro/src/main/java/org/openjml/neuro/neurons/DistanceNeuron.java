package org.openjml.neuro.neurons;

import java.io.Serializable;

/**
 * DistanceNeuron represents a ann distance neuron.
 * Created by jgardona on 31/05/17.
 */
public class DistanceNeuron extends Neuron {


    public DistanceNeuron(int inputs) {
        super(inputs);
    }

    @Override
    public float compute(float[] input) {
        if (input.length != inputCount) {
            throw new RuntimeException("Wrong length of the input vector");
        }

        float dif = 0.0f;

        for (int i = 0; i < inputCount; i++) {
            dif += Math.abs(weights[i] - input[i]);
        }

        output = dif;
        return dif;
    }
}
