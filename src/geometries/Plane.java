package src.geometries;

import src.primitives.*;

/**
 * class plane for a plane in space.
 * the plane is represented by a point and an orthogonal vector to the point.
 */
public class Plane implements Geometry {
    private Point p0;
    private Vector orthoNormal;

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

    //*********************Getters******************//
    public Vector getNormal(Point p) {
        return orthoNormal;
    }


    public Vector getNormal() {
        return orthoNormal.scale(-1.0);
    }

}
