package org.openjml.neuro.networks;

import org.openjml.neuro.layers.DistanceLayer;
import org.openjml.neuro.layers.Layer;

import java.io.Serializable;

/**
 * DistanceNetwork
 * Created by jgardona on 31/05/17.
 */
public class DistanceNetwork extends Network {

    protected DistanceNetwork(int inputCount, int neuronCount) {
        super(inputCount, 1);
        layers[0] = new DistanceLayer(neuronCount, inputCount);
    }

    public int getWinner() {
        float min = output[0];
        int minIndex = 0;

        for (int i = 0; i < output.length; i++) {
            if (output[i] < min) {
                min = output[i];
                minIndex = i;
            }
        }

        return minIndex;
    }

    @Override
    public float[] compute(float[] input) {
        float[] output = input;
        for (Layer layer : layers) {
            output = layer.compute(output);
        }

        this.output = output;

        return output;
    }

    @Override
    public void randomize() {
        for (Layer layer :
                layers) {
            layer.randomize();
        }
    }
}
