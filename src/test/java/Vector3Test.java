import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    Vector3 vector3_1 = new Vector3(1, -5, 0);
    Vector3 vector3_2 = new Vector3(7, 2, -1);

    @Test
    void plusVectors() {
        Vector3 resVector3 = vector3_1.plus(vector3_2);

        assertEquals(8, resVector3.getX());
        assertEquals(-3, resVector3.getY());
        assertEquals(-1, resVector3.getZ());
    }

    @Test
    void minusVectors() {
        Vector3 resVector3 = vector3_1.minus(vector3_2);

        assertEquals(-6, resVector3.getX());
        assertEquals(-7, resVector3.getY());
        assertEquals(1, resVector3.getZ());
    }

    @Test
    void multiplyVectors() {
        Vector3 resVector3 = vector3_1.multiply(vector3_2);

        assertEquals(7, resVector3.getX());
        assertEquals(-10, resVector3.getY());
        assertEquals(-0.0, resVector3.getZ());
    }

    @Test
    void multiplyByCoefficient() {
        Vector3 resVector3 = vector3_1.multiply(-2);

        assertEquals(-2, resVector3.getX());
        assertEquals(10, resVector3.getY());
        assertEquals(-0.0, resVector3.getZ());
    }

    @Test
    void divideByCoefficient() {
        Vector3 resVector3 = vector3_1.divide(-2);

        assertEquals(-0.5, resVector3.getX());
        assertEquals(2.5, resVector3.getY());
        assertEquals(-0.0, resVector3.getZ());
    }

    @Test
    void dot() {
        double res = vector3_1.dot(vector3_2);

        assertEquals(-3, res);
    }

    @Test
    void cross() {
        Vector3 resVector3 = vector3_1.cross(vector3_2);

        assertEquals(5, resVector3.getX());
        assertEquals(1, resVector3.getY());
        assertEquals(37, resVector3.getZ());
    }

    @Test
    void length() {
        assertEquals(Math.sqrt(26), vector3_1.length());
        assertEquals(Math.sqrt(54), vector3_2.length());
    }
}