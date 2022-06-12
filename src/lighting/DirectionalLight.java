package lighting;

import primitives.*;

/**
 * Class Directional Light for a Strong light that has a direction
 * The intensity of the light is not effected by distance
 * extends the abstract class Light and implements the interface LightSource
 */
public class DirectionalLight extends Light implements LightSource {
    private final Vector direction;

    /**
     * Constructor for class DirectionalLight
     *
     * @param c         the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color c, Vector direction) {
        super(c);
        this.direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction;
    }

    @Override
    public double getDistance(Point p) {
        return Double.POSITIVE_INFINITY;
    }

    @Override
    public Point[] getPoints(Point ignore, int ignOre){
        return null;
    }

}
