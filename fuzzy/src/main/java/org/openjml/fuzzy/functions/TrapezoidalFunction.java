/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.fuzzy.functions;

import org.openjml.core.Point;

/**
 * @author jgardona
 */
public class TrapezoidalFunction extends PiecewiseLinearFunction {

    private TrapezoidalFunction(int size) {
        points = new Point[size];
    }

    public TrapezoidalFunction(float m1, float m2, float m3, float m4, float max, float min) {
        this(4);
        points[0] = new Point(m1, min);
        points[1] = new Point(m2, max);
        points[2] = new Point(m3, max);
        points[3] = new Point(m4, min);
    }

    public TrapezoidalFunction(float m1, float m2, float m3, float m4) {
        this(m1, m2, m3, m4, 1.0F, 0.0F);
    }

    public TrapezoidalFunction(float m1, float m2, float m3, float max, float min) {
        this(3);
        points[0] = new Point(m1, min);
        points[1] = new Point(m2, max);
        points[2] = new Point(m3, min);
    }

    public TrapezoidalFunction(float m1, float m2, float m3) {
        this(m1, m2, m3, 1.0F, 0.0F);
    }

    public TrapezoidalFunction(float m1, float m2, float max, float min, EdgeType edge) {
        this(2);
        if (edge == EdgeType.LEFT) {
            points[0] = new Point(m1, min);
            points[1] = new Point(m2, max);
        } else if (edge == EdgeType.RIGHT) {
            points[0] = new Point(m1, max);
            points[1] = new Point(m2, min);
        }
    }

    public TrapezoidalFunction(float m1, float m2, EdgeType edge) {
        this(m1, m2, 1.0F, 0.0F, edge);
    }
}
