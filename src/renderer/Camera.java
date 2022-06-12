package renderer;

import primitives.*;

import java.util.MissingResourceException;

/**
 * Class Camera for a camera
 *
 * @author Dov and Osher
 */
public class Camera {
    private final Point location;
    private final Vector vUp;
    private final Vector vTo;
    private final Vector vRight;
    /**
     * Height of the view plane
     */
    private double height;
    /**
     * Width of the view plane
     */
    private double width;
    /**
     * Distance between camera and the view plane
     */
    private double distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;

    /**
     * The print interval
     */
    private double printInterval = 1;
    /**
     * The amount of threads that will be used
     */
    private int threadsCount = 3;
    //*********Constructor*******//

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
        this.vRight = vTo.crossProduct(vUp).normalize();
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
        pC = distance == 0 ? location : location.add(vTo.scale(distance));
        Point pIJ = pC;
        double Rx = width / nX;
        double Ry = height / nY;

        double xJ = (j - ((nX - 1.0) / 2.0)) * Rx;
        double yI = -(i - ((nY - 1.0) / 2.0)) * Ry;

        if (xJ != 0) pIJ = pIJ.add(vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(vUp.scale(yI));
        return new Ray(location, pIJ.subtract(location));
    }


    /**
     * Render the image of the camera
     *
     * @throws MissingResourceException If the ray tracer or image writer were not initialized
     */
    public Camera renderImage() {
        if (imageWriter == null || rayTracer == null)
            throw new MissingResourceException("Resource missing", "ImageWriter or RayTracer", "imageWriter or rayTracer");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nY; j++) {
                imageWriter.writePixel(j, i, castRay(nX, nY, j, i));
            }
        }
        return this;
    }

    /**
     * Render the image of the camera with multiple number of threads
     *
     * @throws MissingResourceException If the ray tracer or image writer were not initialized
     */
    public Camera renderImageThreads() {
        if (imageWriter == null || rayTracer == null)
            throw new MissingResourceException("Resource missing", "ImageWriter or RayTracer", "imageWriter or rayTracer");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        Pixel.initialize(nY, nX, printInterval);
        while (threadsCount-- > 0) {
            new Thread(() -> {
                for (Pixel pixel = new Pixel(); pixel.nextPixel(); Pixel.pixelDone())
                    imageWriter.writePixel(pixel.col, pixel.row, castRay(nX, nY, pixel.col, pixel.row));
            }).start();
        }
        Pixel.waitToFinish();
        return this;
    }

    /**
     * Casts a ray through a pixel and gets the color of the ray intersection point
     *
     * @param nX number of columns of the imageWriter
     * @param nY number of rows of the imageWriter
     * @param j  the index of the column
     * @param i  the index of the row
     * @return The color of the Ray intersection
     */
    private Color castRay(int nX, int nY, int j, int i) {
        return rayTracer.traceRay(constructRay(nX, nY, j, i));
    }

    /**
     * Print a grid with a given color
     *
     * @param interval the distance between each line or column of the grid
     * @param color    the color of the grid
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void printGrid(int interval, Color color) {
        if (imageWriter == null)
            throw new MissingResourceException("Resource missing", "ImageWriter", "imageWriter");
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if ((i % interval == 0 && i != 0) || (j % interval == 0 && j != 0))
                    imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Create an image with image the resources and imageWriter
     *
     * @throws MissingResourceException if the image writer is not initialized
     */
    public void writeToImage() {
        if (imageWriter == null)
            throw new MissingResourceException("Camera's image writer is null", "ImageWriter", "imageWriter");
        imageWriter.writeToImage();
    }
    //*********Getters/Setters***********//

    /**
     * Sets the size of the view plane
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
     * Changes the distance between camera and view Plane
     *
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
     * Changes the camera's print interval
     *
     * @param printInterval the given printInterval
     * @return The camera with new print interval
     */
    public Camera setPrintInterval(double printInterval) {
        this.printInterval = printInterval;
        return this;
    }

    /**
     * Change the number of threads that will be used
     *
     * @param n the number of threads
     * @return the camera with changed number of threads
     */
    public Camera setThreadsCount(int n) {
        this.threadsCount = n;
        return this;
    }

    /**
     * Get the location of the camera
     *
     * @return The location of the camera
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Get The Vector Vup of the camera
     *
     * @return The Vector Vup of the camera
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * Get the Vector Vto of the Camera
     *
     * @return The Vector Vto of the Camera
     */
    public Vector getVTo() {
        return vTo;
    }

    /**
     * Get the vector v Right of the Camera
     *
     * @return The vector V right of the Camera
     */
    public Vector getVRight() {
        return vRight;
    }

    /**
     * Get the V height vector of the camera
     *
     * @return The vector V height of the Camera
     */
    public double getHeight() {
        return height;
    }

    /**
     * Get the width of the view Plane
     *
     * @return The width of the view Plane
     */
    public double getWidth() {
        return width;
    }

    /**
     * Get the distance between the camera and view plane
     *
     * @return The distance between the view plane and object
     */
    public double getDistance() {
        return distance;
    }

}
