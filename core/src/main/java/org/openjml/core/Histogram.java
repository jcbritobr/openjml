package org.openjml.core;

import java.io.Serializable;

/**
 * Histogram
 * Created by jgardona on 31/05/17.
 */
public class Histogram implements Serializable {
    private int[] values;
    private float mean = 0.0f;
    private float stdDev = 0.0f;
    private int median = 0;
    private int min;
    private int max;
    private long totalCount;

    public Histogram(int[] values) {
        this.values = values;
        update();
    }

    public Range range(float percent) {
        return Statistics.getRange(values, percent);
    }

    public int[] getValues() {
        return values;
    }

    public float getMean() {
        return mean;
    }

    public float getStdDev() {
        return stdDev;
    }

    public int getMedian() {
        return median;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void update() {
        int i, n = values.length;
        max = 0;
        min = n;
        totalCount = 0;

        for (int j = 0; j < n; j++) {
            if (values[j] != 0) {
                if (j > max) {
                    max = j;
                }

                if (j < min) {
                    min = j;
                }

                totalCount += values[j];
            }
        }

        mean = Statistics.mean(values);
        stdDev = Statistics.standardDeviation(values, mean);
        median = Statistics.median(values);
    }
}
