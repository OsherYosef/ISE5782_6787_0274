package lighting;

import primitives.*;

/**
 * Class ambient Light for basic lighting , the colors will be calculated
 * by the intensity of the light
 * @auther  Osher and Dov
 */
public class AmbientLight {
    private final Color intensity;
    //***********Constructor************/

    /**
     * Default constructor for class AmbientLight
     * Default intensity is Black
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * Constructor for class AmbientLight ,Calculates intensity with given Parameters
     *
     * @param iA The original intensity of the light
     * @param kA factor of the light
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }

    /**
     * Get the intensity of the ambient light
     *
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
