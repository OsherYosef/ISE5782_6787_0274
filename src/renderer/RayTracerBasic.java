package renderer;

import primitives.*;

import scene.*;

import java.util.List;

/**
 * Class RayTracerBasic for basic ray tracer, a simple ray tracer that can show 2 colors the color of an object or the background
 */
public class RayTracerBasic extends RayTracerBase {
    /**
     * Constructor for Basic ray tracer
     *
     * @param scene a given scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }


    public Color traceRay(Ray r) {
        List<Point> intersections = scene.geometries.findIntersections(r);
        return intersections != null ? calcColor(r.findClosestPoint(intersections)) : scene.background;
    }

    /**
     * calculate the Color on a certain point
     *
     * @param p the given point
     * @return the ambient light color of the scene
     */
    private Color calcColor(Point p) {
        return scene.ambientLight.getIntensity();
    }
}
