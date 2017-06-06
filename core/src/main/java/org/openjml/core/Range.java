/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.core;

/**
 *
 * @author jgardona
 */
public class Range implements Algebraic<Range>{
    private final float min;
    private final float max;

    public Range(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }
    
    public float length() {
        return max - min;
    }
    
    public boolean isInside(float scalar) {
        return ((scalar >= min) && (scalar <= max));
    }
    
    public boolean isInside(Range another) {
        return ((isInside(another.min)) && (isInside(another.max)));
    }
    
    public boolean isOverlapping(Range another) {
        return ((isInside(another.min)) || (isInside(another.max)) ||
                (another.isInside(min)) || (another.isInside(max)));
    }

    @Override
    public Range plus(Range algebraic) {
        return new Range(
                min + algebraic.min,
                max + algebraic.max
        );
    }

    @Override
    public Range minus(Range algebraic) {
        return new Range(
                min - algebraic.min,
                max - algebraic.max
        );
    }

    @Override
    public Range times(Range algebraic) {
        return new Range(
                min * algebraic.min,
                max * algebraic.max
        );
    }

    @Override
    public Range times(float scalar) {
        return new Range(
                min * scalar,
                max * scalar
        );
    }

    @Override
    public Range divide(Range algebraic) {
        return new Range(
                min / algebraic.min,
                max / algebraic.max
        );
    }

    @Override
    public Range divide(float scalar) {
        return new Range(
                min / scalar,
                max / scalar
        );
    }
}
