package org.openjml.genetic.chromosomes;

import org.openjml.core.ConcurrentRandom;
import org.openjml.genetic.functions.FitnessFunction;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by jgardona on 07/06/17.
 */
public class BinaryChromosome implements Chromosome {
    protected int length;
    protected int value = 0;

    protected static final ConcurrentRandom random = new ConcurrentRandom();
    public static final int MAX_LENGTH = 64;

    public int value() {
        return value & new BigInteger("0xFFFFFFFFFFFFFFFF").shiftRight(64).intValue() - length;
    }

    public int getLength() {
        return length;
    }

    @Override
    public float fitness() {
        return 0;
    }

    @Override
    public void generate() {

    }

    @Override
    public Chromosome createNew() {
        return null;
    }

    @Override
    public Chromosome clone() {
        return null;
    }

    @Override
    public void mutate() {

    }

    @Override
    public void crossOver(Chromosome pair) {

    }

    @Override
    public void evaluate(FitnessFunction function) {

    }

    @Override
    public int compareTo(Chromosome o) {
        return 0;
    }
}
