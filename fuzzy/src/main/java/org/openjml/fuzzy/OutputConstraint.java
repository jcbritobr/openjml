package org.openjml.fuzzy;

public class OutputConstraint {
    private String label;
    private float firingStrength;

    OutputConstraint(String label, float firingStrength) {
        this.label = label;
        this.firingStrength = firingStrength;
    }

    public String getLabel() {
        return label;
    }

    public float getFiringStrength() {
        return firingStrength;
    }
}