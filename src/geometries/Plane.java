package src.geometries;
import src.primitives.*;

public class Plane extends Geometry{
    Point p0;
    Vector orthonormal;
    Double Avalue=0.0;
    Double Bvalue=0.0;
    Double Cvalue=0.0;
    Double Dvalue=0.0;
    public Plane(Point p1,Point p2,Point p3){

    }
    public Plane(Vector norm){
        orthonormal=norm;
    }


    public Vector getNormal(Point p) {
        return null;
    }
}
