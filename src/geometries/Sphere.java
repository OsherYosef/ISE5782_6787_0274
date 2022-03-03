package src.geometries;

import src.primitives.*;

/**
 *Class Sphere for the geometrical shape sphere in space
 * sphere is represented by a point in space and radius
 */
public class Sphere implements Geometry {
    public Point center;
    public double radius;
    //**********Getters**********8//
    public Point getCenter() {
        return center;
    }


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
