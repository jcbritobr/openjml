package org.openjml.core;

import java.security.SecureRandom;

/**
 * This class implements a thread safe random number generator.
 * Created by jgardona on 31/05/17.
 */
public class ConcurrentRandom extends SecureRandom {

    public ConcurrentRandom() {
        super();
    }

    public ConcurrentRandom(long seed) {
        this.setSeed(seed);
    }

    @Override
    public synchronized int nextInt(int bound) {
        return super.nextInt(bound);
    }

    @Override
    public synchronized int nextInt() {
        return super.nextInt();
    }

    @Override
    public synchronized float nextFloat() {
        return super.nextFloat();
    }

    @Override
    public synchronized double nextDouble() {
        return super.nextDouble();
    }

    @Override
    public synchronized long nextLong() {
        return super.nextLong();
    }

    @Override
    public synchronized boolean nextBoolean() {
        return super.nextBoolean();
    }

    @Override
    public synchronized double nextGaussian() {
        return super.nextGaussian();
    }

    @Override
    public synchronized void nextBytes(byte[] bytes) {
        super.nextBytes(bytes);
    }
}
