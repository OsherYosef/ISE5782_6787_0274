package src.geometries;

import src.primitives.*;


public class Tube implements Geometry {
    protected Double radius;
    protected Ray ray;
    public Vector getNormal(Point p) {
        return null;
    }

    public Double getRadius() {
        return radius;
    }

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
