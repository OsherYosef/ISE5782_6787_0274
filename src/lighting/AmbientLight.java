package lighting;

import primitives.*;

/**
 * Class ambient Light for basic lighting , the colors will be calculated
 * by the intensity of the light
 * This class extends the abstract class light
 *
 * @auther Osher and Dov
 */
public class AmbientLight extends Light {

    //***********Constructor************/

    /**
     * Default constructor for class AmbientLight
     * Default intensity is Black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * Constructor for class AmbientLight ,Calculates intensity with given Parameters
     *
     * @param iA The original intensity of the light
     * @param kA factor of the light
     */
    public AmbientLight(Color iA, Double3 kA) {
        super(iA.scale(kA));
    }

    /**
     * Constructor for class AmbientLight ,Calculates intensity with given Parameters
     *
     * @param iA The original intensity of the light
     * @param kA factor of the light
     */
    public AmbientLight(Color iA, double kA) {
        super(iA.scale(kA));
    }


}
