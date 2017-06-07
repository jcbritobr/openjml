/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openjml.fuzzy.functions;

import org.openjml.core.Point;

/**
 *  This class represents a piecewise linear function
 * @author jgardona
 */
public class PiecewiseLinearFunction implements Function {

    protected Point[] points;
    
    protected PiecewiseLinearFunction() {
        points = null;
    }

    public PiecewiseLinearFunction(Point[] points) {
        this.points = points.clone();

        for (int i = 0; i < points.length; i++) {
            if ((points[i].getY() < 0) || (points[i].getY() > 1)) {
                throw new RuntimeException("Y value of points must be in the range of [0, 1]");
            }

            if (i == 0) {
                continue;
            }

            if (points[i - 1].getX() > points[i].getX()) {
                throw new RuntimeException("Points must be in crescent order on X axis");
            }
        }
    }

    @Override
    public float membership(float scalar) {
        if (points.length == 0) {
            return 0.0F;
        }

        if (scalar < points[0].getX()) {
            return points[0].getY();
        }

        for (int i = 1; i < points.length; i++) {
            if (scalar < points[i].getX()) {
                float y1 = points[i].getY();
                float y0 = points[i - 1].getY();
                float x1 = points[i].getX();
                float x0 = points[i - 1].getX();

                float m = (y1 - y0) / (x1 - x0);

                return m * (scalar - x0) + y0;
            }
        }
        return points[points.length - 1].getY();
    }

    @Override
    public float leftLimit() {
        return points[0].getX();
    }

    @Override
    public float rightLimit() {
        return points[points.length - 1].getX();
    }

}
