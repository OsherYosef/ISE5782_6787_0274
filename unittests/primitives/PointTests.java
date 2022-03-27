package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 *
 * @auther Osher and Dov
 */
public class PointTests {
    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test for add
        Point p1 = new Point(1.0, 2.0, 3.0);
        assertTrue(p1.add(new Vector(1.0, 2.0, 3.0)).equals(new Point(2.0, 4.0, 6.0)), "add doesn't work properly");

        // =============== Boundary Values Tests ==================
        //TC02: test adding a point to a vector and creating a 0 point
        assertTrue(p1.add(new Vector(-1.0, -2.0, -3.0)).equals(new Point(0.0, 0.0, 0.0)), "add doesn't work properly");

    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test for subtract
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(2.0, -2.0, 0.0);
        assertTrue(p1.subtract(p2).equals(new Vector(-1.0, 4.0, 3.0)), "subtract doesn't work properly");

        // =============== Boundary Values Tests ==================
        //TC02: test subtracting a point with itself (creating a vector 0)
        assertThrows(IllegalArgumentException.class, () -> p1.subtract(p1), "subtract doesn't throw an exception for vector 0");

    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(Point)}
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test distance(squared) between 2 point
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(1.0, 2.0, 7.0);
        assertEquals(16.0,p1.distanceSquared(p2),"distanceSquared doesn't work properly");

        // =============== Boundary Values Tests ==================
        //TC02: test distance(squared) between a point and itself
        assertTrue(primitives.Util.isZero(p1.distanceSquared(p1)),"distanceSquared doesn't work properly");
    }

    /**
     * Test method for {@link primitives.Point#distance(Point)}
     */
    @Test
    void distance() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test distance between 2 point
        Point p1 = new Point(1.0, 2.0, 3.0);
        Point p2 = new Point(1.0, 2.0, 7.0);
        assertEquals(4.0,p1.distance(p2),"distance doesn't work properly");

        // =============== Boundary Values Tests ==================
        //TC02: test distance between a point and itself
        assertTrue(primitives.Util.isZero(p1.distance(p1)),"distance doesn't work properly");
    }
}