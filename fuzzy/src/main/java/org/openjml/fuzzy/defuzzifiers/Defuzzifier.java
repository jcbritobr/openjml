package org.openjml.fuzzy.defuzzifiers;

import org.openjml.fuzzy.Output;
import org.openjml.fuzzy.operators.Norm;

/**
 * Interface Defuzzifier
 * Created by jgardona on 16/05/17.
 */
public interface Defuzzifier {
    float defuzzify(Output output, Norm normOperator);
}
