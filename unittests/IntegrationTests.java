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
     * Integration Test method for camera interaction with Triangle
     */
    @Test
    void TriangleCameraTest() {
        //TC01: Triangle is in front of the camera
        Triangle t1 = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        Camera c = new Camera(0, 0, 1, new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPSize(3, 3);
        c.setVPDistance(1);
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = t1.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(sum, 1);

        //TC02: Triangle is in front of camera but bigger than the view plane
        Triangle t2 = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = t2.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(sum, 2);

    }

    /**
     * Integration Test method for camera interaction with sphere
     */
    @Test
    void SphereCameraTests() {
        //TC01: Sphere is in front of the camera
        Camera c = new Camera(0, 0, 0, new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPSize(3, 3);
        c.setVPDistance(1);
        Sphere s1 = new Sphere(new Point(0, 0, -3), 1);
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = s1.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(2, sum, "Error in TC01");

        //TC02: Sphere is on the view plane
        c = new Camera(0, 0, 0.5, new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPDistance(1);
        c.setVPSize(3, 3);
        Sphere s2 = new Sphere(new Point(0, 0, -2.5), 2.5);
        sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = s2.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(18, sum, "Error in TC02");

        //TC03: Sphere is on the view plane but smaller
        c = new Camera(0, 0, 0.5, new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPDistance(1);
        c.setVPSize(3, 3);
        sum = 0;
        Sphere s3 = new Sphere(new Point(0, 0, -2), 2);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = s3.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(sum, 10, "Error in TC03");

        //TC04: view plane is inside the sphere
        c = new Camera(0, 0, 1, new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPSize(3, 3);
        c.setVPDistance(1);
        Sphere s4 = new Sphere(new Point(0, 0, 0), 4);
        sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = s4.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(sum, 9, "Error in TC04");

        //TC05: sphere is before the camera
        c = new Camera(0, 0, 0, new Vector(0, 0, -1), new Vector(1, 0, 0));
        c.setVPDistance(1);
        c.setVPSize(3, 3);
        sum = 0;
        Sphere s5 = new Sphere(new Point(0, 0, 5), 0.5);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = s5.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(0, sum, "Error in TC05");

    }

    /**
     * Integration Test method for camera interaction with Plane
     */
    @Test
    void PlaneCameraTests() {
        Camera c = new Camera(1, 0, 0, new Vector(-1, 0, 0), new Vector(0, 1, 0));
        c.setVPDistance(1);
        c.setVPSize(3, 3);
        //TC01: plane is in front of the camera
        Plane p1 = new Plane(new Vector(1, 0, 0), new Point(-2, 0, 0));
        int sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = p1.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(9, sum);

        //TC02: plane is in front of the camera but
        c = new Camera(1, 0, 0, new Vector(-1, 0, 0), new Vector(0, 1, 0));
        c.setVPDistance(1);
        c.setVPSize(3, 3);
        Plane p2 = new Plane(new Vector(1, 0, 1), new Point(-2, 0, 0));
        sum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                List<Point> result = p2.findIntersections(c.constructRay(3, 3, j, i));
                if (result != null)
                    sum += result.size();
            }
        }
        assertEquals(6, sum);
    }
}