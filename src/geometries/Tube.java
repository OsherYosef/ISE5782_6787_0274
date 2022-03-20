package src.geometries;

import src.primitives.*;

import java.util.List;

/**
 * Class Tube for the geometrical shape Tube in space
 * a Tube will have a radius and a ray(direction)
 */

public class Tube implements Geometry {
    private final double radius;
    private final Ray ray;
    //**************Constructor****************//

    /**
     * Constructor for class Tube using a radius and a ray
     *
     * @param radius the radius of the tube
     * @param ray    the ray the tube
     */
    public Tube(double radius, Ray ray) {
        this.radius = radius;
        this.ray = ray;
    }
    //**********Getters**********//

    /**
     * gets a point and returns the normal to that point
     *
     * @param p the given point
     * @return The normal to the given point
     */
    public Vector getNormal(Point p) {
        double t = ray.getDir().dotProduct(p.subtract(ray.getP0()));
        Point o = p.add(ray.getDir().scale(t));
        return p.subtract(o).normalize();
    }

    /**
     * @return the radius of the tube
     */
    public double getRadius() {
        return radius;
    }

    /**
     * @return the ray of the tube
     */
    public Ray getRay() {
        return ray;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "radius=" + radius +
                ", ray=" + ray +
                '}';
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
