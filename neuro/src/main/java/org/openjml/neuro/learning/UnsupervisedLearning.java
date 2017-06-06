package org.openjml.neuro.learning;

/**
 * UnsupervisedLearning
 * Created by jgardona on 31/05/17.
 */
public interface UnsupervisedLearning {
    float run(float[] input);

    float runEpoch(float[][] input);
}
