package primitives;

/**
 * Class Material for shininess of a material
 */
public class Material {
    /**
     * Diffusive
     */
    public Double3 kD = Double3.ZERO;
    /**
     * Specular
     */
    public Double3 kS = Double3.ZERO;
    /**
     * Transparency
     */
    public Double3 kT = Double3.ZERO;
    /**
     * Reflection
     */
    public Double3 kR = Double3.ZERO;
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
     *
     * @param kS kS factor of the material
     * @return the Material after changing the kS factor
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Set the kS of the material using double
     *
     * @param kS kS factor of the material
     * @return the Material after changing the kS factor
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }


    /**
     * Set the kT of the material using Double3
     *
     * @param kT kD factor of the material
     * @return the Material after changing the kD factor
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Set the kT of the material using double
     *
     * @param kT kT factor of the material
     * @return the Material after changing the kT factor
     */
    public Material setKt(double kT) {
        this.kT = new Double3(kT);
        return this;
    }

    /**
     * Set the kR of the material using Double3
     *
     * @param kR kD factor of the material
     * @return the Material after changing the kD factor
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Set the kR of the material using Double3
     *
     * @param kR kR factor of the material
     * @return the Material after changing the kR factor
     */
    public Material setKr(double kR) {
        this.kR = new Double3(kR);
        return this;
    }

    /**
     * Set the shininess of the material
     *
     * @param nShininess the shininess
     * @return the Material after changing the shininess factor
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
