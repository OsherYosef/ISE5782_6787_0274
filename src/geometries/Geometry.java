package src.geometries;

import src.primitives.*;

/**
 * Interface geometry for geometrical shapes
 */
public interface Geometry {

    /**
     * Return the normal to a certain point on a geometry object
     * @param p the given point
     * @return the normal vector created by the point and the geomerty object
     */
    Vector getNormal(Point p);//TODO doc
}
