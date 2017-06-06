package org.openjml.neuro.learning;

import org.openjml.neuro.layers.Layer;
import org.openjml.neuro.networks.DistanceNetwork;
import org.openjml.neuro.neurons.Neuron;

/**
 * ElasticNetworkLearning
 * Created by jgardona on 02/06/17.
 */
public class ElasticNetworkLearning implements UnsupervisedLearning {
    private DistanceNetwork network;
    private float[] distance;
    private float learningRate = 0.1f;
    private float learningRadius = 0.5f;
    private float squaredRadius = 2 * 7 * 7;

    public ElasticNetworkLearning(DistanceNetwork network) {
        this.network = network;
        int neurons = network.getLayers()[0].getNeurons().length;
        float deltaAlpha = (float) (Math.PI * 2.0 / neurons);
        float alpha = deltaAlpha;

        distance = new float[neurons];
        distance[0] = 0.0f;

        for (int i = 1; i < neurons; i++) {
            float dx = (float) (0.5f * Math.cos(alpha) - 0.5);
            float dy = (float) (0.5f * Math.sin(alpha) - 0.5);

            distance[i] = dx * dx + dy * dy;

            alpha += deltaAlpha;
        }
    }

    @Override
    public float run(float[] input) {
        float error = 0.0f;

        network.compute(input);
        int winner = network.getWinner();

        Layer layer = network.getLayers()[0];

        for (int j = 0; j < layer.getNeurons().length; j++) {
            Neuron neuron = layer.getNeurons()[j];
            float factor = (float) Math.exp(-distance[Math.abs(j - winner)] / squaredRadius);
            for (int i = 0; i < neuron.getWeights().length; i++) {
                float e = (input[i] - neuron.getWeights()[i]) * factor;
                error += Math.abs(e);
                neuron.getWeights()[i] += e * learningRate;
            }
        }

        return error;
    }

    @Override
    public float runEpoch(float[][] input) {
        float error = 0.0f;

        for (float[] sample :
                input) {
            error += run(sample);
        }

        return error;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = (float) Math.max(0.0, Math.min(1.0, learningRate));
    }

    public float getLearningRadius() {
        return learningRadius;
    }

    public void setLearningRadius(float learningRadius) {
        this.learningRadius = (float) Math.max(0, Math.min(1.0, learningRadius));
        squaredRadius = 2 * learningRadius * learningRadius;
    }
}
