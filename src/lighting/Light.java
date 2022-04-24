package lighting;

import primitives.*;


/**
 * Abstract Class Light for light sources
 */
abstract class Light {
    protected final Color intensity;

    /**
     * Constructor for class Light
     * Gets a color and sets the intensity of the light to that color
     * @param c The intensity of light
     */
    protected Light(Color c) {
        intensity = c;
    }

    /**
     * Returns the intensity of the light
     * @return the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
