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
    protected double size = 0;
    private Point[] points;
    private final int NUMBER_OF_POINTS = 1000;
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
     *
     * @param size Size of the
     * @return the Point light with array of point
     */
    public PointLight setSize(double size) {
        this.size = size;
        return this;
    }

    /**
     * Initialize The array of points
     */
    public void initializePoints(Point p) {
        if (size == 0)
            return;
        this.points = new Point[this.NUMBER_OF_POINTS];
        Vector n = p.subtract(position);
        Vector vX = getOrthogonal(n);
        Vector vY = vX.crossProduct(n);
        double x, y, radius;
        for (int i = 0; i < NUMBER_OF_POINTS; i += 4) {
            radius = rand.nextDouble(size);
            x = rand.nextDouble(radius);
            y = getCircleScale(x, radius);
            for (int j = 0; j < 4; j++) {
                points[i + j] = position.add(vX.scale(j % 2 == 0 ? x : -x)).add(vY.scale((j <= 1 ? -y : y)));//TODO debug
            }
        }
    }

    private Vector getOrthogonal(Vector vector) {
        return vector.GetX() == 0 ? new Vector(1, 0, 0) : new Vector(-vector.GetY(), vector.GetX(), 0);
    }

    private double getCircleScale(double x, double radius) {
        return Math.sqrt(radius * radius - x * x);
    }

    public Point[] getPoints() {
        return this.points;
    }

    public int getNUMBER_OF_POINTS() {
        return this.NUMBER_OF_POINTS;
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
