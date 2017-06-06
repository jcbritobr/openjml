package org.openjml.neuro.layers;

import org.openjml.neuro.neurons.Neuron;

import java.io.Serializable;

/**
 * Layer
 * Created by jgardona on 31/05/17.
 */
public abstract class Layer implements Serializable {
    protected int inputCount = 0;
    protected int neuronCount = 0;
    protected Neuron[] neurons;
    protected float[] output;

    public Layer(int neuronCount, int inputCount) {
        this.inputCount = Math.max(1, inputCount);
        this.neuronCount = Math.max(1, neuronCount);

        neurons = new Neuron[this.neuronCount];
    }

    public abstract float[] compute(float[] input);

    public abstract void randomize();

    public int getInputCount() {
        return inputCount;
    }

    public Neuron[] getNeurons() {
        return neurons;
    }

    public float[] getOutput() {
        return output;
    }
}
