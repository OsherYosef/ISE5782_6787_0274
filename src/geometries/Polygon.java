package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane plane;
    private int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
        size = vertices.length;
        boundingBox = constructBoundingBox();
    }

    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (!boundingBox.boundingRayIntersection(ray))
            return null;
        List<GeoPoint> intersections = this.plane.findGeoIntersections(ray);
        if (intersections == null)
            return null;
        Vector v = ray.getDir();
        LinkedList<Vector> vectorList = new LinkedList<>();
        //vi = pi - p0
        for (Point p : this.vertices) {
            vectorList.add(p.subtract(ray.getP0()));
        }
        double nv = v.dotProduct((vectorList.get(0).crossProduct(vectorList.get(1))).normalize());
        if (isZero(nv))
            return null;
        boolean positiveSign = nv > 0;
        //checks if all ni*vi have same sign
        for (int i = 1; i < this.size; i++) {
            nv = v.dotProduct((vectorList.get(i).crossProduct(vectorList.get((i + 1) % this.size))).normalize());
            if (isZero(nv)) return null; //if Normal and Vi are orthogonal
            if (nv > 0 && !positiveSign || nv < 0 && positiveSign) return null; //if there is a logic contradiction
        }
        intersections.get(0).geometry = this;
        return intersections;
    }

    /**
     * This function constructs the bounding box of a polygon
     *
     * @return the bounding box of the polygon
     */
    protected AxisBoundingBox constructBoundingBox() {
        Point firstVertice = vertices.get(0);
        Point temp;
        double tempX, tempY, tempZ;
        double minX = firstVertice.getX();
        double minY = firstVertice.getY();
        double minZ = firstVertice.getZ();
        double maxX = minX;
        double maxY = minY;
        double maxZ = minZ;
        for (int i = 1; i < 3; i++) {
            temp = vertices.get(i);
            tempX = temp.getX();
            tempY = temp.getY();
            tempZ = temp.getZ();
            if (tempX < minX)
                minX = tempX;
            if (tempX > maxX)
                maxX = tempX;

            if (tempY < minY)
                minY = tempY;
            if (tempY > maxY)
                maxY = tempY;

            if (tempZ < minZ)
                minZ = tempZ;
            if (tempZ > maxZ)
                maxZ = tempZ;
        }
        return new AxisBoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }
}
