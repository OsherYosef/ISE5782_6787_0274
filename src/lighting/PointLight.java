package lighting;

import primitives.*;

import java.util.Random;

/**
 * Class PointLight for a light that has a location
 * and its Intensity weakens with distance
 * Extends abstract Class Light and implements LightSource
 */
public class PointLight extends Light implements LightSource {
    protected final Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;
    //*****Parameter for Soft shadow
    /**
     * The size of the light
     */
    private double size = 0;
    protected Point[] points;
    /**
     * A random number
     */
    private final Random rand = new Random();

    /**
     * Constructor for class PointLight
     * Light Factor(kc,kl,kq) will be set to default values(1,0,0)
     *
     * @param intensity the intensity of the Light
     * @param pos       The position of the light
     */
    public PointLight(Color intensity, Point pos) {
        super(intensity);
        position = pos;
    }

    /**
     * Sets the factor kC and Returns the PointLight after change
     *
     * @param d new kC value
     * @return PointLight with factor kC
     */
    public PointLight setKc(double d) {
        this.kC = d;
        return this;
    }

    /**
     * Sets the factor kL and Returns the PointLight after change
     *
     * @param d new kL value
     * @return PointLight with factor kL
     */
    public PointLight setKl(double d) {
        this.kL = d;
        return this;
    }

    /**
     * Sets the factor kQ and Returns the PointLight after change
     *
     * @param d new kQ value
     * @return PointLight with factor kQ
     */
    public PointLight setKq(double d) {
        this.kQ = d;
        return this;
    }

    /**
     * Sets the size of the array of points
     * If the given number is a positive number, this will activate SoftShadowing
     *
     * @param size Size of the array
     * @return the Point light with array of point
     */
    public PointLight setSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * Get the array of points that will cast shadow rays
     * NOTE: initializePoints function must be used first,
     * Otherwise this will return null
     *
     * @return The array of point
     */
    public Point[] getPoints(Point p, int numOfPoints) {
        if (size == 0) return null;
        if (this.points != null)
            return this.points;
        Point[] points = new Point[numOfPoints];
        Vector to = p.subtract(position).normalize();
        Vector vX = to.getOrthogonal().normalize();
        Vector vY = vX.crossProduct(to).normalize();
        double x, y, radius;
       // for (int i = 1; i < numOfPoints; i += 4) {
        //TODO : bruh just
      //  }
        for (int i = 0; i < numOfPoints; i += 4) {
            radius = rand.nextDouble(size) + 0.1;
            x = rand.nextDouble(radius) + 0.1;
            y = radius * radius - x * x;//getCircleScale(x, radius);
            for (int j = 0; j < 4; j++) {
                //in this part we mirror the point we got 4 times, to each quarter of the grid
                points[i + j] = position.add(vX.scale(j % 2 == 0 ? x : -x)).add(vY.scale((j <= 1 ? -y : y)));
            }
        }
        this.points = points;
        return points;
    }

    @Override
    public Color getIntensity(Point p) {
        // i0 / (kC + kL*d +kQ*d^2)
        double d = position.distance(p);
        return intensity.reduce(kC + kL * d + kQ * d * d);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point p) {
        return p.distance(position);
    }
}
