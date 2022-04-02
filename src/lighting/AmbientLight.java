package lighting;

import primitives.*;

/**
 * Class ambient Light for basic lighting
 */
public class AmbientLight {
    private final Color intensity;

    //***********Constructor************/

    /**
     * Default constructor for class AmbientLight
     */
    public AmbientLight() {
        intensity = Color.BLACK;
    }

    /**
     * Constructor for class AmbientLight
     *
     * @param iA The original intensity of the light
     * @param kA factor of the light
     */
    public AmbientLight(Color iA, Double3 kA) {
        intensity = iA.scale(kA);
    }


    public Color getIntensity() {
        return intensity;
    }

}
