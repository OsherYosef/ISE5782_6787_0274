package src.geometries;


import src.primitives.*;

/**
 * Class triangle for the geometrical shape Triangle in space
 * Triangle is represented by a plane
 */
public class Triangle {
    private final Plane plane;

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

    //************Getters*****************//
    //Returns the plane of the triangle
    public Plane getPlane() {
        return plane;
    }

    //**********Operations****************//
    @Override
    public String toString() {
        return "Triangle{" +
                "plane=" + plane +
                '}';
    }
}
