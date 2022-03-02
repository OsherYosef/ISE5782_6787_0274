package src.primitives;


/*
        *
        * @author Osher and Dov
        *
        */
public class Vector extends Point{

    //*********************Constructors*********************//
    public Vector(Double3 from) {
        super(from);
        if (from.equals(Double3.ZERO))
            throw new IllegalArgumentException("Can't create a 0 vector");
    }
    public Vector(Double d1,Double d2,Double d3){
        super(d1,d2,d3);
        if (d1==0&&d2==0&&d3==0)
            throw new IllegalArgumentException("Can't create a 0 vector");
    }
    //*********************Others*********************//
    public  double dotProduct(Vector vector){
        Vector v= new Vector(vector.xyz);
        Vector v1= new Vector(xyz.product(v.xyz));
        return (v1.xyz.d1+v1.xyz.d2+v1.xyz.d3);
    }
    /**
     * Gets vector
     */
    public Double3 getVector(){
        return xyz;
    }
    /**
     * Normalizes a vector
     */
    public Vector normalize(){
        Vector temp= new Vector(xyz);
        return temp.scale(1/length());
    }

    /**
     *
     * @return The length of the vector
     */
    public double length(){
        return Math.sqrt(lengthSquared());
    }
    /**
     * @Return The length of the vector(squared)
     */
    public double lengthSquared(){
        Double3 temp=xyz.product(xyz);
        return temp.d1+temp.d2+temp.d3;
    }

    /**
     * Cross product between 2 vectors
     * @param vector  The other vector
     * @return the result of cross product
     */
    public  Vector crossProduct(Vector vector){
        Vector v= new Vector(vector.xyz);
        Vector v1=new Vector(v.xyz.d2*xyz.d3-v.xyz.d3*xyz.d2,v.xyz.d3*xyz.d1-v.xyz.d1*xyz.d3,v.xyz.d1*xyz.d2-v.xyz.d2*xyz.d1);
        return v1;
    }

    /**
     * Multiplies all the Vector's elements with a scalar
     * @param d The scalar
     * @return the new vector(multiplied with the scalar
     */
    public Vector scale(double d){
        Vector v= new Vector(xyz.d1*d, xyz.d2*d, xyz.d3*d);
        return v;
    }

    /**
     * Adds a vector with another vector
     * @param vector the other vector
     */
    public Vector add(Vector vector){
        Vector v= new Vector(vector.xyz);
        Vector v1= new Vector(xyz.add(v.xyz));
        return v1;
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