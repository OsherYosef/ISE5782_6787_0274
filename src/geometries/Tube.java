package src.geometries;

import src.primitives.*;

/**
 * Class Tube for the geometrical shape Tube in space
 * a Tube will have a radius and a ray(direction)
 */

public class Tube implements Geometry {
    private final Double radius;
    private final Ray ray;
    //**************Constructor****************//

    /**
     * Constructor for class Tube using a radius and a ray
     *
     * @param radius the radius of the tube
     * @param ray    the ray the tube
     */
    public Tube(Double radius, Ray ray) {
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
        return null;
    }

    /**
     * @return the radius of the tube
     */
    public Double getRadius() {
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
}
