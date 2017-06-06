package org.openjml.neuro.learning;

/**
 * SupervisedLearning
 * Created by jgardona on 31/05/17.
 */
public interface SupervisedLearning {
    float run(float[] input, float[] output);

    float runEpoch(float[][] input, float[][] output);
}
