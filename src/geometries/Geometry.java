package src.geometries;

import src.primitives.*;

/**
 * Interface geometry for geometrical shapes
 */
public interface Geometry extends Intersectable {

    /**
     * Calculate the vector to a point on a geometrical shape
     * @param p given point
     * @return Normal vector to the point
     */
    Vector getNormal(Point p);
}
