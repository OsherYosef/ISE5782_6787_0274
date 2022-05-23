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

    /**
     * Sets the size of the array of points
     * If the given number is a positive number, this will activate SoftShadowing
     *
     * @param size Size of the array
     * @return the Point light with array of point
     */
    public SpotLight setSize(double size) {
        super.setSize(size);
        this.initializePoints();
        return this;
    }

    /**
     * Initialize the array of points that will cast shadow rays
     */
    private void initializePoints() {
        //Send the location of the light+ the direction vector of the light
        super.initializePoints(this.position.add(direction));
    }

}
