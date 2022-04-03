package geometries;

import primitives.*;


import java.util.List;

import static primitives.Util.*;

/**
 * class plane for a plane in space.
 * the plane is represented by a point and an orthogonal vector to the point.
 */
public class Plane implements Geometry {
    final private Point p0;
    final private Vector orthoNormal;

    //****************Constructor***************//

    /**
     * create a plane with 3 points
     *
     * @param p1 1st point
     * @param p2 2nd point
     * @param p3 3rd point
     * @throws IllegalArgumentException when all the points are on the same line
     */
    public Plane(Point p1, Point p2, Point p3) {
        p0 = p1;
        Vector v1 = p2.subtract(p1); // v1=p2-p1
        Vector v2 = p3.subtract(p1); // v2=p3-p1
        orthoNormal = v1.crossProduct(v2).normalize(); // n=normalize(v1Xv2)
    }

    /**
     * Create a plane with a vector and a point
     *
     * @param norm given vector (will be normalized)
     * @param p    given point
     */
    public Plane(Vector norm, Point p) {
        orthoNormal = norm.normalize();
        p0 = p;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        /*
          Point P0 = ray.getP0();
        Vector v = ray.getDir();
        Vector n = orthoNormal;

        double newV = n.dotProduct(v);

        if (isZero(newV)) return null;

        Vector P0v = p0.subtract(P0);
        double t = alignZero(n.dotProduct(P0v) / newV);
        if (t > 0) {
            return List.of(P0.add(v.scale(t)));
        }
         */
        Point p0 = ray.getP0();
        Vector v = ray.getDir();

        Vector p0v;
        try {
            p0v = p0.subtract(this.p0);
        } catch (IllegalArgumentException ignore) {
            return null;
        }

        double newV = orthoNormal.dotProduct(v);
        if (isZero(newV)) return null;

        double t = alignZero(orthoNormal.dotProduct(p0v) / newV);
        return t > 0 ? List.of(ray.getPoint(t)) : null;
    }

    //*********************Getters******************//
    public Vector getNormal(Point p) {
        return orthoNormal;
    }


    public Vector getNormal() {
        return orthoNormal;
    }

}
