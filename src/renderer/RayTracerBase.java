package renderer;

import primitives.*;
import scene.*;

/**
 * Class RayTracerBase- an abstract class for ray tracing
 *
 * @auther Osher and Dov
 */
abstract public class RayTracerBase {
    protected Scene scene;

    /**
     * Constructor for class RayTracerBase, gets a scene and saves it
     *
     * @param s given scene
     */
    public RayTracerBase(Scene s) {
        scene = s;
    }

    /**
     * Calculates the color of the object that the ray intersects
     * if the ray doesn't intersect with an object it will return the background color of the scene
     *
     * @param r the given ray
     * @return the color of the object or background
     */
    abstract public Color traceRay(Ray r);
}
