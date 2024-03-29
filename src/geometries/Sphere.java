package geometries;

import primitives.*;

import java.util.List;

import static primitives.Util.*;

/**
 * Class Sphere for the geometrical shape sphere in space
 * sphere is represented by a point in space and radius
 */
public class Sphere extends Geometry {
    private final Point center;
    private final double radius;
    private final double radiusSqr;

    /**
     * Constructor of class sphere, gets the center point and radius of the sphere
     *
     * @param center center point of the Sphere
     * @param radius Radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radiusSqr = radius * radius;
        double x = center.getX();
        double y = center.getY();
        double z = center.getZ();
        this.boundingBox = new AxisBoundingBox(new Point(x - radius, y - radius, z - radius), new Point(x + radius, y + radius, z + radius));
    }


    //**********Getters**********8//

    /**
     * Return the center of the sphere
     *
     * @return Center point of the sphere
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Return the Radius of the sphere
     *
     * @return Radius of the sphere
     */
    public double getRadius() {
        return radius;
    }

    //********Operations***********//
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    @Override
    public Vector getNormal(Point p) {
        return p.subtract(center).normalize();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (!boundingBox.boundingRayIntersection(ray))
            return null;
        Point p0 = ray.getP0();
        Vector v = ray.getDir();
        Vector u;
        try {
            u = center.subtract(p0);
        } catch (IllegalArgumentException ignore) {
            //if the starting point of the ray is exactly in the middle of the sphere
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
        }

        //vector to the center of the sphere, then the scalar and then the distance
        double tm = alignZero(v.dotProduct(u));
        double dSqr = alignZero(u.lengthSquared() - tm * tm);
        double thSqr = alignZero(radiusSqr - dSqr);
        if (thSqr <= 0) return null; //no intersections = the direction is above the sphere
        double th = Math.sqrt(thSqr); // the distance from p1 to intersection with d

        double t2 = alignZero(tm + th);//p0 to p2
        if (t2 <= 0) return null;

        double t1 = alignZero(tm - th);//p0 to p1
        return t1 <= 0 ? List.of(new GeoPoint(this, ray.getPoint(t2))) : List.of(new GeoPoint(this, ray.getPoint(t1)), new GeoPoint(this, ray.getPoint(t2)));
    }

}
