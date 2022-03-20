package src.geometries;


import src.primitives.*;

import java.util.List;

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
        plane = new Plane(p1, p2, p3);
    }


    public List<Point> findIntersections(Ray ray){
        List<Point> result = plane.findIntersections(ray);
        if(result == null){
            return null;
        }
        Point p0 = ray.getP0();
        Vector v=ray.getDir();
        Point p1 = vertices.get(0);
        Point p2 = vertices.get(1);
        Point p3 = vertices.get(2);
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);
        Vector n1 = v1.crossProduct(v2);
        Vector n2 = v2.crossProduct(v3);
        Vector n3 = v3.crossProduct(v1);
        double s1 = n1.dotProduct(v);
        double s2 = n2.dotProduct(v);
        double s3 = n3.dotProduct(v);
        if(s1> 0 && s2 > 0 && s3 > 0 ||  s1 < 0 && s2< 0 && s3 < 0)
            return result;
        return super.findIntersections(ray);
    }

    @Override
    public Vector getNormal(Point point) {
        return super.getNormal(point);
    }

    //**********Operations****************//
    @Override
    public String toString() {
        return "Triangle{" +
                "plane=" + plane +
                '}';
    }
}
