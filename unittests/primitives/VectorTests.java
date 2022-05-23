package primitives;

import org.junit.jupiter.api.Test;
import geometries.Polygon;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Testing vectors
 *
 * @author Osher and Dov
 */
class VectorTests {
    /**
     * Test method for {@link primitives.Vector#Vector}
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test for a vector with 3 double
        assertDoesNotThrow(() -> new Vector(1, 2, 3), "Failed constructing a correct vector");

        // =============== Boundary Values Tests ==================
        //TC02: test constructor for vector 0
        assertThrows(IllegalArgumentException.class, () -> new Vector(0.0, 0.0, 0.0), "Constructor doesn't throw an exception for vector 0");

    }

    /**
     * Test Method for Vector subtraction
     */
    @Test
    void testVectorSubtraction() {
        Vector v = new Vector(2, 3, 4);
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test for subtraction
        assertEquals(new Vector(1, 2, 3), v.subtract(new Point(1, 1, 1)), "subtraction doesn't work");

        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class, () -> v.subtract(new Point(2,3,4)), "Subtraction doesn't throw an exception for vector 0");

    }

    /**
     * Test method for {@link primitives.Vector#dotProduct}
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(1d, 2d, 3d);
        Vector v2 = new Vector(0d, 0d, 4d);

        //TC01: Dot product between 2 vector
        assertEquals(12.0, v1.dotProduct(v2), "dotProduct() result is not as expected");

        //TC02: Dot product between vector and itself
        assertEquals(14.0, v1.dotProduct(v1), "dotProduct() result is not as expected");

        //TC03: Negative result for Dot product
        Vector v4 = new Vector(-2d, 0d, 0d);
        assertEquals(-2.0, v1.dotProduct(v4), "dotProduct() result is not as expected");

        // =============== Boundary Values Tests ==================
        Vector v3 = new Vector(1d, 0d, 0d);

        //TC04:  test Dot product that will return 0
        assertEquals(v2.dotProduct(v3), 0, "dotProduct() result is not 0 for orthogonal vectors");
    }

    /**
     * Test method for {@link primitives.Vector#normalize}
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(6.0, 8.0, 0.0);
        Vector n = v1.normalize();

        //TC01: Test that the length of a normalized vector is 1
        assertEquals(1.0, n.length(), "normalize() result is not as expected");
    }


    /**
     * Test method for {@link primitives.Vector#length}
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(6.0, 8.0, 0.0);

        //TC01: Simple test for length
        assertEquals(10.0, v1.length(), "length() result is not as expected");
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared}
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(6.0, 8.0, 0.0);

        //TC01: Simple test for lengthSquared
        assertEquals(100.0, v1.lengthSquared(), "lengthSquared() result is not as expected");
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct}
     */
    @Test
    void testCrossProduct() {
        Vector v1 = new Vector(1.0, 2.0, 3.0);
        Vector v2 = new Vector(-2.0, -4.0, -6.0);
        Vector v3 = new Vector(0.0, 3.0, -2.0);

        // ============ Equivalence Partitions Tests ==============
        Vector vr = v1.crossProduct(v3);

        //TC01: check for cross product between 2 vector
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
        assertEquals(vr, new Vector(-13, 2, 3), "Not the expected vector");

        //TC02: check dot product between cross product vector (should be 0)
        assertEquals(vr.dotProduct(v1), 0, "Wrong");
        assertEquals(vr.dotProduct(v3), 0, "Wrong");

        // =============== Boundary Values Tests ==================
        //TC11: test for cross product between parallel vectors
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link primitives.Vector#scale}
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(6.0, 8.0, 0.0);

        //TC01: simple test for scale
        assertEquals(v1.scale(2), new Vector(12, 16, 0), "scale() wrong result for scale with natural number");

        //TC02: Scale is a fraction
        assertEquals(v1.scale(0.5), new Vector(3, 4, 0), "scale() wrong result for scale with fraction");

        //TC03: Scale is negative
        assertEquals(v1.scale(-1), new Vector(-6, -8, 0), "scale() wrong result for scale with negative");

        // =============== Boundary Values Tests ==================
        //TC04: Scale is 0, which means it creates a 0 vector
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "scale() doesn't throw an exception for vector 0");
    }

    /**
     * Test method for {@link primitives.Vector#add}
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        Vector v1 = new Vector(6.0, 8.0, 0.0);
        Vector v2 = new Vector(2.0, -4.0, 1.0);

        //TC01: test for two different vectors
        assertEquals(v1.add(v2), new Vector(8, 4, 1), "add() doesn't work for different vectors");

        // =============== Boundary Values Tests ==================
        //TC02: test for two vectors that are opposite to each other
        Vector v3 = new Vector(-6.0, -8.0, 0.0);
        assertThrows(IllegalArgumentException.class, () -> v1.add(v3), "add doesn't throw an exception for vector 0");
    }

    @Test
    void testOrthogonal(){
        //TODO add
    }

}