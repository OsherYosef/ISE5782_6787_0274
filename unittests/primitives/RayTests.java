package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing class Ray
 */
class RayTests {

    /**
     * Test method for {@link Ray#getPoint(double)}
     */
    @Test
    void getPoint() {
        Ray testRay = new Ray(new Vector(1, 0, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
        //TC01: t>0
        assertEquals(new Point(2, 0, 1), testRay.getPoint(2));

        //TC02: t<0
        assertEquals(new Point(-3, 0, 1), testRay.getPoint(-3));

        // =============== Boundary Values Tests ==================
        //TC03: T=0
        assertEquals(new Point(0, 0, 1), testRay.getPoint(0));

    }

    /**
     * Test method for {@link Ray#findClosestPoint(List)}
     */
    @Test
    void testFindClosestPoint() {
        Ray testRay = new Ray(new Vector(1, 0, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
        //TC01: closest point is in the middle of the list
        assertEquals(testRay.findClosestPoint(List.of(new Point(0, 2, 10), new Point(3, 1, 4), new Point(0, 0, 2), new Point(10, 0, 0))), new Point(0, 0, 2),
                "Test 1 failed");

        // =============== Boundary Values Tests ==================
        //TC02: empty list
        assertNull(testRay.findClosestPoint(List.of()), "Test 2 failed");

        //TC03: 1 Point in the list
        assertEquals(new Point(1, 2, 3), testRay.findClosestPoint(List.of(new Point(1, 2, 3))), "Test 3 failed");

        //TC04: the closest point is the first point of the list
        assertEquals(new Point(0, 0, 2), testRay.findClosestPoint(List.of(new Point(0, 0, 2), new Point(2, 2, 2), new Point(3, 3, 3), new Point(-10, -2, 0))),
                "Test 4 failed");

        //TC05: the closest point is the last point of the list
        assertEquals(new Point(0, 0, 2), testRay.findClosestPoint(List.of(new Point(-12, 4, 7), new Point(-7, 10, 0), new Point(1, 2, 3), new Point(0, 0, 2))),
                "Test 5 failed");
    }
}