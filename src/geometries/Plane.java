package src.geometries;

import src.primitives.*;



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
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        p0 = p1;
        //According to the lecture (Part 2 page 20)
        Vector v1 = p2.subtract(p1);//v1=p2-p1
        Vector v2 = p3.subtract(p1);//v2=p3-p1
        orthoNormal = v1.crossProduct(v2).normalize();//n=normalize(v1Xv2)
    }

    /**
     * Create a plane with a normal vector
     *
     * @param norm
     */
    public Plane(Vector norm, Point p) {
        orthoNormal = norm.normalize();
        p0 = p;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point P0=ray.getP0();
        Vector v=ray.getDir();
        Vector n=orthoNormal;

        double newV = n.dotProduct(v);

        if(isZero(newV)) return null;

        Vector P0v=p0.subtract(P0);
        double t=alignZero(n.dotProduct(P0v)/newV);
        if(t>0) {

            return List.of(P0.add(v.scale(t)));
        }

        return null;
    }

    //*********************Getters******************//
    public Vector getNormal(Point p) {
        return orthoNormal;
    }


    public Vector getNormal() {
        return orthoNormal;
    }

}
