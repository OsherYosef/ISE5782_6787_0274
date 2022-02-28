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
     * Constructor of the class Point
     * @param from gets a Double3
     * @auther Osher and Dov
     * **/
    public Point(Double3 from){
        xyz=from;
    }
    public Point(Double do1,Double do2,Double do3){
        //TODO check after with jo and e
        //Double3 d3= new Double3(do1,do2,do3);

    }

    public Double3 getXyz() {
        return xyz;
    }

    public void setXyz(Double3 xyz) {
        this.xyz = xyz;
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
    public static Vector subtract(Point point){
        return null;
    }

}
