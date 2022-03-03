package src.geometries;

import src.primitives.*;

/**
 * Class geometry for geometrical shapes
 */
public interface Geometry {
    /**
     * @return The normal to a shape
     */
    Vector getNormal(Point p);
}
