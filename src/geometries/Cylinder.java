package geometries;

import primitives.*;
import java.util.List;

/**
 * Class Cylinder for a cylinder in space
 * a Cylinder will have directions and points and heights
 * Extends the class tube
 */
public class Cylinder extends Tube {
    private final double height;

    /**
     * Constructor for class cylinder using a radius, ray and height
     *
     * @param radius the radius
     * @param ray    the ray
     * @param h      the height of the cylinder
     */
    public Cylinder(double radius, Ray ray, double h) {
        super(radius, ray);
        this.height = h;
    }

    //***********Getters**********//
    public double getHeight() {
        return height;
    }

    //***********Operations********//
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", radius=" + super.getRadius() +
                ", ray=" + super.getRay() +
                '}';
    }



}
