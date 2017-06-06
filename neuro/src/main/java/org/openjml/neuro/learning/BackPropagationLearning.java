package org.openjml.neuro.learning;

import org.openjml.neuro.functions.ActivationFunction;
import org.openjml.neuro.layers.Layer;
import org.openjml.neuro.networks.ActivationNetwork;
import org.openjml.neuro.neurons.ActivationNeuron;
import org.openjml.neuro.neurons.Neuron;

/**
 * BackPropagationLearning
 * Created by jgardona on 31/05/17.
 */
public class BackPropagationLearning implements SupervisedLearning {

    private ActivationNetwork network;
    private float learningRate = 0.1f;
    private float momentum = 0.0f;
    private float[][] neuronErrors;
    private float[][][] weightUpdates;
    private float[][] thresholdUpdates;

    private float calcError(float[] output) {
        Layer layer, nextLayer;
        float[] errors, nextErrors;
        float error = 0, e, sum;
        float out;
        float layerCount = network.getLayers().length;

        ActivationFunction function = ((ActivationNeuron) network.getLayers()[0].getNeurons()[0]).getFunction();
        layer = network.getLayers()[(int) layerCount - 1];
        errors = neuronErrors[(int) layerCount - 1];

        for (int i = 0; i < layer.getNeurons().length; i++) {
            out = layer.getNeurons()[i].getOutput();
            e = output[i] - out;
            errors[i] = e * function.derivativeY(out);
            error += (e * e);
        }

        for (int j = (int) layerCount - 2; j >= 0; j--) {
            layer = network.getLayers()[j];
            nextLayer = network.getLayers()[j + 1];
            errors = neuronErrors[j];
            nextErrors = neuronErrors[j + 1];

            for (int i = 0; i < layer.getNeurons().length; i++) {
                sum = 0.0f;
                for (int k = 0; k < nextLayer.getNeurons().length; k++) {
                    sum += nextErrors[k] * nextLayer.getNeurons()[k].getWeights()[i];
                }

                errors[i] = sum * function.derivativeY(layer.getNeurons()[i].getOutput());
            }
        }

        return error / 2.0f;
    }

    private void calcUpdate(float[] input) {
        Neuron neuron;
        Layer layer, prevLayer;
        float[][] weightUpdateLayer;
        float[] thresholdUpdateLayer;
        float[] errors;
        float[] weightUpdateNeuron;

        layer = network.getLayers()[0];
        errors = neuronErrors[0];
        weightUpdateLayer = weightUpdates[0];
        thresholdUpdateLayer = thresholdUpdates[0];

        float cachedMomentum = learningRate * momentum;
        float normCachedMomentum = learningRate * (1 - momentum);
        float cachedError;

        for (int i = 0; i < layer.getNeurons().length; i++) {
            neuron = layer.getNeurons()[i];
            cachedError = errors[i] * normCachedMomentum;
            weightUpdateNeuron = weightUpdateLayer[i];

            for (int j = 0; j < weightUpdateNeuron.length; j++) {
                weightUpdateNeuron[j] = cachedMomentum * weightUpdateNeuron[j] + cachedError * input[j];
            }
            thresholdUpdateLayer[i] = cachedMomentum * thresholdUpdateLayer[i] + cachedError;
        }


        for (int k = 1; k < network.getLayers().length; k++) {
            prevLayer = network.getLayers()[k - 1];
            layer = network.getLayers()[k];
            errors = neuronErrors[k];
            weightUpdateLayer = weightUpdates[k];
            thresholdUpdateLayer = thresholdUpdates[k];

            for (int i = 0; i < layer.getNeurons().length; i++) {
                neuron = layer.getNeurons()[i];
                cachedError = errors[i] * normCachedMomentum;
                weightUpdateNeuron = weightUpdateLayer[i];
                for (int j = 0; j < weightUpdateNeuron.length; j++) {
                    weightUpdateNeuron[j] = cachedMomentum * weightUpdateNeuron[j] + cachedError * prevLayer.getNeurons()[j].getOutput();
                }

                thresholdUpdateLayer[i] = cachedMomentum * thresholdUpdateLayer[i] + cachedError;
            }

        }
    }

    private void updateNerwork() {
        ActivationNeuron neuron;
        Layer layer;
        float[][] weightUpdatelayer;
        float[] thresholdUpdateLayer;
        float[] weightUpdateNeuron;

        for (int i = 0; i < network.getLayers().length; i++) {
            layer = network.getLayers()[i];
            weightUpdatelayer = weightUpdates[i];
            thresholdUpdateLayer = thresholdUpdates[i];

            for (int j = 0; j < layer.getNeurons().length; j++) {
                neuron = (ActivationNeuron) layer.getNeurons()[j];
                weightUpdateNeuron = weightUpdatelayer[j];

                for (int k = 0; k < neuron.getWeights().length; k++) {
                    neuron.getWeights()[k] += weightUpdateNeuron[k];
                }

                float th = neuron.getThreshold() + thresholdUpdateLayer[j];
                neuron.setThreshold(th);
            }
        }
    }

    public BackPropagationLearning(ActivationNetwork network) {
        this.network = network;

        neuronErrors = new float[network.getLayers().length][];
        weightUpdates = new float[network.getLayers().length][][];
        thresholdUpdates = new float[network.getLayers().length][];

        for (int i = 0; i < network.getLayers().length; i++) {
            Layer layer = network.getLayers()[i];
            neuronErrors[i] = new float[layer.getNeurons().length];
            weightUpdates[i] = new float[layer.getNeurons().length][];
            thresholdUpdates[i] = new float[layer.getNeurons().length];

            for (int j = 0; j < weightUpdates[i].length; j++) {
                weightUpdates[i][j] = new float[layer.getInputCount()];
            }
        }
    }

    @Override
    public float run(float[] input, float[] output) {
        network.compute(input);
        float error = calcError(output);
        calcUpdate(input);
        updateNerwork();

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

    public float getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(float learningRate) {
        this.learningRate = (float) Math.max(0.0, Math.min(1.0, learningRate));
    }

    public float getMomentum() {
        return momentum;
    }

    public void setMomentum(float momentum) {
        this.momentum = (float) Math.max(0.0, Math.min(1.0, momentum));
    }
}
