package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

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
     * Test method for {@link src.geometries.Plane#getNormal}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p1 = new Plane(new Point(1.0, 0.0, 0.0), new Point(0.0, 0.0, 0.0), new Point(0.0, 2.0, 0.0));
        assertEquals(1.0, p1.getNormal().length(), "Normal vector length not 1");
    }
}