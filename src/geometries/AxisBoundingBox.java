package geometries;

import primitives.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static primitives.Util.isZero;

public class AxisBoundingBox extends Intersectable {
    /**
     * min values of the box
     */
    private double minX, minY, minZ;
    /**
     * mid-values of the box
     */
    private final double midX;
    private final double midY;
    private final double midZ;
    /**
     * max values of the box
     */
    private double maxX, maxY, maxZ;

    private List<Intersectable> contains;

    /**
     * Create an AABB given the furthest axis values
     *
     * @param minPoint minimum point
     * @param maxPoint maximum point
     */
    public AxisBoundingBox(Point minPoint, Point maxPoint) {
        this.minX = minPoint.getX();
        this.minY = minPoint.getY();
        this.minZ = minPoint.getZ();

        this.maxX = maxPoint.getX();
        this.maxY = maxPoint.getY();
        this.maxZ = maxPoint.getZ();

        this.midX = (minX + maxX) / 2;
        this.midY = (minY + maxY) / 2;
        this.midZ = (minZ + maxZ) / 2;

        contains = new ArrayList<>();
    }

    /**
     * Create an AABB that encapsulates a list of Boxes
     *
     * @param boxes list of boxes to check boundaries of
     */
    public AxisBoundingBox(List<AxisBoundingBox> boxes) {
        this.maxX = boxes.get(0).getMaxX();
        this.maxY = boxes.get(0).getMaxY();
        this.maxZ = boxes.get(0).getMaxZ();
        this.minX = boxes.get(0).getMinX();
        this.minY = boxes.get(0).getMinY();
        this.minZ = boxes.get(0).getMinZ();

        for (int i = 1; i < boxes.size(); i++) {
            if (boxes.get(i).getMaxX() > maxX) {
                this.maxX = boxes.get(i).getMaxX();
            }
            if (boxes.get(i).getMaxY() > maxY) {
                this.maxY = boxes.get(i).getMaxY();
            }
            if (boxes.get(i).getMaxZ() > maxZ) {
                this.maxZ = boxes.get(i).getMaxZ();
            }
            if (boxes.get(i).getMinX() < minX) {
                this.minX = boxes.get(i).getMinX();
            }
            if (boxes.get(i).getMinY() < minY) {
                this.minY = boxes.get(i).getMinY();
            }
            if (boxes.get(i).getMinZ() < minZ) {
                this.minZ = boxes.get(i).getMinZ();
            }

        }

        this.midX = (minX + maxX) / 2;
        this.midY = (minY + maxY) / 2;
        this.midZ = (minZ + maxZ) / 2;

        contains = new ArrayList<>();
    }

    public void addToContains(Intersectable... boundable) {
        Collections.addAll(this.contains, boundable);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        //the ray's head and direction points
        Point dir = ray.getDir();
        Point point = ray.getP0();

        //Direction fractions
        double dx = 1.0 / dir.getX();
        double dy = 1.0 / dir.getY();
        double dz = 1.0 / dir.getZ();

        //Points
        double pX = point.getX();
        double pY = point.getY();
        double pZ = point.getZ();

        double t1 = (minX - pX) * dx;
        double t2 = (maxX - pX) * dx;
        double t3 = (minY - pY) * dy;
        double t4 = (maxY - pY) * dy;
        double t5 = (minZ - pZ) * dz;
        double t6 = (maxZ - pZ) * dz;

        double tMin = Math.min(Math.min(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
        double tMax = Math.max(Math.max(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));

        // if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
        if (tMax < 0) {
            return null;
        }
        // if tmin > tmax, ray doesn't intersect AABB
        if (tMin > tMax) {
            return null;
        }

        List<GeoPoint> lst = new ArrayList<>();
        for (Intersectable geo : contains) {
            List<GeoPoint> pointLst = geo.findGeoIntersections(ray);
            if (pointLst != null)
                lst.addAll(pointLst);
        }
        return lst;
    }

    @Override
    public AxisBoundingBox getBoundingBox() {
        return this;//a bounding box bounds itself
    }

    /**
     * Creates an AABB tree given a list of boundable objects.
     * Used to create an AABB tree for a scene with geometries.
     *
     * @return AABB tree if size > 0, else null
     */
    public AxisBoundingBox createTree() {
        //List<Intersectable> boundables
        //if we got 0 boundables to bound
        if (contains.size() == 0)
            return null;
        else {
            //turn the list of boundables into a list of boxes that encapsulate the boundables
            ArrayList<AxisBoundingBox> boxes = new ArrayList<>();
            for (Intersectable boundable : contains) {
                boxes.add(boundable.getBoundingBox());
            }
            return createTreeRec(boxes);
        }
    }


    /**
     * Create a tree of boxes given a list of boxes to turn into a tree
     *
     * @param boxes list of boxes
     * @return AABB tree
     */
    private AxisBoundingBox createTreeRec(List<AxisBoundingBox> boxes) {
        //create a box that encapsulates all the other ones
        AxisBoundingBox node = new AxisBoundingBox(boxes);

        //find the longest edge of the box
        double x = node.maxX - node.minX;
        double y = node.maxY - node.minY;
        double z = node.maxZ - node.minZ;
        int edge = x > y && x > z ? 0 : (y > x && y > z ? 1 : 2);

        //base of the recursion, if the list has 1 box, return it
        if (boxes.size() == 1) {
            return boxes.get(0);
        }

        //base of the recursion, if the list has 2 boxes
        if (boxes.size() <= 2) {
            for (AxisBoundingBox box : boxes) {
                node.addToContains(box);//add them to this box
            }
        }

        //recursion step, if we have more boxes left
        else {
            ArrayList<AxisBoundingBox> left, right;

            //sort the boxes according to how they align on that edge
            sortBoxesByAxis(boxes, edge);

            //split the list into 2 even pieces
            left = new ArrayList<>(boxes.subList(0, boxes.size() / 2));
            right = new ArrayList<>(boxes.subList(boxes.size() / 2, boxes.size()));

            //go down the recursion
            node.addToContains(createTreeRec(left));
            node.addToContains(createTreeRec(right));

        }
        return node;
    }

    /**
     * Sorts a bounding box list according to the axis given
     *
     * @param boxes List of boxes to sort
     * @param axis  Axis to sort by - 0=x,1=y,2=z
     */
    public static void sortBoxesByAxis(List<AxisBoundingBox> boxes, int axis) {
        switch (axis) {
            case 0:
                boxes.sort((AxisBoundingBox x, AxisBoundingBox y) -> Double.compare(y.midX, x.midX));
            case 1:
                boxes.sort((AxisBoundingBox x, AxisBoundingBox y) -> Double.compare(y.midY, x.midY));
            case 2:
                boxes.sort((AxisBoundingBox x, AxisBoundingBox y) -> Double.compare(y.midZ, x.midZ));
        }
    }

    //====================================Getters=====================================

    /**
     * @return The value of minX.
     */
    public double getMinX() {
        return minX;
    }

    /**
     * @return The value of minY.
     */
    public double getMinY() {
        return minY;
    }

    /**
     * @return The value of minZ.
     */
    public double getMinZ() {
        return minZ;
    }

    /**
     * @return The value of maxX.
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * @return The value of maxY.
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * @return The value of maxZ.
     */
    public double getMaxZ() {
        return maxZ;
    }

    /**
     * @return The value of midZ.
     */
    public double getMidX() {
        return midX;
    }

    /**
     * @return The value of midZ.
     */
    public double getMidY() {
        return midY;
    }

    /**
     * @return The value of midZ.
     */
    public double getMidZ() {
        return midZ;
    }

    /**
     * @return The list of contains
     */
    public List<Intersectable> getContains() {
        return contains;
    }

    //===============================================end of Getters=========================

}

