package org.openjml.neuro.neurons;

import org.openjml.core.ConcurrentRandom;
import org.openjml.core.Range;

import java.io.Serializable;

/**
 * This class implements an ann abstract neuron.
 * Created by jgardona on 31/05/17.
 */
public abstract class Neuron implements Serializable{
    protected int inputCount = 0;
    protected float[] weights;
    protected float output = 0.0f;
    protected static ConcurrentRandom concurrentRandom = new ConcurrentRandom();
    protected static Range randRange = new Range(0.0f, 1.0f);

    protected Neuron(int inputs) {
        inputCount = Math.max(1, inputs);
        weights = new float[inputCount];
        randomize();
    }

    public void randomize() {
        float d = randRange.length();
        for (int i = 0; i < inputCount; i++) {
            weights[i] = concurrentRandom.nextFloat() * d + randRange.getMin();
        }
    }

    //public abstract void randomize();
    
    public abstract float compute(float[] input);

    public int getInputCount() {
        return inputCount;
    }

    public float getOutput() {
        return output;
    }

    public static void setConcurrentRandom(ConcurrentRandom concurrentRandom) {
        Neuron.concurrentRandom = concurrentRandom;
    }

    public static ConcurrentRandom getConcurrentRandom() {
        return concurrentRandom;
    }

    public static void setRandRange(Range randRange) {
        Neuron.randRange = randRange;
    }

    public static Range getRandRange() {
        return randRange;
    }

    public float[] getWeights() {
        return weights;
    }
}
