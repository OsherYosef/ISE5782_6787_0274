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
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
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
        Color color = calcLocalEffects(geoPoint, ray, k);
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
        Point point = gp.point;
        Vector n = gp.geometry.getNormal(point);
        Material material = gp.geometry.getMaterial();
        return calcGlobalEffect(constructReflectedRay(point, v, n), level, k, material.kR)
                .add(calcGlobalEffect(constructRefractedRay(point, v, n), level, k, material.kT));
    }

    /**
     * Calculate the Global effect on a certain point
     *
     * @param ray   given ray
     * @param level level of recursion
     * @param k     recursive k factor
     * @param kx    kR or kT factors
     * @return the color after calculating the effect on the point
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
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
        return new Ray(v, n, gp);
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
        double nv = v.dotProduct(n);
        //The ray is created with the point and vector r (= v-2(nv)n)
        return new Ray(v.subtract(n.scale(nv * 2)), n, gp);
    }

    /**
     * Find the closest intersection point to the ray staring point
     *
     * @param ray given ray
     * @return the closest intersection point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        return ray.findClosestGeoPoint(scene.geometries.findGeoIntersections(ray));
    }

    /**
     * Calculate the Local effects of the lights in the area
     *
     * @param gp  the point
     * @param ray the ray from the camera
     * @return the added effects of the light on the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Color color = gp.geometry.getEmission();
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 hitRate = hitPercentageColor(lightSource, gp, n);//the percentage of rays that intersect with the lightSource.
                Double3 ktr = transparency(gp, l, n, lightSource);
                Color iL = lightSource.getIntensity(gp.point);
                if (!hitRate.equals(new Double3(-1, -1, -1))) {
                    iL = iL.scale(hitRate);
                } else {
                    //Double3 kKtr = ktr.product(k);
                    iL = iL.scale(ktr);//!kKtr.lowerThan(MIN_CALC_COLOR_K) ? kKtr : ktr);
                }
                color = color.add(iL.scale(calcDiffusive(material, nl)), iL.scale(calcSpecular(material, n, l, nl, v)));
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
     * Checks if there are any object that might create a shadow on a certain point
     *
     * @param gp          certain point
     * @param lightSource The light source
     * @param n           Normal vector from the geometry
     * @param l           Vector l
     * @return True if there are no objects between the point and the light source
     */
    private boolean unshaded(GeoPoint gp, LightSource lightSource, Vector n, Vector l) {
        List<Point> intersections = scene.geometries.findIntersections(new Ray(l.scale(-1), n, gp.point));
        if (intersections == null) return true;
        double d = lightSource.getDistance(gp.point);
        for (Point p : intersections) {
            if (d > lightSource.getDistance(p))
                return false;
        }
        return true;
    }

    /**
     * Return the transparency of a certain point
     *
     * @param geoPoint given point
     * @param l        vector L
     * @param n        vector n to the geometry
     * @param ls       light source
     * @return the transparency of the point
     */
    private Double3 transparency(GeoPoint geoPoint, Vector l, Vector n, LightSource ls) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(new Ray(l.scale(-1), n, geoPoint.point));
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        double maxDistance = ls.getDistance(geoPoint.point);
        for (var p : intersections) {
            if (geoPoint.point.distance(p.point) < maxDistance) {
                ktr = ktr.product(p.geometry.getMaterial().kT);
                if (ktr.lowerThan(MIN_CALC_COLOR_K)) return Double3.ZERO;
            }
        }
        return ktr;
    }

    /**
     * Calculates the percentage of rays that hit the object,
     * from all the rays that were created by the points of hte light source.
     *
     * @param ls       the light source.
     * @param geoPoint the intersection point.
     * @return the percentage of rays that are heat by some object.
     */
    private Double3 hitPercentageColor(LightSource ls, GeoPoint geoPoint, Vector n) {
        if (ls instanceof DirectionalLight)
            return Double3.ONE;
        if (!(ls instanceof SpotLight)) {//means that this is the point light.
            ((PointLight) ls).initializePoints(geoPoint.point);//so need to initialize the points vector.
        }
        PointLight pl = (PointLight) ls;
        if (pl.getPoints() == null)//means that the light has no size thus we have to return the code that indicates so.
            return new Double3(-1, -1, -1);
        //if this is a relevant light source, and it has size, we are iterating over the points of the light source and averaging the transparency of all of them.
        Double3 average = Double3.ZERO;
        Point[] points = pl.getPoints();
        for (Point point : points) {
            average = average.add(transparency(geoPoint, geoPoint.point.subtract(point), n, ls).reduce(points.length));
        }

        return average;
    }
}
