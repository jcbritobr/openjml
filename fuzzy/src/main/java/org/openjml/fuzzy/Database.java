package org.openjml.fuzzy;

import java.util.HashMap;

/**
 * This class represents a container to store linguistic
 * variables
 * Created by jgardona on 15/05/17.
 */
public class Database {
    private HashMap<String, LinguisticVariable> variables;

    public Database() {
        variables = new HashMap<>(10);
    }

    public void add(LinguisticVariable variable) {
        variables.put(variable.getName(), variable);
    }

    public void clear() {
        variables.clear();
    }

    public LinguisticVariable getVariable(String variableName) {
        return variables.get(variableName);
    }
}
