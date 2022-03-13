package src.geometries;

import src.primitives.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Sphere class
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
        Sphere s1 = new Sphere(new Point(0.0, 0.0, 0.0),1.0 );
        assertEquals(1.0,s1.getNormal(new Point(1.0,0.0,0.0)).length());
    }
}