package src.primitives;


/**
 * Class Ray
 * Ray in space is determined by a point and a direction
 * the direction is a normal vector
 * @author Osher and Dov
 * **/
public class Ray {
    final Point p0;
    final Vector dir;
    //***************Constructors**********//
    /**
     * Build by vector and point
     * @param vector a vector that will be normalized
     */
    public Ray(Vector vector){
        dir= vector.normalize();
        p0=new Point(0.0,0.0,0.0);
    }

    //****************Getters***************//
    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }
    //****************Operations************//
    @Override
    public String toString() {
        return "Ray{" +
                "P0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0)&& dir.equals(ray.dir);
    }

}
