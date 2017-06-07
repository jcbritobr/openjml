package org.openjml.core;

/**
 * Created by jgardona on 07/06/17.
 */
public final class Statistics {
    public static float mean(int[] values) {
        int hits;
        long total = 0;
        float mean = 0.0f;

        for (int i = 0; i < values.length; i++) {
            hits = values[i];
            mean += i * hits;
            total += hits;
        }

        return (total == 0) ? 0 : mean / total;
    }

    public static float standardDeviation(int[] values) {
        return standardDeviation(values, mean(values));
    }

    public static float standardDeviation(int[] values, float mean) {
        float standardDeviation = 0.0f;
        float diff;
        int hits;
        int total = 0;

        for (int i = 0; i < values.length; i++) {
            hits = values[i];
            diff = i - mean;
            standardDeviation += diff * diff * hits;
            total += hits;
        }

        return (total == 0) ? 0 : (float) Math.sqrt(standardDeviation / total);
    }

    public static int median(int[] values) {
        int total = 0;
        int n = values.length;

        for (int value : values) {
            total += value;
        }

        int halfTotal = total / 2;
        int median = 0;
        int v = 0;

        for (; median < n; median++) {
            v += values[median];
            if (v >= halfTotal) {
                break;
            }
        }

        return median;
    }

    public static Range getRange(int[] values, float percent) {
        int total = 0;
        int n = values.length;

        for (int i = 0; i < n; i++) {
            total += values[i];
        }

        int min, max, hits;
        int h = (int) (total * (percent + (1 - percent) / 2));

        for (min = 0, hits = total; min < n; min++) {
            hits -= values[min];
            if (hits < h) {
                break;
            }
        }

        for (max = n - 1, hits = total; max >= 0; max--) {
            hits -= values[max];
            if (hits < h) {
                break;
            }
        }

        return new Range(min, max);
    }

    public static float entropy(int[] values) {
        int n = values.length;
        int total = 0;
        float entropy = 0.0f;
        float p;

        for (int i = 0; i < n; i++) {
            total += values[i];
        }

        if (total != 0) {
            for (int i = 0; i < n; i++) {
                p = (float) values[i] / total;
                if (p != 0) {
                    entropy += (-p * Math.log(p));
                }
            }
        }

        return entropy;
    }

    public static int mode(int[] values) {
        int mode = 0, curMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > curMax) {
                curMax = values[i];
                mode = i;
            }
        }

        return mode;
    }
}
