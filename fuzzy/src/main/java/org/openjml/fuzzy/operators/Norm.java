/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.fuzzy.operators;

/**
 *
 * @author jgardona
 */
public interface Norm {
    float evaluate(float membarshipA, float membershipB);
}
