package geometries;

import java.util.List;

import primitives.*;

/**
 * Interface for Intersections between points within rays and other types of Geometrical shapes
 *
 * @author Osher and Dov
 */
public interface Intersectable {
    /**
     * Calculate the intersection points between a geometrical shape and a given ray
     *
     * @param ray the given ray
     * @return list of points that intersects with the ray
     */
    List<Point> findIntersections(Ray ray);


}
