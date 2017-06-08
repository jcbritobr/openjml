package org.openjml.core;

/**
 * Created by jgardona on 31/05/17.
 */
public class Gaussian {
    private float sigma = 1.0f;
    private float squaredSigma = 1.0f;

    public Gaussian() {
    }

    public Gaussian(float sigma) {
        setSigma(sigma);
    }

    public float function(float scalar) {
        return (float) (Math.exp(scalar * scalar / (-2 * sigma)) / (Math.sqrt(2 * Math.PI) * sigma));
    }

    public float function2D(float scalarX, float scalarY) {
        return (float) (Math.exp((scalarX * scalarX + scalarY * scalarY) / (-2 * squaredSigma)) / (2 * Math.PI * squaredSigma));
    }

    public float[] kernel(int size) {
        if (((size % 2) == 0) || (size < 3) || size > 101) {
            throw new RuntimeException("Wrong Kernel size");
        }

        int r = size / 2;
        float[] kernel = new float[size];
        for (int x = -r, i = 0; i < size; x++, i++) {
            kernel[i] = function(x);
        }

        return kernel;
    }

    public float[][] kernel2D(int size) {
        if (((size % 2) == 0) || (size < 3) || (size > 101)) {
            throw new RuntimeException("Wrong kernel size");
        }

        int r = size / 2;
        float[][] kernel = new float[size][size];

        for (int y = -r, i = 0; i < size; y++, i++) {
            for (int x = -r, j = 0; j < size; x++, j++) {
                kernel[i][j] = function2D(x, y);
            }
        }

        return  kernel;
    }

    public float getSigma() {
        return sigma;
    }

    public void setSigma(float sigma) {
        this.sigma = (float) Math.max(0.00000001, sigma);
        this.squaredSigma = (float) Math.pow(sigma, 2.0);
    }


}
