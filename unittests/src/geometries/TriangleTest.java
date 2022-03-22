package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void findIntersections() {

        // ============ Equivalence Partitions Tests ==============

        //TC01: ray in inside the triangle
        Vector v = new Vector(0d, 0d, -1d);
        Triangle t1 = new Triangle(new Point(2d, 0d, 0d), new Point(0d, 2d, 0d), new Point(2d, 2d, 0d));
        Ray r1 = new Ray(v, new Point(1.5, 1.5, 1d));
        assertEquals(t1.findIntersections(r1).size(), 1, "Bad intersection");

        //TC02: ray is outside: against edge
        Ray r2 = new Ray(v, new Point(3d, 3d, 1d));
        assertNull(t1.findIntersections(r2), "Bad intersection");

        //TC03: ray is outside: against vertex
        Ray r3 = new Ray(v, new Point(0.5, 0.5, 1d));
        assertNull(t1.findIntersections(r3), "Bad intersection");

        // =============== Boundary Values Tests ==================

        //TC11: ray is On the triangle edge(o points)
        Ray r4 = new Ray(v, new Point(2d, 2d, 1d));
        assertNull(t1.findIntersections(r4), "Bad intersection");

        //TC12: ray is in vertex
        Ray r5 = new Ray(v, new Point(2d, 1d, 1d));
        assertNull(t1.findIntersections(r5), "Bad intersection");

        //TC13: ray is on edge's continuation
        Ray r6 = new Ray(v, new Point(2d, 3d, 1d));
        assertNull(t1.findIntersections(r6), "Bad intersection");

    }


}