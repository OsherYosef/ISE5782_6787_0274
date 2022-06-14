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

    private Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * Returns the color of the emission light
     *
     * @return the color of the emission light
     */
    public Color getEmission() {
        return emission;
    }

    /**
     * Get the material that the Geometry is made out of
     *
     * @return the Material of the Geometry
     */
    public Material getMaterial() {
        return material;
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

    /**
     * Change the Material of the geometry
     *
     * @param material given material
     * @return the geometry after changing the material
     */
    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }


}
