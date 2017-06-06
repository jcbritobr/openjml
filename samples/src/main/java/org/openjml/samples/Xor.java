package org.openjml.samples;

import org.openjml.neuro.functions.SigmoidFunction;
import org.openjml.neuro.learning.BackPropagationLearning;
import org.openjml.neuro.networks.ActivationNetwork;

import java.io.IOException;
import java.util.Arrays;

/**
 * Xor sample uses a multilayer architecture and
 * backpropagation learning to solve a simple xor problem.
 * Created by jgardona on 02/06/17.
 */
public class Xor {
    public static void main(String[] args) throws IOException {

        System.out.println("Training neural network to solve xor problem ...");

        float[][] input = new float[][]{
                new float[]{0.0f, 0.0f}, new float[]{0.0f, 1.0f},
                new float[]{1.0f, 0.0f}, new float[]{1.0f, 1.0f}
        };

        float[][] output = new float[][]{
                new float[]{0.0f}, new float[]{1.0f},
                new float[]{1.0f}, new float[]{0.0f}
        };

        ActivationNetwork network = new ActivationNetwork(
                new SigmoidFunction(2.0f),
                2,
                2,
                1
        );

        BackPropagationLearning teacher = new BackPropagationLearning(network);
        teacher.setLearningRate(0.1f);
        teacher.setMomentum(0.0f);
        boolean needstop = false;

        while (!needstop) {
            float error = teacher.runEpoch(input, output);
            System.out.printf("error %f%n", error);
            if (error <= 0.01) {
                needstop = true;
            }
        }

        float[] inputData1 = {0.0f, 0.0f};
        float[] inputData2 = {0.0f, 1.0f};
        float[] inputData3 = {1.0f, 0.0f};
        float[] inputData4 = {1.0f, 1.0f};

        float[] result1 = network.compute(inputData1);
        float[] result2 = network.compute(inputData2);
        float[] result3 = network.compute(inputData3);
        float[] result4 = network.compute(inputData4);

        System.out.printf("Input data: %s result: %f%n", Arrays.toString(inputData1), result1[0]);
        System.out.printf("Input data: %s result: %f%n", Arrays.toString(inputData2), result2[0]);
        System.out.printf("Input data: %s result: %f%n", Arrays.toString(inputData3), result3[0]);
        System.out.printf("Input data: %s result: %f%n", Arrays.toString(inputData4), result4[0]);
    }
}
