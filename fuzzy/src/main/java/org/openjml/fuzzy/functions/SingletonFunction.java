/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.fuzzy.functions;

/**
 *
 * @author jgardona
 */
public class SingletonFunction implements Function {

    protected float support;

    public SingletonFunction(float support) {
        this.support = support;
    }

    @Override
    public float membership(float scalar) {
        return support == scalar ? 1 : 0;
    }

    @Override
    public float leftLimit() {
        return support;
    }

    @Override
    public float rightLimit() {
        return support;
    }
}
