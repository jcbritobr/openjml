package org.openjml.genetic.chromosomes;

import org.openjml.genetic.functions.FitnessFunction;

/**
 * Created by jgardona on 07/06/17.
 */
public interface Chromosome extends Comparable<Chromosome> {
    float fitness();

    void generate();

    Chromosome createNew();

    Chromosome clone();

    void mutate();

    void crossOver(Chromosome pair);

    void evaluate(FitnessFunction function);

}
