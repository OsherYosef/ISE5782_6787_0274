package primitives;

import java.util.List;

import static primitives.Util.*;

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
     * @param v the direction vector- will be normalized
     * @param p the given point
     */
    public Ray(Vector v, Point p) {
        p0 = p;
        dir = v.normalize();
    }

    /**
     * Build by vector and point
     *
     * @param v the direction vector- will be normalized
     * @param p the given point
     */
    public Ray(Point p, Vector v) {
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

    public Point getPoint(double t) {
        return !isZero(t) ? p0.add(dir.scale(t)) : p0;
    }

    //****************Operations************//

    /**
     * Find the closest point to the ray's starting point
     *
     * @param points a list of points
     * @return The closest point to the ray's starting point
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null||points.isEmpty())
            return null;

        double min = 1.7976931348623157E308; //this is double's max value
        Point minPoint = new Point(0, 0, 0);
        for (Point p : points) {
            double temp = p0.distance(p);
            if (temp < min) {
                min = temp;
                minPoint = p;
            }
        }
        return minPoint;
    }

    @Override
    public String toString() {
        return "Ray{" +
                "P0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

}
