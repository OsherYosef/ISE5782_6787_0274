package geometries;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods for class plane
 *
 * @auther Osher and Dov
 */
class PlaneTests {
    @Test
    void testConstructor() {
        // =============== Boundary Values Tests ==================
        //TC01: The points are on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1.0, 0.0, 0.0), new Point(2.0, 0.0, 0.0), new Point(3.0, 0.0, 0.0)));
        //TC02: The first 2 point are same
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0), new Point(3.0, 2.0, 0.0)));

    }

    /**
     * Test method for {@link geometries.Plane#getNormal}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p1 = new Plane(new Point(1.0, 0.0, 0.0), new Point(0.0, 0.0, 0.0), new Point(0.0, 2.0, 0.0));
        assertEquals(1.0, p1.getNormal().length(), "Normal vector length not 1");
    }

    /**
     * Test method for {@link geometries.Plane#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Plane testPlane = new Plane(new Point(0, 0, 1), new Point(2, 0, 1), new Point(0, 2, 1));

        // ***Group: Ray is not orthogonal nor parallel to the plane
        // TC01: but it intersects with the plane(1 Point)
        assertEquals(testPlane.findIntersections(new Ray(new Vector(0.5, 0.5, -1), new Point(0, 0, 2))), List.of(new Point(0.5, 0.5, 1)),
                "Doesn't intersects exactly once");

        // TC02: it doesn't intersect with the plane(0 Points)
        assertNull(testPlane.findIntersections(new Ray(new Vector(7, 7, -1), new Point(0, 0, -3))),
                "02 Intersects");


        // =============== Boundary Values Tests ==================
        // **** Group: Ray's is parallel to the plane(0 points)

        //TC11: and is inside the plane
        assertNull(testPlane.findIntersections(new Ray(new Vector(1, 1, 0), new Point(0.5, 0.5, 1))),
                "11 Intersects");
        //TC12: and is outside the plane
        assertNull(testPlane.findIntersections(new Ray(new Vector(1, 1, 0), new Point(0, 0, 2))),
                "12 Intersects");

        // **** Group: Ray's is orthogonal to the plane
        //TC13: and intersects(1 Point)
        assertEquals(testPlane.findIntersections(new Ray(new Vector(0, 0, -1), new Point(0.5, 0.5, 2))), List.of(new Point(0.5, 0.5, 1)),
                "Doesn't intersects exactly once");

        //TC14: before the plane(0 Point)
        assertNull(testPlane.findIntersections(new Ray(new Vector(0, 0, -1), new Point(-1, -1, 1))),
                "14 Intersects");

        //TC15: after the plane (0 Point)
        assertNull(testPlane.findIntersections(new Ray(new Vector(0, 0, -1), new Point(3, 0, 1))),
                "15 Intersects");

        // **** Group: Ray's is on the plane(0 Points) and neither orthogonal nor parallel
        //TC16: and starts in it
        assertNull(testPlane.findIntersections(new Ray(new Vector(1, 1, 1), new Point(0.5, 0.5, 1))),
                "16 Intersects");

        //TC17: and start on one of points
        assertNull(testPlane.findIntersections(new Ray(new Vector(1, 1, 1), new Point(0, 0, 2))),
                "17 Intersects");

    }
}