package org.openjml.fuzzy;

import java.util.ArrayList;
import java.util.List;

/**
 * OutputConstraint
 * Created by jgardona on 16/05/17.
 */
public class Output {
    private List<OutputConstraint> outputList;
    private LinguisticVariable outputVariable;

    public Output(LinguisticVariable outputVariable) {
        outputList = new ArrayList<>();
        this.outputVariable = outputVariable;
    }

    public List<OutputConstraint> getOutputList() {
        return outputList;
    }

    public LinguisticVariable getOutputVariable() {
        return outputVariable;
    }

    public void add(String labelName, float firingStrength) {
        outputList.add(new OutputConstraint(labelName, firingStrength));
    }

    public void clear() {
        outputList.clear();
    }
}
