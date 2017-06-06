package org.openjml.fuzzy;

import java.util.HashMap;

/**
 * This class represents a fuzzy linguistic variable
 * Created by jgardona on 15/05/17.
 */
public class LinguisticVariable {
    private String name;
    private float start;
    private float end;
    private float input;
    private HashMap<String, Set> labels;

    public LinguisticVariable(String name, float start, float end) {
        this.name = name;
        this.start = start;
        this.end = end;

        this.labels = new HashMap<>(10);
    }

    public void add(Set set) {
        this.labels.put(set.getName(), set);
    }

    public void clear() {
        this.labels.clear();
    }

    public Set getLabel(String label) {
        return labels.get(label);
    }

    public float membership(String labelName, float scalar) {
        Set fs = labels.get(labelName);
        return fs.membership(scalar);
    }

    public String getName() {
        return name;
    }

    public float getStart() {
        return start;
    }

    public float getEnd() {
        return end;
    }

    public float getInput() {
        return input;
    }

    public void setInput(float scalar) {
        input = scalar;
    }
}
