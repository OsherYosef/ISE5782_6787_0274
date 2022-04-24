package lighting;

import primitives.*;

/**
 * SpotLight class for a spotlight that has a direction ,location
 * and its intensity weakens with distance
 * extends PointLight class
 */
public class SpotLight extends PointLight {
    private final Vector direction;
    private double narrowBeam;

    /**
     * Constructor for class SpotLight
     *
     * @param c         the intensity of the light
     * @param p         the location of the light
     * @param direction the direction of the light
     */
    public SpotLight(Color c, Point p, Vector direction) {
        super(c, p);
        this.direction = direction.normalize();
        this.narrowBeam = 1;
    }

    @Override
    public Color getIntensity(Point p) {
        // (i0 * max(0,dir*l) / (kC + kL*d + kQ*d*d)
        double l = Math.pow(direction.dotProduct(getL(p)), narrowBeam);//l = dir*l
        Color i0 = getIntensity();
        return l < 0 ? i0.scale(0) : super.getIntensity(p).scale(l);
    }

    /**
     * Change the narrow beam of the SpotLight
     *
     * @param n the new NarrowBeam Value
     * @return the SpotLight with change narrowBeam
     */
    public SpotLight setNarrowBeam(double n) {
        this.narrowBeam = n;
        return this;
    }

}
