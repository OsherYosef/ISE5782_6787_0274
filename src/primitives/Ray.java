package src.primitives;


/**
 * Class Ray
 * Ray in space is determined by a point and a direction
 * the direction is a normal vector
 *
 * @author Osher and Dov
 **/
public class Ray {
    private final Point p0;
    private final Vector dir;
    //***************Constructors**********//

    /**
     * Build by vector and point
     *
     * @param v the given vector- will be normalized
     * @param p the given point
     */
    public Ray(Vector v, Point p) {
        p0 = p;
        dir = v.normalize();
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
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

}
