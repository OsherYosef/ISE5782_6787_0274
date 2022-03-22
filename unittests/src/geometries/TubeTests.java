package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.*;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods for class Tube
 *
 * @auther Osher and Dov
 */
class TubeTests {

    /**
     * Test method for {@link src.geometries.Tube#getNormal}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: simple test for tube

        Tube t1 = new Tube(1.0, new Ray(new Vector(1.0, 0.0, 0.0), new Point(1.0, 0.0, 0.0)));
        assertEquals(1.0, t1.getNormal(new Point(2.0, 1.0, 0.0)).length());
        // =============== Boundary Values Tests ==================
        //TC02: the given point creates 90 degrees with the tube point

        assertThrows(IllegalArgumentException.class, () -> t1.getNormal(new Point(1.0, 1.0, 0.0)).length());
    }

    /**
     * Test method for {@link src.geometries.Tube#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        //TODO bonus
    }
}