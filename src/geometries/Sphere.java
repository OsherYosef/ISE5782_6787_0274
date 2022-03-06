package src.geometries;

import src.primitives.*;

/**
 * Class Sphere for the geometrical shape sphere in space
 * sphere is represented by a point in space and radius
 */
public class Sphere implements Geometry {
    private Point center;
    private double radius;

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
        return null;
    }
}
