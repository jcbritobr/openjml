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
public class Point implements Algebraic<Point> {

    private final float x;
    private final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float distanceTo(Point another) {
        float dx = x - another.x;
        float dy = y - another.y;

        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    public float squaredDistanceTo(Point another) {
        float dx = x - another.x;
        float dy = y - another.y;

        return dx * dx + dy * dy;
    }
    
    public float euclideanNorm() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public Point plus(Point algebraic) {
        return new Point(
                x + algebraic.x,
                y + algebraic.y
        );
    }

    @Override
    public Point minus(Point algebraic) {
        return new Point(
                x - algebraic.x,
                y - algebraic.y
        );
    }

    @Override
    public Point times(Point algebraic) {
        return new Point(
                x * algebraic.x,
                y * algebraic.y
        );
    }

    @Override
    public Point times(float scalar) {
        return new Point(
                x * scalar,
                y * scalar
        );
    }

    @Override
    public Point divide(Point algebraic) {
        return new Point(
                x / algebraic.x,
                y / algebraic.y
        );
    }

    @Override
    public Point divide(float scalar) {
        return new Point(
                x / scalar,
                y / scalar
        );
    }
}
