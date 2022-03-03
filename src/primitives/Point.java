package src.primitives;

/**
 * Class Point for point in space
 * This class contains a Double3
 * @author Osher and Dov
 *
 **/
public class Point {
    protected Double3 xyz;

    //*********************Constructors*********************//
    /**
     * Constructor of the class Point with a Double3
     * @param from Double3
     *
     * **/
    public Point(Double3 from){
        xyz=from;
    }
    /**
     * Constructor of the class point with 3 Doubles
     * @param do1 first double x
     * @param do2 second double y
     * @param do3 third double z
     *
     * **/
    public Point(Double do1,Double do2,Double do3){
      xyz= new Double3(do1,do2,do3);
    }
    //************Operations******************//
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return xyz.equals(other.xyz);
    }
    @Override
    public String toString() { return xyz.toString(); }

    /**
     * Adds a vector to our point and returns a point
     * @param vector a vector that will be added to the point
     */

    public Point add(Vector vector){
        Double3 temp= vector.xyz.add(xyz);
        return new Point(temp);
    }
    /**
     * Subtracts a point with our point and returns the created vector
     * @param point a given point
     * */
    public  Vector subtract(Point point){
        Vector v=new Vector(xyz.subtract(point.xyz));//point.xyz.subtract(xyz));
        return v;
    }
    /**
     * Calculates the distance(Squared) between 2 points
     * @param p a given point
     * */
    public double distanceSquared(Point p){
        Double3 temp= p.xyz.subtract(xyz);//subtract p's xyz with this xyz
        temp=temp.product(temp);
        Double sum= temp.d1+temp.d2+temp.d3;
        return sum;
    }
    /**
     * Calculates the distance between 2 points
     * @param p a given point
     */
    public double distance(Point p){
        double d=distanceSquared(p);
        return Math.sqrt(d);
    }

}
