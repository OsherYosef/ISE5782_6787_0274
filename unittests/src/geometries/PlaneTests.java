package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.Point;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    /**
     * Test method for {@link src.geometries.Plane#getNormal}
     */
    @Test
    void getNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p1=new Plane(new Point(1.0,0.0,0.0),new Point(0.0,0.0,0.0),new Point(0.0,2.0,0.0));
        assertEquals(1.0,p1.getNormal().length(),"Normal vector length not 1");
    }
}