package org.openjml.neuro.networks;

import org.openjml.neuro.functions.ActivationFunction;
import org.openjml.neuro.layers.ActivationLayer;
import org.openjml.neuro.layers.Layer;


/**
 * ActivationNetwork
 * Created by jgardona on 31/05/17.
 */
public class ActivationNetwork extends Network {

    public ActivationNetwork(ActivationFunction function, int inputCount, int ... neuronCount) {
        super(inputCount, neuronCount.length);

        for (int i = 0; i < layers.length; i++) {
            layers[i] = new ActivationLayer(neuronCount[i], (i == 0) ? inputCount : neuronCount[i - 1], function);
        }
    }

    public void setActivationFunction(ActivationFunction function) {
        for (int i = 0; i < layers.length; i++) {
            ((ActivationLayer) layers[i]).setActivationFuncion(function);
        }
    }

    @Override
    public float[] compute(float[] input) {
        float[] out = input;
        for (Layer layer : layers) {
            out = layer.compute(out);
        }

        this.output = out;

        return out;
    }

    @Override
    public void randomize() {
        for (Layer layer :
                layers) {
            layer.randomize();
        }
    }
}
