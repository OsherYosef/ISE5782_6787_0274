package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * Class Sphere for the geometrical shape sphere in space
 * sphere is represented by a point in space and radius
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;

    /**
     * Constructor of class sphere, gets the center point and radius of the sphere
     *
     * @param center center point of the Sphere
     * @param radius Radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }


    //**********Getters**********8//

    /**
     * Return the center of the sphere
     *
     * @return Center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Return the Radius of the sphere
     *
     * @return Radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    //********Operations***********//
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        //According to lecture (Part 2 page 33)
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        //if the starting point of the ray is exactly in the middle of the sphere
        if (p0.equals(center)) return List.of(center.add(v.scale(radius)));
        //vector to the center of the sphere, then the scalar and then the distance
        Vector U = center.subtract(p0);
        double tm = alignZero(v.dotProduct(U));
        double d = alignZero(Math.sqrt(U.lengthSquared() - tm * tm));

        if (d >= radius) return null;//no intersections=the direction is above the sphere

        double th = alignZero(Math.sqrt(radius * radius - d * d)); // the distance from p1 to a intersection with d
        double t1 = alignZero(tm - th);//p0 to p1
        double t2 = alignZero(tm + th);//p0 to p2

        if (t1 > 0 && t2 > 0) {
            Point p1 = ray.getPoint(t1);
            Point p2 = ray.getPoint(t2);
            return List.of(p1, p2);
        }
        if (t1 > 0) {
            Point p1 = ray.getPoint(t1);
            return List.of(p1);
        }
        if (t2 > 0) {
            Point p2 = ray.getPoint(t2);
            return List.of(p2);
        }
        return null;
    }
}
