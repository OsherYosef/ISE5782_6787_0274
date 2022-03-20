package src.geometries;

import src.primitives.*;

/**
 * Interface geometry for geometrical shapes
 */
public interface Geometry extends Intersectable {

    /**
     * Return the normal to a certain point on a geometry object
     * @param p
     * @return
     */
    Vector getNormal(Point p);//TODO doc
}
