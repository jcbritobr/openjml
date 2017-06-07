package org.openjml.fuzzy;

/**
 * This class represents a fuzzy clause
 * Created by jgardona on 15/05/17.
 */
public class Clause {
    private LinguisticVariable variable;
    private Set label;

    public LinguisticVariable getVariable() {
        return variable;
    }

    public Set getLabel() {
        return label;
    }

    public Clause(LinguisticVariable variable, Set label) {
        this.label = label;
        this.variable = variable;
    }

    public float evaluate() {
        return label.membership(variable.getInput());
    }

    @Override
    public String toString() {
        return String.format("%s IS %s", variable.getName(), label.getName());
    }
}
