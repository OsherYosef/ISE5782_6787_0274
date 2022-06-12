package primitives;


/**
 * Class vector for a vector with points
 * any vector has a size: the distance from the origin point
 * and a direction: represent by the point
 * This class extends point class
 *
 * @author Osher and Dov
 */
public class Vector extends Point {
    /**
     * unit vector for X axis
     */
    public static final Vector X = new Vector(1, 0, 0);
    /**
     * unit vector for Y axis
     */
    public static final Vector Y = new Vector(0, 1, 0);
    /**
     * unit vector for Z axis
     */
    public static final Vector Z = new Vector(0, 0, 1);


    //*********************Constructors*********************//

    /**
     * Constructor for class Vector using a Double3
     * NOTE: A vector with only 0's will throw IllegalArgumentException
     *
     * @param from Double3
     */
    public Vector(Double3 from) {
        super(from);
        if (from.equals(Double3.ZERO))
            throw new IllegalArgumentException("Can't create a 0 vector");
    }

    /**
     * Constructor for class Vector using 3 coordinates
     * NOTE: A vector with only 0's will throw IllegalArgumentException
     *
     * @param x 1st coordinate value
     * @param y 2nd coordinate value
     * @param z 3rd coordinate value
     */
    public Vector(double x, double y, double z) {
        this(new Double3(x, y, z));
    }
    //*********************Operations*********************//

    /**
     * Execute a dot product operation on our vector with another vector
     *
     * @param v Another vector
     * @return The output scalar
     */
    public double dotProduct(Vector v) {
        return v.xyz.d1 * xyz.d1 + v.xyz.d2 * xyz.d2 + v.xyz.d3 * xyz.d3;
    }

    /**
     * Normalizes a vector
     */
    public Vector normalize() {
        return new Vector(xyz).scale(1 / length());
    }

    /**
     * @return The length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * @return The length of the vector(squared)
     */
    public double lengthSquared() {
        return dotProduct(this);
    }

    /**
     * Cross product between 2 vectors
     *
     * @param v The other vector
     * @return the result of cross product
     */
    public Vector crossProduct(Vector v) {
        return new Vector(xyz.d2 * v.xyz.d3 - xyz.d3 * v.xyz.d2, xyz.d3 * v.xyz.d1 - xyz.d1 * v.xyz.d3, xyz.d1 * v.xyz.d2 - xyz.d2 * v.xyz.d1);
    }

    /**
     * Multiplies all the Vector's elements with a scalar
     *
     * @param d The scalar
     * @return the new vector(multiplied with the scalar
     */
    public Vector scale(double d) {
        return new Vector(xyz.d1 * d, xyz.d2 * d, xyz.d3 * d);
    }

    /**
     * Adds a vector with another vector
     *
     * @param v the other vector
     */
    public Vector add(Vector v) {
        return new Vector(xyz.add(v.xyz));
    }

    /**
     * Return an orthogonal vector to a given vector
     *
     * @return An orthogonal vector to the given Vector
     */
    public Vector getOrthogonal() {
        return xyz.d1 == 0 ? Vector.X : new Vector(-xyz.d2, xyz.d1, 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Point other)) return false;
        return super.equals(other);
    }

    @Override
    public String toString() {
        return "->" + super.toString();
    }

}