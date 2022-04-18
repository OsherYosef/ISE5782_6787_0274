package geometries;

import primitives.*;

/**
 * Interface geometry for geometrical shapes
 */
public abstract class Geometry extends Intersectable {

    /**
     * Calculate the vector to a point on a geometrical shape
     *
     * @param p given point
     * @return Normal vector to the point
     */
    public abstract Vector getNormal(Point p);

    protected Color emission = Color.BLACK;

    /**
     * Returns the color of the emission light
     *
     * @return the color of the emission light
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Change the color of the geometry light
     *
     * @param color given color
     * @return the geometry after changing the emission color
     */
    public Geometry setEmission(Color color) {
        emission = color;
        return this;
    }
}
