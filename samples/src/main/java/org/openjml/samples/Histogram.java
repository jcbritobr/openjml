package org.openjml.samples;

/**
 * Created by jgardona on 07/06/17.
 */
public class Histogram {
    public static void main(String[] args) {
        org.openjml.core.Histogram histogram = new org.openjml.core.Histogram(new int[]{1, 3, 6, 8, 11, 34, 55, 0, 1});
        System.out.printf("Mean: %f Deviation: %f", histogram.getMean(), histogram.getStdDev());
    }
}
