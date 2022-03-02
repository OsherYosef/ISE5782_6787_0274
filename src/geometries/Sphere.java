package src.geometries;

import src.primitives.Point;
import src.primitives.Vector;

public class Sphere implements Geometry {
    public Point center;
    public double radius;

    public Point getCenter() {
        return center;
    }


    public double getRadius() {
        return radius;
    }


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
