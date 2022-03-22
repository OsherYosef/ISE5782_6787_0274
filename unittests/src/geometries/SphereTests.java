package src.geometries;

import src.primitives.*;
import org.junit.jupiter.api.Test;

import java.awt.geom.PathIterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods for class sphere
 *
 * @auther Osher and Dov
 */
class SphereTests {
    /**
     * Test method for {@link src.geometries.Sphere#getNormal}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Sphere s1 = new Sphere(new Point(0.0, 0.0, 0.0), 1.0);
        assertEquals(new Vector(1d, 0d, 0d), s1.getNormal(new Point(1.0, 0.0, 0.0)));
    }

    /**
     * Test method for {@link src.geometries.Sphere#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(1.0, 0.0, 0.0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 1, 0), new Point(-1, 0, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        assertEquals(2, sphere.findIntersections(new Ray(new Vector(0d, 0d, -1d), new Point(1d, 0.5, 1d))).size(),
                "Ray goes through but doesn't intersects twice");
        Point p1 = new Point(1, 0.5, 0.8660254037844386);
        Point p2 = new Point(1, 0.5, -0.8660254037844386);
        assertEquals(sphere.findIntersections(new Ray(new Vector(0d, 0d, -1d), new Point(1d, 0.5, 1d))), List.of(p1, p2),
                "Not expected points");

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals(1, sphere.findIntersections(new Ray(new Vector(1d, 0d, 0d), new Point(1d, 0d, 0.5))).size(),
                "Ray starts inside but doesn't intersects exactly once");
        assertEquals(sphere.findIntersections(new Ray(new Vector(1d, 0d, 0d), new Point(1d, 0d, 0.5))), List.of(new Point(1.8660254037844386, 0, 0.5)),
                "Not expected points");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 1, 1), new Point(3, 0, 0))));

        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals(1, sphere.findIntersections(new Ray(new Vector(-1, 0, -1), new Point(1, 0, 1))).size(),
                "Ray starts on the sphere and goes inside but doesn't intersects exactly once");
        assertEquals(sphere.findIntersections(new Ray(new Vector(-1, 0, -1), new Point(1, 0, 1))), List.of(new Point(0, 0, 0)));

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 2, 3), new Point(1, 0, 1))),
                "Ray starts on the sphere and goes outside but intersects");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        assertEquals(2, sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(-1, 0, 0))).size(),
                "Ray starts before the sphere and doesn't intersects twice");
        p1 = new Point(0, 0, 0);
        p2 = new Point(2, 0, 0);
        assertEquals(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(-1, 0, 0))), List.of(p1, p2));

        // TC14: Ray starts at sphere and goes inside (1 points)
        var result = sphere.findIntersections(new Ray(new Vector(-1, 0, 0), new Point(2, 0, 0)));
        assertEquals(1, result.size(), "Ray on the sphere and goes inside and through the middle but doesn't intersects exactly once");
        assertEquals(result, List.of(new Point(0, 0, 0)));

        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(0.5, 0, 0)));
        assertEquals(1, result.size(), "Ray starts inside the sphere through the middle but doesn't intersects exactly once ");
        assertEquals(result, List.of(new Point(2, 0, 0)));

        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Vector(0, 0, 1), new Point(1, 0, 0)));
        assertEquals(1, result.size(), "Ray starts on the center but doesn't intersects exactly once");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(2, 0, 0))),
                "Ray starts on the sphere and goes outside but intersects");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(3, 0, 0))),
                "Ray starts after the sphere and goes outside but intersects");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(0, 0, 1))),
                " Ray starts before the tangent point but intersects");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(1, 0, 1))),
                " Ray starts on the tangent point but intersects");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(2, 0, 1))),
                " Ray starts after the tangent point but intersects");
        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Vector(1, 0, 0), new Point(1, 0, 2))),
                " Ray's line is outside, ray is orthogonal to ray start to sphere's center line but intersects");
    }
}