package geometries;


import primitives.*;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class triangle for the geometrical shape Triangle in space
 * Triangle is represented by a plane
 */


public class Triangle extends Polygon {


    //*************Constructor*************//

    /**
     * Constructor for class sphere using 3 Points
     *
     * @param p1 first point
     * @param p2 second point
     * @param p3 third point
     */
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }


    public List<Point> findIntersections(Ray ray) {
        List<Point> result = plane.findIntersections(ray);
        if (result == null) return null;

        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double s1 = alignZero(n1.dotProduct(v));
        if (s1 == 0) return null;

        Point p3 = vertices.get(2);
        Vector v3 = p3.subtract(p0);
        Vector n2 = v2.crossProduct(v3);
        double s2 = alignZero(n2.dotProduct(v));
        if (s1 * s2 <= 0) return null;

        Vector n3 = v3.crossProduct(v1);
        double s3 = alignZero(n3.dotProduct(v));
        if (s1 * s3 <= 0) return null;

        return result;
    }

    //**********Operations****************//
    @Override
    public String toString() {
        return "Triangle{" +
                "plane=" + plane +
                '}';
    }
}
