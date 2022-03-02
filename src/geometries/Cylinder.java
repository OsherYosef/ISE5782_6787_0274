package src.geometries;

public class Cylinder extends Tube{
        double height;

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", radius=" + radius +
                ", ray=" + ray +
                '}';
    }
}
