package geometries;

import java.util.List;

import primitives.*;

/**
 * Interface for Intersections between points within rays and other types of Geometrical shapes
 *
 * @author Osher and Dov
 */
public abstract class Intersectable {
    /**
     * Calculate the intersection points between a geometrical shape and a given ray
     *
     * @param ray the given ray
     * @return list of points that intersects with the ray
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).toList();
    }


    /**
     * Static class GeoPoint
     * Each GeoPoint contains a normal point and the Geometry its on
     */
    public static class GeoPoint {
        /**
         * The geometry that the GeoPoint is on
         */
        public Geometry geometry;
        /**
         * The Point that the GeoPoint Represents
         */
        public Point point;

        /**
         * constructor for class GeoPoint
         *
         * @param g the geometry
         * @param p the point it intersects with the geometry
         */
        public GeoPoint(Geometry g, Point p) {
            geometry = g;
            point = p;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return geometry == geoPoint.geometry && point.equals(geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }


    /**
     * Find the intersection GeoPoints that intersect with a ray
     *
     * @param ray given ray
     * @return the list of Points that intersect with a ray
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
    }

    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);


    /**
     * A helper Function that finds all the intersection GeoPoint that intersect with a ray
     *
     * @param ray given ray
     * @return the list of Point that intersect with the ray
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
