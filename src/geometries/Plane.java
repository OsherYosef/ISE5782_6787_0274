package src.geometries;

import src.primitives.*;

public class Plane implements Geometry {
    Point p0;
    Vector orthonormal;

    public Plane(Point p1, Point p2, Point p3) {

    }

    public Plane(Vector norm) {
        orthonormal = norm.normalize();

    }


    public Vector getNormal(Point p) {
        return null;
    }


    public Vector getNormal() {
        return null;
    }

}
