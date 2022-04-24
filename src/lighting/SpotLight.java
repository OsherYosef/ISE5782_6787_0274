package lighting;

import primitives.*;

import static primitives.Util.alignZero;

/**
 * SpotLight class for a spotlight that has a direction ,location
 * and its intensity weakens with distance
 * extends PointLight class
 */
public class SpotLight extends PointLight {
    private final Vector direction;
    private double narrowBeam = 1;

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
    }

    @Override
    public Color getIntensity(Point p) {
        // (i0 * max(0,dir*l) / (kC + kL*d + kQ*d*d)
        double dl = direction.dotProduct(getL(p)); //dl = dir*l
        return alignZero(dl) <= 0 ? Color.BLACK //
                : super.getIntensity(p).scale(narrowBeam == 1 ? dl : Math.pow(dl, narrowBeam));
    }

    /**
     * Change the narrow beam of the SpotLight - which is the power of dir * l
     *
     * @param n the new NarrowBeam Value
     * @return the SpotLight with change narrowBeam
     */
    public SpotLight setNarrowBeam(double n) {
        this.narrowBeam = n;
        return this;
    }

}
