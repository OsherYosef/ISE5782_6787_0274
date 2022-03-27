package geometries;

import org.junit.jupiter.api.Test;
import geometries.Tube;
import primitives.*;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods for class Tube
 *
 * @auther Osher and Dov
 */
class TubeTests {

    /**
     * Test method for {@link geometries.Tube#getNormal}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test for tube
        Tube t1 = new Tube(1.0, new Ray(new Vector(1.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0)));
        assertEquals(t1.getNormal(new Point(2.0, 1.0, 0.0)), new Vector(0, 1, 0));

        // =============== Boundary Values Tests ==================
        //TC02: the given point creates 90 degrees with the tube point
        assertEquals(t1.getNormal(new Point(1, 1, 0)), new Vector(0, 1, 0));
    }

    /**
     * Test method for {@link geometries.Tube#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        //TODO bonus
    }
}