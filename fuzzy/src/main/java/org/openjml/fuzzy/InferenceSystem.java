package org.openjml.fuzzy;

import org.openjml.fuzzy.defuzzifiers.Defuzzifier;
import org.openjml.fuzzy.operators.CoNorm;
import org.openjml.fuzzy.operators.MaximumCoNorm;
import org.openjml.fuzzy.operators.MinimumNorm;
import org.openjml.fuzzy.operators.Norm;

/**
 * This class represents a fuzzy inference system.
 * Created by jgardona on 16/05/17.
 */
public class InferenceSystem {
    private Database database;
    private Rulebase rulebase;
    private Defuzzifier defuzzifier;
    private Norm normOperator;
    private CoNorm coNormOperator;

    public InferenceSystem(Database database, Defuzzifier defuzzifier, Norm normOperator, CoNorm coNormOperator) {
        this.database = database;
        this.rulebase = new Rulebase();
        this.defuzzifier = defuzzifier;
        this.normOperator = normOperator;
        this.coNormOperator = coNormOperator;
    }

    public  InferenceSystem(Database database, Defuzzifier defuzzifier) {
        this(database, defuzzifier, new MinimumNorm(), new MaximumCoNorm());
    }

    public Rule newRule(String name, String rule) {
        Rule r = new Rule(database, name, rule, normOperator, coNormOperator);
        rulebase.add(r);
        return r;
    }

    public void setInput(String variableName, float scalar) {
        database.getVariable(variableName).setInput(scalar);
    }

    public LinguisticVariable getLinguisticVariable(String variableName) {
        return database.getVariable(variableName);
    }

    public Rule getRule(String ruleName) {
        return rulebase.rule(ruleName);
    }

    public float evaluate(String variableName) {
        Output output =  executeInference(variableName);
        float res = defuzzifier.defuzzify(output, normOperator);
        return res;
    }

    public Output executeInference(String variableName) {
        LinguisticVariable linguisticVariable = database.getVariable(variableName);

        Output output = new Output(linguisticVariable);
        Rule[] rules = rulebase.rules();

        for (Rule rule: rules) {
            if(rule.getOutput().getVariable().getName().equals(variableName)){
                String labelName = rule.getOutput().getLabel().getName();
                float firingStrength = rule.evaluateFiringStrength();

                if (firingStrength > 0) {
                    output.add(labelName, firingStrength);
                }
            }
        }

        return output;
    }
}
