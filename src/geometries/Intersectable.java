package src.geometries;

import java.util.List;

import src.primitives.*;

/**
 * Interface for Intersections between points within rays and other types of Geometrical shapes
 *
 * @author Osher and Dov
 */

public interface Intersectable {
//====================function==============================

    /**
     * @param ray the given ray
     * @return list of points that interact with the ray
     */
    List<Point> findIntersections(Ray ray);


}
