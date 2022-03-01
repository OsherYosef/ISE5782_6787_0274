package src.primitives;



/**
 *
 * @author Osher and Dov
 *
 **/
public class Point {
    protected Double3 xyz;

    //*********************Constructor*********************//
    /**
     * Constructor of the class Point with a Double3
     * @param from Double3
     * @auther Osher and Dov
     * **/
    public Point(Double3 from){
        xyz=from;
    }
    /**
     * Constructor of the class point with 3 Doubles
     * @param do1 first double x
     * @param do2 second double y
     * @param do3 third double z
     * @auther Osher and Dov
     * **/
    public Point(Double do1,Double do2,Double do3){
      xyz= new Double3(do1,do2,do3);
    }




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

    public static Point add(Vector V){
        return null;
    }
    public  Vector subtract(Point point){
        Vector v=new Vector(point.xyz.subtract(xyz));
        return v;
    }
    public double distanceSquared(Point p){

    }
    public double distance(Point p){

    }

}
