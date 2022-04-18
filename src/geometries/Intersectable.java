package geometries;

import java.util.List;
import java.util.Objects;

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
    public abstract List<Point> findIntersections(Ray ray);

    /**
     * Static class GeoPoint
     * TODO
     */
    public static class GeoPoint {
        public Geometry geometry;
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
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
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
     * TODO
     *
     * @param ray
     * @return
     */
    public abstract List<GeoPoint> findGeoIntersections(Ray ray);

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
