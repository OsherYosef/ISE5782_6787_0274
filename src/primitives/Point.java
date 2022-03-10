package src.primitives;

/**
 * Class Point for point in space
 * This class contains a Double3
 *
 * @author Osher and Dov
 **/
public class Point {
    final Double3 xyz;

    //*********************Constructors*********************//

    /**
     * Constructor of the class Point with a Double3
     *
     * @param from Double3
     **/
    public Point(Double3 from) {
        xyz = from;
    }

    /**
     * Constructor of the class point with 3 Doubles
     *
     * @param do1 first double x
     * @param do2 second double y
     * @param do3 third double z
     **/
    public Point(Double do1, Double do2, Double do3) {
        xyz = new Double3(do1, do2, do3);
    }

    //************Operations******************//
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point) obj;
        return xyz.equals(other.xyz);
    }

    @Override
    public String toString() {
        return xyz.toString();
    }

    /**
     * Adds a vector to our point and returns a point
     *
     * @param vector a vector that will be added to the point
     */

    public Point add(Vector vector) {
        return new Point(vector.xyz.add(xyz));
    }

    /**
     * Subtracts a point with our point and returns the created vector
     *
     * @param point a given point
     */
    public Vector subtract(Point point) {
        return new Vector(xyz.subtract(point.xyz));
    }

    /**
     * Calculates the distance(Squared) between 2 points
     *
     * @param p a given point
     */
    public double distanceSquared(Point p) {
        Double x = p.xyz.d1 - xyz.d1;
        Double y = p.xyz.d2 - xyz.d2;
        Double z = p.xyz.d3 - xyz.d3;
        return x * x + y * y + z * z;
    }

    /**
     * Calculates the distance between 2 points
     *
     * @param p a given point
     */
    public double distance(Point p) {
        return Math.sqrt(distanceSquared(p));
    }

}
