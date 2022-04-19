package renderer;

import primitives.*;

import scene.*;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Class RayTracerBasic for basic ray tracer, a simple ray tracer that can show 2 colors the color of an object or the background
 *
 * @auther Osher and Dov
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


    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint);
    }

    /**
     * calculate the Color on a certain point
     *
     * @param gp the given point(Won't be used here)
     * @return the ambient light color of the scene
     */
    private Color calcColor(GeoPoint gp) {
        return scene.ambientLight.getIntensity()
                .add(gp.geometry.getEmission());
    }
}
