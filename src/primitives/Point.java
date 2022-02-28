package src.primitives;
import primitives.Double3;
import  src.primitives.*;


/**
 *
 * @author Osher and Dov
 *
 **/
public class Point {
     public Double3 xyz;



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
    public static Vector subtract(Point point){
        return null;
    }

}
