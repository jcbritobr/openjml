package org.openjml.fuzzy;

import org.openjml.fuzzy.operators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * This class represents a fuzzy Rule
 * Created by jgardona on 15/05/17.
 */
public class Rule {
    private static final String UNARY_OPERATORS = "NOT;VERY";

    private String name;
    private String rule;
    private List<Object> rpnTokenList;
    private Clause output;
    private Database database;
    private Norm normOperator;
    private CoNorm coNormOperator;
    private UnaryOperator notUnaryOperator;

    public Rule(Database database, String name, String rule, Norm normOperator, CoNorm coNormOperator) {
        rpnTokenList = new ArrayList<>();

        this.name = name;
        this.rule = rule;
        this.database = database;
        this.normOperator = normOperator;
        this.coNormOperator = coNormOperator;
        this.notUnaryOperator = new NotUnaryOperator();

        parseRule();
    }

    public Rule(Database database, String name, String rule) {
        this(database, name, rule, new MinimumNorm(), new MaximumCoNorm());
    }

    public String getRpnExpression() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object o : rpnTokenList) {
            if (o instanceof Clause) {
                Clause c = (Clause) o;
                stringBuilder.append(c.toString());
            } else {
                stringBuilder.append(o.toString());
            }
            stringBuilder.append(", ");
        }

        stringBuilder.append('#');
        String result = stringBuilder.toString().replace(", #", "");

        return result;
    }

    private int priority(String operator) {
        if (UNARY_OPERATORS.contains(operator)) {
            return 4;
        }

        switch (operator) {
            case "(":
                return 1;
            case "OR":
                return 2;
            case "AND":
                return 3;
        }

        return 0;
    }

    private void parseRule() {
        boolean consequent = false;

        String upRule = rule.toUpperCase();

        if (!upRule.startsWith("IF")) {
            throw new RuntimeException("A Fuzzy Rule must start with an IF statement");
        }

        if (!upRule.contains("THEN")) {
            throw new RuntimeException("Missing the consequent (THEN) statement");
        }

        String spacedRule = rule.replace("(", " ( ")
                .replace(")", " ) ");
        String[] tokensList = getRUleTokens(spacedRule);

        Stack<String> s = new Stack<>();
        String lastToken = "IF";
        LinguisticVariable lingVar = null;

        for (String tk : tokensList) {

            String token = tk.trim();
            String upToken = token.toUpperCase();

            if (upToken.equals("") || upToken.equals("IF")) {
                continue;
            }

            if (upToken.equals("THEN")) {
                lastToken = upToken;
                consequent = true;
                continue;
            }

            if (lastToken.equals("VAR")) {
                if (upToken.equals("IS")) {
                    lastToken = upToken;
                } else {
                    throw new RuntimeException("An IS statement is expected after a linguistic variable");
                }
            } else if (lastToken.equals("IS")) {
                try {
                    Set set = lingVar.getLabel(token);
                    Clause c = new Clause(lingVar, set);
                    if (consequent) {
                        output = c;
                    } else {
                        rpnTokenList.add(c);
                    }

                    lastToken = "LAB";

                } catch (Exception e) {
                    throw new RuntimeException("Linguistic label " + token + " was not found on the variable " + lingVar.getName() + ".");
                }
            } else {
                if (upToken.equals("(")) {
                    if (consequent) {
                        throw new RuntimeException("Linguistic Variable expected after a THEN statement");
                    }

                    s.push(upToken);
                    lastToken = upToken;
                } else if (upToken.equals("AND") || upToken.equals("OR") || UNARY_OPERATORS.contains(upToken)) {
                    if (consequent) {
                        throw new RuntimeException("Linguistic variable expected after a THEN statement");
                    }

                    while ((s.size() > 0) && (priority(s.peek()) > priority(upToken))) {
                        rpnTokenList.add(s.pop());
                    }

                    s.push(upToken);
                    lastToken = upToken;
                } else if (upToken.equals(")")) {
                    if (consequent) {
                        throw new RuntimeException("Linguistic variable expected after a THEN statement");
                    }

                    if (s.size() == 0) {
                        throw new RuntimeException("Openning parenthesis missing");
                    }

                    while (!s.peek().equals("(")) {
                        rpnTokenList.add(s.pop());
                        if (s.size() == 0) {
                            throw new RuntimeException("Openning parenthesis missing");
                        }
                    }

                    s.pop();
                    lastToken = upToken;
                } else {
                    try {
                        lingVar = database.getVariable(token);
                        lastToken = "VAR";
                    } catch (Exception e) {
                        throw new RuntimeException("Linguistic variable " + token + " was not found on the database");
                    }
                }
            }

        }

        while (s.size() > 0) {
            rpnTokenList.add(s.pop());
        }
    }

    private String[] getRUleTokens(String rule) {
        String[] tokens = rule.split(" ");

        for (int i = 0; i < tokens.length; i++) {

            if ((UNARY_OPERATORS.contains(tokens[i].toUpperCase())) &&
                    (i > 1) && (tokens[i - 1].toUpperCase().equals("IS"))) {
                tokens[i - 1] = tokens[i - 2];
                tokens[i - 2] = tokens[i];
                tokens[i] = "IS";
            }
        }

        return tokens;
    }

    public float evaluateFiringStrength() {
        Stack<Float> s = new Stack<>();

        for (Object o : rpnTokenList) {
            if (o instanceof Clause) {
                Clause c = (Clause) o;
                s.push(c.evaluate());
            } else {
                float y = s.pop();
                float x = 0F;

                if (!UNARY_OPERATORS.contains(o.toString())) {
                    switch (o.toString()) {
                        case "AND":
                            s.push(normOperator.evaluate(x, y));
                            break;
                        case "OR":
                            s.push(coNormOperator.evaluate(x, y));
                            break;
                        case "NOT":
                            s.push(notUnaryOperator.evaluate(y));
                            break;
                        default:
                            throw new RuntimeException("Operator not implemented yet.");
                    }
                }
            }
        }

        return s.pop();
    }

    public String getName() {
        return name;
    }

    public Clause getOutput() {
        return output;
    }
}
