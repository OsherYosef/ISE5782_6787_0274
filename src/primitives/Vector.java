package src.primitives;
/**
 *
 * @author Osher and Dov
 *
 **/
public class Vector extends Point{
    public static double dotProduct(Vector v){
        //TODO
        return 0.0;
    }
    public Vector normalize(){
        //TODO
        return null;
    }
    public double length(){
        //TODO
        return 0.0;
    }
    public double lengthSquared(){
        //TODO
        return 0.0;
    }
    public static Vector crossProduct(Vector v){
        //TODO
        return null;
    }
    public static Vector scale(double d){
        //TODO
        return null;
    }
    public static Vector add(Vector vector){
        //TODO
        return null;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point other = (Point)obj;
        return super.equals(other);
    }
    @Override
    public String toString() { return "->" + super.toString(); }

}
