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
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point p1, Point p2, Point p3) {
        //To be
    }

    /**
     * Create a plane with a normal vector
     * @param norm
     */
    public Plane(Vector norm,Point p) {
        orthoNormal = norm.normalize();

    }

    //*********************Getters******************//
    public Vector getNormal(Point p) {
        return null;
    }


    public Vector getNormal() {
        return null;
    }

}