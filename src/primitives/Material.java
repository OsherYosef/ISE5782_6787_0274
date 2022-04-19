package primitives;

/**
 * Class Material for shininess of a material
 */
public class Material {
    public Double3 kD = new Double3(0);
    public Double3 kS = new Double3(0);
    public int nShininess = 0;

    /**
     * Set the kD of the material using Double3
     *
     * @param kD kD factor of the material
     * @return the Material after changing the kD factor
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Set the kD of the material using double
     *
     * @param kD kD factor of the material
     * @return the Material after changing the kD factor
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }

    /**
     * Set the kS of the material using Double3
     * @param kS kS factor of the material
     * @return the Material after changing the kS factor
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Set the kS of the material using Double3
     * @param kS kS factor of the material
     * @return the Material after changing the kS factor
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }

    /**
     * Set the shininess of the material
     * @param nShininess the shininess
     * @return the Material after changing the shininess factor
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
