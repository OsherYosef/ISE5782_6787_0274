package renderer;

import lighting.*;
import primitives.*;

import scene.*;
import static geometries.Intersectable.GeoPoint;

import static primitives.Util.*;

/**
 * Class RayTracerBasic for basic ray tracer, a simple ray tracer that can show 2 colors the color of an object or the background
 *
 * @author Osher and Dov
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
        return intersections == null ? scene.background //
                : calcColor(ray.findClosestGeoPoint(intersections), ray);
    }

    /**
     * calculate the Color on a certain point
     *
     * @param gp the given point
     * @param r  the ray from the camera
     * @return the ambient light color of the scene
     */
    private Color calcColor(GeoPoint gp, Ray r) {
        return scene.ambientLight.getIntensity().add(gp.geometry.getEmission()).add(calcLocalEffects(gp, r));
    }

    /**
     * Calculate the Local effects of the lights in the area
     *
     * @param gp  the point
     * @param ray the ray from the camera
     * @return the added effects of the light on the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = Color.BLACK;
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl).add(calcSpecular(material, n, l, nl, v))));
            }
        }
        return color;
    }

    /**
     * Calculate the Diffusive factor
     *
     * @param m  the material
     * @param nl the dot product between vector n and l
     * @return the diffusive factor
     */
    private Double3 calcDiffusive(Material m, double nl) {
        return m.kD.scale(nl > 0 ? nl : -nl);
    }

    /**
     * Calculate the specular factor
     *
     * @param mat the material of the geometry
     * @param n   Vector n
     * @param l   vector l
     * @param nl  dotProduct of n and l
     * @param v   Vector v
     * @return the specular factor
     */
    private Double3 calcSpecular(Material mat, Vector n, Vector l, double nl, Vector v) {
        //  return mat.kS.scale()
        Vector r = l.subtract(n.scale(nl * 2));
        double vr = alignZero(-v.dotProduct(r));
        if (vr <= 0) return Double3.ZERO;
        return mat.kS.scale(Math.pow(vr, mat.nShininess));
    }

}
