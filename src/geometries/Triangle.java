package src.geometries;

import src.geometries.Plane;
import src.primitives.*;
public class Triangle {
    Plane plane;
    public Triangle(Point p1, Point p2, Point p3) {
        plane=new Plane(p1, p2, p3);
    }

    public Plane getPlane() {
        return plane;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "plane=" + plane +
                '}';
    }
}
