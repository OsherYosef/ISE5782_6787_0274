package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {

        // =============== Boundary Values Tests ==================
        //TC01: test for empty collection
        Geometries g0=new Geometries();
        assertEquals(0,0);
        //TC02: test for no intersection between the ray and any shape in the collection
        Geometries g1 = new Geometries();
        assertNull(g1.findIntersections(new Ray(new Vector(1d, 0d, 0d), new Point(0.5d, 0d, 0d))));
        //TC03: test for 1 intersection: only one of the shapes

        //TC04: test for all the shapes intersecting with the Ray

        // =============== Boundary Values Tests ==================
        //TC11: some of the shapes intersect with the ray

    }
}