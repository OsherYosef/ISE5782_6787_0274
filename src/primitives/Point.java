package primitives;

/**
 * Class Point for point in space
 * This class contains a Double3
 *
 * @author Osher and Dov
 **/
public class Point {
    final Double3 xyz;

    //*********************Constructors*********************//
    public static final Point ZERO = new Point(0, 0, 0);

    /**
     * Constructor of the class Point with a Double3
     *
     * @param from Double3
     **/
    public Point(Double3 from) {
        xyz = from;
    }

    /**
     * Constructor of the class point with 3 coordinate value
     *
     * @param x 1st coordinate value
     * @param y 2nd coordinate value
     * @param z 3rd coordinate value
     **/
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    /**
     * Constructor of the class point with 3 coordinate value
     *
     * @param x all The coordinate values
     **/
    public Point(double x) {
        xyz = new Double3(x, x, x);
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
        double x = p.xyz.d1 - xyz.d1;
        double y = p.xyz.d2 - xyz.d2;
        double z = p.xyz.d3 - xyz.d3;
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

    //************Getters******************//
    //These functions return the x, y or z coordinate values of our point
    public double getX() {
        return xyz.d1;
    }

    public double getY() {
        return xyz.d2;
    }

    public double getZ() {
        return xyz.d3;
    }

}
