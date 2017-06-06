package org.openjml.neuro.learning;

import org.openjml.neuro.layers.Layer;
import org.openjml.neuro.networks.ActivationNetwork;
import org.openjml.neuro.neurons.ActivationNeuron;

/**
 * PerceptronLearning
 * Created by jgardona on 03/06/17.
 */
public class PerceptronLearning implements SupervisedLearning {
    private ActivationNetwork network;
    private float learningRate = 0.1f;

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = Math.max(0.0f, Math.min(1.0f, learningRate));
    }

    public PerceptronLearning(ActivationNetwork network) {
        if (network.getLayers().length != 1) {
            throw new RuntimeException("Invalid network. It should have one layer");
        }

        this.network = network;
    }

    @Override
    public float run(float[] input, float[] output) {
        float[] networkOutput = network.compute(input);
        Layer layer = network.getLayers()[0];

        float error = 0.0f;

        for (int j = 0; j < layer.getNeurons().length; j++) {
            float e = output[j] - networkOutput[j];
            if (e != 0) {
                ActivationNeuron perceptron = (ActivationNeuron) layer.getNeurons()[j];

                for (int i = 0; i < perceptron.getWeights().length; i++) {
                    perceptron.getWeights()[i] += learningRate * e * input[i];
                }

                float th = perceptron.getThreshold() + learningRate * e;
                perceptron.setThreshold(th);
                error += Math.abs(e);
            }
        }

        return error;
    }

    @Override
    public float runEpoch(float[][] input, float[][] output) {
        float error = 0.0f;

        for (int i = 0; i < input.length; i++) {
            error += run(input[i], output[i]);
        }

        return error;
    }
}
