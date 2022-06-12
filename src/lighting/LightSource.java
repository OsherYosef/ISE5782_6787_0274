package lighting;

import primitives.*;

/**
 * Interface LightSource
 * Every light source that we will create will implement this Interface
 */
public interface LightSource {
    /**
     * Get a Point and Returns the intensity of the light at that point
     *
     * @param p given point
     * @return the intensity of the light in that point
     */
    Color getIntensity(Point p);

    /**
     * Gets a Point and return a vector L to that point
     *
     * @param p given Point
     * @return the vector L
     */
    Vector getL(Point p);

    /**
     * Gets a Point and return the distance between that point and the Light source
     *
     * @param p the given point
     * @return The distance between the point and the light
     */
    double getDistance(Point p);

    Point[] getPoints(Point p, int numOfPoints);

}