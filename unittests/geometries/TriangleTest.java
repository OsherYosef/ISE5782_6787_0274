package geometries;

import org.junit.jupiter.api.Test;
import geometries.Triangle;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods for class Triangle
 *
 * @auther Osher and Dov
 */
class TriangleTest {
    /**
     * Test method for {@link Triangle#getNormal(Point)}
     */
    @Test
    void TestGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC1: There is a simple single test here
        Triangle t = new Triangle(
                new Point(1, 1, 1),
                new Point(2, 3, 5),
                new Point(4, 1, 3));
        Vector v = t.getNormal(new Point(1, 1, 1));
        Vector test = new Vector(4, 10, -6);
        assertEquals(test.normalize(), v, "Normal for triangle not as expected");
    }

    /**
     * Test method for {@link Triangle#findIntersections(Ray)}
     */
    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: ray in inside the triangle
        Vector v = new Vector(0, 0, -1);
        Triangle t1 = new Triangle(new Point(2, 0, 1), new Point(0, 2, 1), new Point(0, 0, 1));
        Ray r1 = new Ray(v, new Point(0.5, 0.5, 2));
        assertEquals(t1.findIntersections(r1), List.of(new Point(0.5, 0.5, 1)), "Not expected point");

        //TC02: ray is outside: against edge
        Ray r2 = new Ray(v, new Point(3, 3, 1));
        assertNull(t1.findIntersections(r2), "Intersects outside");

        //TC03: ray is outside: against vertex
        Ray r3 = new Ray(v, new Point(0.5, 0.5, 1));
        assertNull(t1.findIntersections(r3), "Intersects outside");

        // =============== Boundary Values Tests ==================

        //TC11: ray is On the triangle edge
        Ray r4 = new Ray(v, new Point(2, 2, 1));
        assertNull(t1.findIntersections(r4), "Intersects on the edge");

        //TC12: ray is in vertex
        Ray r5 = new Ray(v, new Point(2, 1, 1));
        assertNull(t1.findIntersections(r5), "Intersects in vertex");

        //TC13: ray is on edge's continuation
        Ray r6 = new Ray(v, new Point(2, 3, 1));
        assertNull(t1.findIntersections(r6), "Intersects on edge continuation");

    }


}