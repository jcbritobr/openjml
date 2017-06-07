package org.openjml.fuzzy;

import java.util.HashMap;

/**
 * This class represents a fuzzy rule.
 * Created by jgardona on 15/05/17.
 */
public class Rulebase {
    private HashMap<String, Rule> rules;

    public Rulebase() {
        rules = new HashMap<>(20);
    }

    public void add(Rule rule) {
        rules.put(rule.getName(), rule);
    }

    public void clear() {
        rules.clear();
    }

    public Rule rule(String ruleName) {
        return rules.get(ruleName);
    }

    public Rule[] rules() {
        return rules.values().toArray(new Rule[rules.values().size()]);
    }
}
