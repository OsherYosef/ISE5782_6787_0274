package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {
        Ray testRay = new Ray(new Vector(1d, 0d, 0d), new Point(1d, 1d, 1d));
        // =============== Boundary Values Tests ==================
        //TC01: test for empty collection
        Geometries g0 = new Geometries();
        assertNull(g0.findIntersections(testRay), "Empty collection intersects with a ray");

        //TC02: test for no intersection between the ray and any shape in the collection
        Triangle t1 = new Triangle(new Point(1d, 0d, 0d), new Point(0d, 2d, 0d), new Point(1d, 2d, 0d));
        Sphere s1 = new Sphere(new Point(1d, 0d, 0d), 0.5);
        Plane p1 = new Plane(new Vector(0d, 0d, -1d), new Point(2d, 0d, 0d));
        Geometries g1 = new Geometries(p1, s1, t1);
        assertNull(g1.findIntersections(testRay), "Collection intersects with a ray that it is not suppose to");

        //TC03: test for 1 intersection: only one of the shapes
        Sphere s2 = new Sphere(new Point(2d, 1d, 2d), 2d);
        Geometries g2 = new Geometries(t1, p1, s2);
        assertEquals(1, g2.findIntersections(testRay).size());

        //TC04: test for all the shapes intersecting with the Ray
        Tube t2 = new Tube(2d, new Ray(new Vector(0d, 1d, 2d), new Point(2d, 0d, 0d)));
        Geometries g3=new Geometries(s2,t2);
        assertEquals(g3.findIntersections(testRay).size(),2,3);
        // =============== Boundary Values Tests ==================
        //TC11: some of the shapes intersect with the ray
        Geometries g4= new Geometries(s2,t2,t1,s1);
        assertEquals(g4.findIntersections(testRay).size(),2,3);
    }
}