package renderer;

import lighting.*;
import primitives.*;
import scene.*;
import java.util.List;
import static geometries.Intersectable.GeoPoint;
import static primitives.Util.*;

/**
 * Class RayTracerBasic for basic ray tracer, a simple ray tracer that can show 2 colors the color of an object or the background
 *
 * @author Osher and Dov
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1;

    /**
     * Constructor for Basic ray tracer
     *
     * @param scene a given scene
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Trace a ray through a scene
     *
     * @param ray given ray
     * @return the Color of the ray intersections
     */
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? Color.BLACK : calcColor(closestPoint, ray);
    }

    /**
     * calculate the Color on a certain point
     *
     * @param gp the given point
     * @param r  the ray from the camera
     * @return the color of the scene in a certain point
     */
    private Color calcColor(GeoPoint gp, Ray r) {
        return scene.ambientLight.getIntensity().add(calcColor(gp, r, MAX_CALC_COLOR_LEVEL, Double3.ONE.scale(INITIAL_K)));
    }

    /**
     * calculate the Color on a certain point
     *
     * @param geoPoint the given point
     * @param ray      the ray from the camera
     * @param level    Level of recursion
     * @param k        Minimum level of Color calculation
     * @return the color of the scene in a certain point
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Color color = geoPoint.geometry.getEmission().add(calcLocalEffects(geoPoint, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(geoPoint, ray.getDir(), level, k));
    }

    /**
     * Calculate the Global effects on a certain point
     *
     * @param gp    the certain point
     * @param v     Vector v
     * @param level Level of recursion
     * @param k     Minimum level of Color calculation
     * @return the color after all the Global effects on it
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Point point = gp.point;
        Vector n = gp.geometry.getNormal(point);
        Material material = gp.geometry.getMaterial();

        Double3 kkr = material.kR.product(k);
        if (!kkr.lowerThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(point, v, n), level, material.kR, kkr);

        Double3 kkt = material.kT.product(k);
        if (!kkt.lowerThan(MIN_CALC_COLOR_K))
            color = color.add(calcGlobalEffect(constructRefractedRay(point, v, n), level, material.kT, kkt));
        return color;
    }

    /**
     * Calculate the Global effect on a certain point
     *
     * @param ray   given ray
     * @param level level of recursion
     * @param kx    kR or kT factors
     * @param kkx   kR or kT multiplied by k
     * @return the color after calculating the effect on the point
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * Construct a ray that would be refracted from a geometry point
     *
     * @param gp geometry point
     * @param v  v vector
     * @param n  normal to the geometry
     * @return a refracted ray
     */
    private Ray constructRefractedRay(Point gp, Vector v, Vector n) {
        return new Ray(v, n.scale(-1), gp);
    }

    /**
     * Construct a ray that would be reflected from the geometry point
     *
     * @param gp geometry point
     * @param v  v vector
     * @param n  normal to the geometry
     * @return a reflected ray
     */
    private Ray constructReflectedRay(Point gp, Vector v, Vector n) {
        //The ray is created with the point and vector r (= v-2(nv)n)
        return new Ray(v.subtract(n.scale(v.dotProduct(n) * 2)), n, gp);
    }

    /**
     * Find the closest intersection point to the ray staring point
     *
     * @param ray given ray
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> points = scene.geometries.findGeoIntersections(ray);
        if (points == null || points.isEmpty())
            return null;
        Point p0 = ray.getP0();
        double min = Double.POSITIVE_INFINITY; //this is double's max value
        GeoPoint minPoint = null;
        for (GeoPoint geoP : points) {
            Point p = geoP.point;
            double temp = p0.distance(p);
            if (temp < min) {
                min = temp;
                minPoint = geoP;
            }
        }
        return minPoint;
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
                if (unshaded(gp, lightSource, n, l)) {
                    Color iL = lightSource.getIntensity(gp.point);
                    color = color.add(iL.scale(calcDiffusive(material, nl).add(calcSpecular(material, n, l, nl, v))));
                }
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
        Vector r = l.subtract(n.scale(nl * 2));
        double vr = alignZero(-v.dotProduct(r));
        if (vr <= 0) return Double3.ZERO;
        return mat.kS.scale(Math.pow(vr, mat.nShininess));
    }

    /**
     * Check if there are no objects between a given point on a geometrical shape and a light source
     *
     * @param gp          a point on a geometrical shape
     * @param lightSource Light source
     * @param n           normal to the Geometry
     * @param l           vector l -from light source to the point
     * @return True if there aren't any objects between the point
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector n, Vector l) {
        Point point = gp.point;
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(new Ray(l.scale(-1), n, point));
        if (intersections == null) return true;
        double d = lightSource.getDistance(point);
        for (GeoPoint p : intersections) {
            if (d > lightSource.getDistance(p.point) && p.geometry.getMaterial().kT.equals(Double3.ZERO))
                return false;
        }
        return true;
    }
}
