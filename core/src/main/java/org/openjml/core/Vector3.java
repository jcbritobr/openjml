package org.openjml.core;

/**
 * This class represents a vector with 3 dimensions
 * Created by jgardona on 30/05/17.
 */
public class Vector3 implements Algebraic<Vector3> {

    private float x;
    private float y;
    private float z;

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(float scalar) {
        x = y = z = scalar;
    }

    public float max() {
        return x > y ? ((x > z) ? x : z) : ((y > z) ? y : z);
    }

    public float min() {
        return x < y ? ((x < z) ? x : z) : ((y < z) ? y : z);
    }

    public int maxIndex() {
        return x >= y ? ((x <= z) ? 0 : 2) : ((y >= z) ? 1 : 2);
    }

    public int minIndex() {
        return x <= y ? ((x <= z) ? 0 : 2) : ((y <= z) ? 1 : 2);
    }

    public float norm() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public float square() {
        return x * x + y * y + z * z;
    }

    public Vector3 inverse() {
        return new Vector3(
                x == 0 ? 0 : 1.0f / x,
                y == 0 ? 0 : 1.0f / y,
                z == 0 ? 0 : 1.0f / z
        );
    }

    public float normalize() {
        float norm = (float) Math.sqrt(x * x + y * y + z * z);
        float inverseNorm = 1.0f / norm;
        x *= inverseNorm;
        y *= inverseNorm;
        z *= inverseNorm;

        return norm;
    }

    public Vector3 abs() {
        return new Vector3(
                Math.abs(x),
                Math.abs(y),
                Math.abs(z)
        );
    }

    public static Vector3 cross(Vector3 vectorA, Vector3 vectorB) {
        return new Vector3(
                vectorA.y * vectorB.z - vectorA.z * vectorB.y,
                vectorA.z * vectorB.x - vectorA.x * vectorB.z,
                vectorA.x * vectorB.y - vectorA.y * vectorB.x
        );
    }

    public static float dot(Vector3 vectorA, Vector3 vectorB) {
        return vectorA.x * vectorB.x + vectorA.y * vectorB.y + vectorA.z * vectorB.z;
    }

    @Override
    public String toString() {
        return String.format("{%.02f} {%.02f} {%.02f}", x, y, z);
    }

    @Override
    public Vector3 plus(Vector3 algebraic) {
        return new Vector3(
                x + algebraic.x,
                y + algebraic.y,
                z + algebraic.z
        );
    }

    @Override
    public Vector3 minus(Vector3 algebraic) {
        return new Vector3(
                x - algebraic.x,
                y - algebraic.y,
                z - algebraic.z
        );
    }

    @Override
    public Vector3 times(Vector3 algebraic) {
        return new Vector3(
                x * algebraic.x,
                y * algebraic.y,
                z * algebraic.z
        );
    }

    @Override
    public Vector3 times(float scalar) {
        return new Vector3(
                x * scalar,
                y * scalar,
                z * scalar
        );
    }

    @Override
    public Vector3 divide(Vector3 algebraic) {
        return new Vector3(
                x / algebraic.x,
                y / algebraic.y,
                z / algebraic.z
        );
    }

    @Override
    public Vector3 divide(float scalar) {
        return new Vector3(
                x / scalar,
                y / scalar,
                z / scalar
        );
    }
}
