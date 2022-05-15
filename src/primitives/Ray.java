package primitives;

import java.util.List;

import geometries.Intersectable.GeoPoint;

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
    private static final double DELTA = 0.1;

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
     * Constructor for class ray with 2 vectors and a point
     *
     * @param v ray direction (normalized!)
     * @param n normal to the point
     * @param p given Point
     */
    public Ray(Vector v, Vector n, Point p) {
        double nv = v.dotProduct(n);
        p0 = p.add(n.scale(nv > 0 ? DELTA : -DELTA));
        dir = v;
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
        try {
            return p0.add(dir.scale(t));
        } catch (IllegalArgumentException ignore) {
            return p0;
        }
    }

    //****************Operations************//

    /**
     * Find the closest point to the ray's starting point
     *
     * @param points- list of geo points
     * @return the closest point in the list
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null || points.isEmpty())
            return null;

        double min = Double.POSITIVE_INFINITY; //this is double's max value
        GeoPoint minPoint = null;
        for (GeoPoint geoP : points) {
            Point p = geoP.point;
            double temp = p0.distance(p);
            if (temp < min) {
                min = temp;
                minPoint = geoP;
            }
        }
        return minPoint;
    }

    /**
     * Find the closest point to the ray's starting point
     *
     * @param points a list of points
     * @return The closest point to the ray's starting point
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
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
        if (this == o) return true;
        if (!(o instanceof Ray ray)) return false;
        return p0.equals(ray.p0) && dir.equals(ray.dir);
    }

}
