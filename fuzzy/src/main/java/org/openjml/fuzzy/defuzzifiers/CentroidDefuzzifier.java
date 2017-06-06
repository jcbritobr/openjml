package org.openjml.fuzzy.defuzzifiers;

import org.openjml.fuzzy.Output;
import org.openjml.fuzzy.OutputConstraint;
import org.openjml.fuzzy.operators.Norm;

/**
 * This class represents a defuzzifier.
 * Created by jgardona on 16/05/17.
 */
public class CentroidDefuzzifier implements Defuzzifier {

    private int intervals;

    public CentroidDefuzzifier(int intervals) {
        this.intervals = intervals;
    }

    @Override
    public float defuzzify(Output output, Norm normOperator) {
        float weightSum = 0F;
        float membershipSum = 0.F;

        float start = output.getOutputVariable().getStart();
        float end = output.getOutputVariable().getEnd();

        float increment = (end - start) / this.intervals;

        for (float scalar = start; scalar < end; scalar += increment) {

            for (OutputConstraint oc : output.getOutputList()) {
                float membership = output.getOutputVariable().membership(oc.getLabel(), scalar);
                float constrainedMembership = normOperator.evaluate(membership, oc.getFiringStrength());

                weightSum += scalar * constrainedMembership;
                membershipSum += constrainedMembership;
            }
        }

        if (membershipSum == 0) {
            throw new RuntimeException("The numerical output in unavaliable. All memberships are zero");
        }

        return weightSum / membershipSum;
    }
}
