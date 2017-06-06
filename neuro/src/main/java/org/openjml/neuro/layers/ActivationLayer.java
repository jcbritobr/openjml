package org.openjml.neuro.layers;

import org.openjml.neuro.functions.ActivationFunction;
import org.openjml.neuro.neurons.ActivationNeuron;
import org.openjml.neuro.neurons.Neuron;

import java.io.Serializable;

/**
 * ActivationLayer
 * Created by jgardona on 31/05/17.
 */
public class ActivationLayer extends Layer implements Serializable {

    public ActivationLayer(int neuronCount, int inputCount, ActivationFunction function) {
        super(neuronCount, inputCount);

        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new ActivationNeuron(inputCount, function);
        }
    }

    public void setActivationFuncion(ActivationFunction function) {
        for (int i = 0; i < neurons.length; i++) {
            ((ActivationNeuron) neurons[i]).setFunction(function);
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
