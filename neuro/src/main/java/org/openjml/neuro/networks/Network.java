package org.openjml.neuro.networks;

import org.openjml.neuro.layers.Layer;

import java.io.*;

/**
 * Network
 * Created by jgardona on 31/05/17.
 */
public abstract class Network implements Serializable {
    protected int inputCount;
    protected int layerCount;
    protected Layer[] layers;
    protected float[] output;

    protected Network(int inputCount, int layerCount) {
        this.inputCount = Math.max(1, inputCount);
        this.layerCount = Math.max(1, layerCount);
        this.layers = new Layer[this.layerCount];
    }

    public static void save(String fileName, Network network) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(network);
        oos.close();
    }

    public static Network load(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        Network network = (Network) ois.readObject();
        return network;
    }

    public abstract float[] compute(float[] input);

    public abstract void randomize();

    public int getInputCount() {
        return inputCount;
    }

    public Layer[] getLayers() {
        return layers;
    }

    public float[] getOutput() {
        return output;
    }
}
