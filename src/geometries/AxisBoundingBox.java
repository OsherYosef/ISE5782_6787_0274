package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AxisBoundingBox { //extends Intersectable {
    private static final double INF = Double.POSITIVE_INFINITY;
    private static final double NINF = Double.NEGATIVE_INFINITY;

    public static final AxisBoundingBox INFINITE_BOUNDS = new AxisBoundingBox(new Point(INF), new Point(NINF));

    /**
     * min values of the box
     */
    private double minX, minY, minZ;

    /**
     * max values of the box
     */
    private double maxX, maxY, maxZ;

    private List<AxisBoundingBox> contains = new ArrayList<>();

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
        addToContains(boxes);
    }

    public boolean checkIntersection(Ray ray) {
        if (!isCollection())
            return boundingRayIntersection(ray);
        else {
            for (AxisBoundingBox boundingBox : contains)
                if (boundingBox.checkIntersection(ray))
                    return true;
            return false;
        }
    }

    public void addToContains(List<AxisBoundingBox> axisBoundingBoxes) {
        for (AxisBoundingBox a : axisBoundingBoxes)
            contains.add(a);
    }



    public boolean isCollection() {
        return !contains.isEmpty();
    }

    /**
     * Check if the Ray intersects with the boundary box
     *
     * @return True if it does intersect
     */
    public boolean boundingRayIntersection(Ray ray) {
        Vector dir = ray.getDir();
        Point point = ray.getP0();
        double txMax, tyMax, tzMax, txMin, tyMin, tzMin;
        double dx = dir.getX();
        double dy = dir.getY();
        double dz = dir.getZ();
        double pX = point.getX();
        double pY = point.getY();
        double pZ = point.getZ();

        double t1 = (maxX - pX) / dx;
        double t2 = (minX - pX) / dx;
        txMin = Math.min(t1, t2);
        txMax = Math.max(t1, t2);

        t1 = (maxY - pY) / dy;
        t2 = (minY - pY) / dy;
        tyMin = Math.min(t1, t2);
        tyMax = Math.max(t1, t2);
        if ((txMin > tyMax) || tyMin > txMax)
            return false;

        if (tyMin > txMin)
            txMin = tyMin;

        if (tyMax < txMax)
            txMax = tyMax;

        t1 = (maxZ - pZ) / dz;
        t2 = (minZ - pZ) / dz;
        tzMin = Math.min(t1, t2);
        tzMax = Math.max(t1, t2);

        if ((txMin > tzMax) || (tzMin > txMax))
            return false;
        return true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AxisBoundingBox box)) return false;
        return Double.compare(box.minX, minX) == 0 && Double.compare(box.minY, minY) == 0 && Double.compare(box.minZ, minZ) == 0 && Double.compare(box.maxX, maxX) == 0 && Double.compare(box.maxY, maxY) == 0 && Double.compare(box.maxZ, maxZ) == 0;
    }


//===============================================end of Getters=========================

}

