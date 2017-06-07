package org.openjml.neuro.layers;

import org.openjml.neuro.neurons.DistanceNeuron;
import org.openjml.neuro.neurons.Neuron;

import java.io.Serializable;

/**
 * DistanceLayer.
 * Created by jgardona on 31/05/17.
 */
public class DistanceLayer extends Layer {

    public DistanceLayer(int inputCount, int neuronCount) {
        super(neuronCount, inputCount);

        for (int i = 0; i < neuronCount; i++) {
            neurons[i] = new DistanceNeuron(inputCount);
        }
    }

    @Override
    public float[] compute(float[] input) {
        float[] out = new float[neuronCount];

        for (int i = 0; i < neurons.length; i++) {
            out[i] = neurons[i].compute(input);
        }

        this.output = out;
        return out;
    }

    @Override
    public void randomize() {
        for (Neuron neuron :
                neurons) {
            neuron.randomize();
        }
    }
}
