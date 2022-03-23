package renderer;

import src.primitives.*;
import src.geometries.*;

/**
 * @author Dov and Osher
 */
public class Camera {
    private Point location;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double height;
    private double width;
    private double distance; // from view plane

    //*********Constructor*******//

    /**
     * Constructor for class Camera
     *
     * @param locX x coordinate value of the camera's location
     * @param locY y coordinate value of the camera's location
     * @param locZ z coordinate value of the camera's location
     * @param vTo  Vector to the view plane
     * @param vUp  Vector that controls the height of the camera
     * @throws IllegalArgumentException if the given vectors are not orthogonal
     */
    public Camera(double locX, double locY, double locZ, Vector vTo, Vector vUp) {
        location = new Point(locX, locY, locZ);
        //If the Vectors are not orthogonal an IllegalArgumentException should be thrown here
        //TODO check if not orthogonal
        this.vRight = vTo.crossProduct(vUp).normalize();
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
    }

    //*********Operations********//

    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;//TODO Calculation
    }

    //*********Getters/Setters***********//
    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Point getLocation() {
        return location;
    }

    public Vector getvUp() {
        return vUp;
    }

    public Vector getvTo() {
        return vTo;
    }

    public Vector getvRight() {
        return vRight;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getDistance() {
        return distance;
    }
}
