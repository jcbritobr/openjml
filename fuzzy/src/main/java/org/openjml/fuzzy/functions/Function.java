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
public interface Function {

    float membership(float scalar);

    float leftLimit();

    float rightLimit();
}
