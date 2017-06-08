package org.openjml.neuro.learning;

import org.openjml.neuro.functions.ActivationFunction;
import org.openjml.neuro.layers.Layer;
import org.openjml.neuro.networks.ActivationNetwork;
import org.openjml.neuro.neurons.ActivationNeuron;

/**
 * DeltaRuleLearning.
 * Created by jgardona on 02/06/17.
 */
public class DeltaRuleLearning implements SupervisedLearning {

    private ActivationNetwork network;
    private float learningRate = 0.0f;

    public DeltaRuleLearning(ActivationNetwork network) {
        if (network.getLayers().length != 1) {
            throw new RuntimeException("nvalid neural network. It should have one layer only");
        }

        this.network = network;
    }

    @Override
    public float run(float[] input, float[] output) {

        float[] networkOutput = network.compute(input);
        Layer layer = network.getLayers()[0];
        ActivationFunction activationFunction = ((ActivationNeuron) layer.getNeurons()[0]).getFunction();

        float error = 0.0f;

        for (int j = 0; j < layer.getNeurons().length; j++) {
            ActivationNeuron neuron = (ActivationNeuron) layer.getNeurons()[j];
            float e = output[j] - networkOutput[j];
            float functionDerivative = activationFunction.derivativeY(networkOutput[j]);

            for (int i = 0; i < neuron.getWeights().length; i++) {
                neuron.getWeights()[i] += learningRate * e * functionDerivative * input[i];
            }

            float th = neuron.getThreshold() + learningRate * e * functionDerivative;

            neuron.setThreshold(th);

            error += (e * e);
        }

        return error / 2;
    }

    @Override
    public float runEpoch(float[][] input, float[][] output) {
        float error = 0.0f;

        for (int i = 0; i < input.length; i++) {
            error += run(input[i], output[i]);
        }

        return error;
    }

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = Math.max(0.0f, Math.min(1.0f, learningRate));
    }
}
