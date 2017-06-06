package org.openjml.neuro.learning;

import org.openjml.neuro.layers.Layer;
import org.openjml.neuro.networks.DistanceNetwork;
import org.openjml.neuro.neurons.Neuron;

/**
 * SOMLearning SOMLearning Created by jgardona on 03/06/17.
 */
public class SOMLearning implements UnsupervisedLearning {

    private DistanceNetwork network;
    private int width;
    private int height;
    private float learningRate = 0.1f;
    private float learningRadius = 7;
    private float squaredRadius = 2 * 7 * 7;

    public SOMLearning(DistanceNetwork network) {
        int neuronCount = network.getLayers()[0].getNeurons().length;
        int lWidth = (int) Math.sqrt(neuronCount);

        if (lWidth * lWidth != neuronCount) {
            throw new RuntimeException("Invalid network size");
        }

        this.network = network;
        this.width = lWidth;
        this.height = lWidth;
    }

    public SOMLearning(DistanceNetwork network, int width, int height) {

        if (network.getLayers()[0].getNeurons().length != width * height) {
            throw new RuntimeException("Invalid network size");
        }

        this.network = network;
        this.width = width;
        this.height = height;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = Math.max(0.0f, Math.min(1.0f, learningRate));
    }

    public float getLearningRadius() {
        return learningRadius;
    }

    public void setLearningRadius(float learningRadius) {
        this.learningRadius = Math.max(0.0f, learningRate);
        this.squaredRadius = 2 * learningRadius * learningRadius;
    }

    @Override
    public float run(float[] input) {
        float error = 0.0f;

        network.compute(input);
        int winner = network.getWinner();

        Layer layer = network.getLayers()[0];

        if (learningRadius == 0.0f) {
            Neuron neuron = layer.getNeurons()[winner];
            for (int i = 0; i < neuron.getWeights().length; i++) {
                float e = input[i] - neuron.getWeights()[i];
                error += Math.abs(e);
                neuron.getWeights()[i] += e * learningRate;
            }
        } else {
            int wx = winner % width;
            int wy = winner % height;

            for (int j = 0; j < layer.getNeurons().length; j++) {
                Neuron neuron = layer.getNeurons()[j];
                int dx = (j % width) - wx;
                int dy = (j / width) - wy;

                float factor = (float) Math.exp(-(float) (dx * dx + dy * dy) / squaredRadius);

                for (int i = 0; i < neuron.getWeights().length; i++) {
                    float e = (input[i] - neuron.getWeights()[i]) * factor;
                    error += Math.abs(e);
                    neuron.getWeights()[i] += e * learningRate;
                }
            }
        }

        return error;
    }

    @Override
    public float runEpoch(float[][] input) {
        float error = 0.0f;

        for (float[] sample
                : input) {
            error += run(sample);
        }

        return error;
    }
}
