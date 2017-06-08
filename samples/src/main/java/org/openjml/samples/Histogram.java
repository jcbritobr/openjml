package org.openjml.samples;

/**
 * Created by jgardona on 07/06/17.
 */
public class Histogram {
    public static void main(String[] args) {
        org.openjml.core.Histogram histogram = new org.openjml.core.Histogram(1, 1, 2, 3, 6, 8, 11, 12, 7, 3);
        System.out.printf("Mean: %f Deviation: %f", histogram.getMean(), histogram.getStdDeviation());
    }
}
