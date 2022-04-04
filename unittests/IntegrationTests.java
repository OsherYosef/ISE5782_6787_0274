import geometries.*;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;

import java.sql.PreparedStatement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration Tests to check if camera works for camera and geometrical objects
 *
 * @auther Dov and Osher
 */
class IntegrationTests {
    /**
     * This function returns the number of intersections point between a camera and a geometrical shape
     *
     * @param c     Camera
     * @param shape Geometrical shape
     * @return number of intersection point between the camera and geometrical shape
     */
    int sumOfIntersections(Camera c, Intersectable shape) {
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = shape.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        return sum;
    }

    /**
     * Integration Test method for camera interaction with Triangle
     */
    @Test
    void TriangleCameraTest() {
        Camera c = new Camera(new Point(0, 0, 1), new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPSize(3, 3);
        c.setVPDistance(1);

        //TC01: Triangle is in front of the camera
        Triangle t1 = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(sumOfIntersections(c, t1), 1);

        //TC02: Triangle is in front of camera but bigger than the view plane
        Triangle t2 = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(sumOfIntersections(c, t2), 2);
    }

    /**
     * Integration Test method for camera interaction with sphere
     */
    @Test
    void SphereCameraTests() {
        //TC01: Sphere is in front of the camera
        Camera c = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(1, 0, 0)).setVPSize(3, 3).setVPDistance(1);
        Sphere s1 = new Sphere(new Point(0, 0, -3), 1);
        assertEquals(2, sumOfIntersections(c, s1), "Error in TC01");

        //TC02: Sphere is on the view plane
        c = new Camera(new Point(0, 0, 0.5), new Vector(0, 0, -1), new Vector(1, 0, 0)).setVPDistance(1).setVPSize(3, 3);
        Sphere s2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        assertEquals(18, sumOfIntersections(c, s2), "Error in TC02");

        //TC03: Sphere is on the view plane but smaller
        Sphere s3 = new Sphere(new Point(0, 0, -2), 2);
        assertEquals(10, sumOfIntersections(c, s3), "Error in TC03");

        //TC04: view plane is inside the sphere
        c = new Camera(new Point(0, 0, 1), new Vector(0, 0, -1), new Vector(1, 0, 0)).setVPSize(3, 3).setVPDistance(1);
        ;
        Sphere s4 = new Sphere(new Point(0, 0, 0), 4);
        assertEquals(9, sumOfIntersections(c, s4), "Error in TC04");

        //TC05: sphere is before the camera
        c = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(1, 0, 0)).setVPDistance(1).setVPSize(3, 3);
        ;
        Sphere s5 = new Sphere(new Point(0, 0, 5), 0.5);
        assertEquals(0, sumOfIntersections(c, s5), "Error in TC05");

    }

    /**
     * Integration Test method for camera interaction with Plane
     */
    @Test
    void PlaneCameraTests() {
        Camera c = new Camera(new Point(1, 0, 0), new Vector(-1, 0, 0), new Vector(0, 1, 0)).setVPDistance(1).setVPSize(3, 3);

        //TC01: plane is in front of the camera
        Plane p1 = new Plane(new Vector(1, 0, 0), new Point(-2, 0, 0));
        assertEquals(9, sumOfIntersections(c, p1));

        //TC02: plane is in front of the camera but
        Plane p2 = new Plane(new Vector(1, 0, 1), new Point(-2, 0, 0));
        assertEquals(6, sumOfIntersections(c, p2));
    }
}