package src.primitives;


/*
        *
        * @author Osher and Dov
        *
        */
public class Vector extends Point{

    public Vector(Double3 from) {
        super(from);
    }
    public Vector(Double d1,Double d2,Double d3){super(d1,d2,d3);}

    public static double dotProduct(Vector v){
        //TODO
        return 0.0;
    }
    public Vector normalize(){
        //TODO
        double sum=0.0;
        sum=length();
        //sum=Math.sqrt(sum);
        Vector v= new Vector(xyz.d1/sum, xyz.d2/sum, xyz.d3/sum);
        return null;
        //return (xyz.d1/sum, xyz.d2/sum, xyz.d3/sum);
    }
    public double length(){
        double sum=0.0;
        sum=((xyz.d1* xyz.d1)+(xyz.d2* xyz.d2)+(xyz.d3* xyz.d3));
        sum=Math.sqrt(sum);
        return sum;
    }
    public double lengthSquared(){
        //TODO
        return 0.0;
    }
    public static Vector crossProduct(Vector v){
        //TODO
        return null;
    }
    public Vector scale(double d){
        double sum=0.0;
        sum=length();
        Vector v= new Vector(xyz.d1*sum, xyz.d2*sum, xyz.d3*sum);
        return v;
    }
    public static Vector add(Vector vector){
        Vector v=(vector.xyz.d1+)
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