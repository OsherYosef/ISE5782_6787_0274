package src.geometries;

/**
 * Class Cylinder for a cylinder in space
 * a Cylinder will have directions and points and heights
 * Extends the class tube
 */
public class Cylinder extends Tube{
        double height;
    //***********Getters**********//
    public double getHeight() {
        return height;
    }
    //***********Operations********//
    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", radius=" + radius +
                ", ray=" + ray +
                '}';
    }
}
