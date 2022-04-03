package renderer;

import jdk.jshell.execution.LocalExecutionControl;
import primitives.*;
import geometries.*;

import java.util.MissingResourceException;

/**
 * Class Camera for a camera
 *
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
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
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
        if (vTo.dotProduct(vUp) != 0)
            throw new IllegalArgumentException("Vectors are not orthogonal");
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp).normalize();

    }

    /**
     * Constructor for class Camera with point
     *
     * @param p   The location of the camera
     * @param vTo Vector to the view plane
     * @param vUp Vector that controls the height of the camera
     * @throws IllegalArgumentException if the given vectors are not orthogonal
     */
    public Camera(Point p, Vector vTo, Vector vUp) {
        location = p;
        //If the Vectors are not orthogonal an IllegalArgumentException should be thrown here
        if (vTo.dotProduct(vUp) != 0)
            throw new IllegalArgumentException("Vectors are not orthogonal");
        this.vTo = vTo.normalize();
        this.vUp = vUp.normalize();
        this.vRight = vTo.crossProduct(vUp);

    }

    //*********Operations********//

    /**
     * Construct a ray through a certain Pixel
     *
     * @param nX number of rows
     * @param nY number of columns
     * @param j  index of rows
     * @param i  index of columns
     * @return Ray through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        Point pC;
        pC = distance != 0 ? location.add(vTo.scale(distance)) : location;

        Point pIJ = pC;
        double Rx = width / nX;
        double Ry = height / nY;
        double xJ = (j - ((nX - 1.0) / 2.0)) * Rx;
        double yI = -(i - ((nY - 1.0) / 2.0)) * Ry;
        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        return new Ray(pIJ.subtract(location), location);
    }

    /**
     * Render the image of the camera
     */
    public void renderImage() {
        if (imageWriter == null || rayTracer == null)
            throw new MissingResourceException("Resource missing", "ImageWriter or RayTracer", "imageWriter or rayTracer");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imageWriter.writePixel(j, i, rayTracer.traceRay(constructRay(imageWriter.getNx(), imageWriter.getNy(), j, i)));
            }
        }
    }

    /**
     * Print a grid with a given color
     *
     * @param interval the distance between each line or column of the grid
     * @param color    the color of the grid
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("", "", "");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == interval || j == interval)
                    imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Create an image with image the resources and imageWriter
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Camera's image writer is null", "ImageWriter", "imageWriter");
        imageWriter.writeToImage();
    }
    //*********Getters/Setters***********//

    /**
     * Gets the size of the view plane
     *
     * @param width  of the view plane
     * @param height of the view plane
     * @return The camera with width and height
     */
    public Camera setVPSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * @param distance The distance between the camera and the view Plane
     * @return The camera with distance from the view plane
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**
     * Changes a camera's imageWriter
     *
     * @param imageWriter the given image writer
     * @return The Camera with an imageWriter
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Changes a camera's Ray Tracer
     *
     * @param rayTracer the given Ray tracer
     * @return The Camera with a RayTracer
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     * @return The location of the camera
     */
    public Point getLocation() {
        return location;
    }

    /**
     * @return The Vector Vup of the camera
     */
    public Vector getvUp() {
        return vUp;
    }

    /**
     * @return The Vector Vto of the Camera
     */
    public Vector getvTo() {
        return vTo;
    }

    /**
     * @return The vector Vright of the Camera
     */
    public Vector getvRight() {
        return vRight;
    }

    /**
     * @return The vector Vheight of the Camera
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return The width of the view Plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * @return The distance between the view plane and object
     */
    public double getDistance() {
        return distance;
    }

}
