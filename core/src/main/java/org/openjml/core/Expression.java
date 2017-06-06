/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.core;

import java.util.Stack;

/**
 *
 * @author jgardona
 */
public final class Expression {

    private Expression() {
    }

    public float evaluateExpression(String expression, float[] variables) {
        String[] tokens = expression.trim().split(" ");
        Stack<Float> arguments = new Stack<>();

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                arguments.push(Float.parseFloat(token));
            } else if (token.charAt(0) == '$') {
                arguments.push(variables[Integer.parseInt(token.substring(1))]);
            } else {
                float v = arguments.pop();

                switch (token) {
                    case "+":
                        arguments.push(arguments.pop() + v);
                        break;
                    case "-":
                        arguments.push(arguments.pop() - v);
                        break;
                    case "*":
                        arguments.push(arguments.pop() * v);
                        break;
                    case "/":
                        arguments.push(arguments.pop() / v);
                        break;
                    case "sin":
                        arguments.push((float) Math.sin(v));
                        break;
                    case "cos":
                        arguments.push((float) Math.cos(v));
                        break;
                    case "ln":
                        arguments.push((float) Math.log(v));
                        break;
                    case "exp":
                        arguments.push((float) Math.exp(v));
                        break;
                    case "sqrt":
                        arguments.push((float) Math.sqrt(v));
                        break;
                    default:
                        throw new RuntimeException("Unsupported function: " + token);
                }
            }
        }

        if (arguments.size() != 1) {
            throw new RuntimeException("Incorrect expression");
        }

        return arguments.pop();
    }
}
