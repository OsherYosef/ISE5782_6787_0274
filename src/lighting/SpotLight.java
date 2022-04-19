package lighting;

import primitives.*;

/**
 * SpotLight class for a spotlight that has a direction ,location
 * and its intensity weakens with distance
 * extends PointLight class
 */
public class SpotLight extends PointLight {
    private final Vector direction;

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
        double l = direction.dotProduct(getL(p));//l = dir*l
        Color i0 = getIntensity();
        return l < 0 ? i0.scale(0) : super.getIntensity(p).scale(l);
    }
    public SpotLight setNarrowBeam(int n){
        return this;//TODO bonus
    }

}
