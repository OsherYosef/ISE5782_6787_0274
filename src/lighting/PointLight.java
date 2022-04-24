package lighting;

import primitives.*;

/**
 * Class PointLight for a light that has a location
 * and its Intensity weakens with distance
 * Extends abstract Class Light and implements LightSource
 */
public class PointLight extends Light implements LightSource {
    private final Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructor for class PointLight
     * Light Factor(kc,kl,kq) will be set to default values(1,0,0)
     *
     * @param intensity the intensity of the Light
     * @param pos       The position of the light
     */
    public PointLight(Color intensity, Point pos) {
        super(intensity);
        position = pos;
    }

    /**
     * Sets the factor kC and Returns the PointLight after change
     *
     * @param d new kC value
     * @return PointLight with factor kC
     */
    public PointLight setKc(double d) {
        this.kC = d;
        return this;
    }

    /**
     * Sets the factor kL and Returns the PointLight after change
     *
     * @param d new kL value
     * @return PointLight with factor kL
     */
    public PointLight setKl(double d) {
        this.kL = d;
        return this;
    }

    /**
     * Sets the factor kQ and Returns the PointLight after change
     *
     * @param d new kQ value
     * @return PointLight with factor kQ
     */
    public PointLight setKq(double d) {
        this.kQ = d;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        // i0 / (kC + kL*d +kQ*d^2)
        double d = position.distance(p);
        return intensity.reduce(kC + kL * d + kQ * d * d);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
