/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.core;

/**
 * Helps to implement operator overloading
 * @author jgardona
 * @param <T>
 */
public interface Algebraic<T> {

    T plus(T algebraic);

    T minus(T algebraic);

    T times(T algebraic);

    T times(float scalar);

    T divide(T algebraic);

    T divide(float scalar);
}
