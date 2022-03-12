package src.geometries;

import src.primitives.*;

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
}
