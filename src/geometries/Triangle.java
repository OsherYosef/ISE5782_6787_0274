package src.geometries;


import src.primitives.*;

/**
 * Class triangle for the geometrical shape Triangle in space
 * Triangle is represented by a plane
 */
public class Triangle {
    Plane plane;

    //*************Constructor*************//
    public Triangle(Point p1, Point p2, Point p3) {
        plane=new Plane(p1, p2, p3);
    }

    //************Getters*****************//
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
