package geometries;

import primitives.*;

import java.util.*;

/**
 * Geometries class for geometries groups using Linked list
 *
 * @auther Osher and Dov
 */

public class Geometries extends Intersectable {
    private final List<Intersectable> intersectablesList = new LinkedList<>();
    private AxisBoundingBox boundingBox;
    /**
     * if the AABB tag is true(we default it for now) then calculate the image according to AABB principles
     */
    private boolean AABB = true;
    //**************Constructors*************//

    /**
     * Default constructor for class Geometries
     */
    public Geometries() {
    }

    /**
     * Constructor for class Geometries
     *
     * @param geometries list of Geometrical shapes
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    //************Operations***************//

    /**
     * Adds a collection of geometrical shapes to the list
     *
     * @param intersectables Collection of shapes
     */
    public void add(Intersectable... intersectables) {
        Collections.addAll(intersectablesList, intersectables);
        if (AABB) {
            if (boundingBox == null) {
                boundingBox = getBoundingBox();
                boundingBox.addToContains(intersectables);
            }
        }
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        if (AABB) {
            return boundingBox == null ? null : boundingBox.findGeoIntersections(ray);
        } else {
            List<GeoPoint> result = null;
            for (var item : intersectablesList) {
                List<GeoPoint> itemList = item.findGeoIntersections(ray);
                if (itemList != null) {
                    if (result == null)
                        result = new LinkedList<>(itemList);
                    else
                        result.addAll(itemList);
                }

            }
            return result;
        }

    }

    /**
     * If it set to be true the intersections will be calculated according to
     *
     * @param aabb True or false
     */
    public void setAABB(boolean aabb) {
        this.AABB = aabb;
    }

    @Override
    public AxisBoundingBox getBoundingBox() {
        AxisBoundingBox firstShape = intersectablesList.get(0).getBoundingBox();
        AxisBoundingBox temp;
        //List<Intersectable> tempLst = new ArrayList<>();
        double t;
        double minX = firstShape.getMinX();
        double minY = firstShape.getMinY();
        double minZ = firstShape.getMinZ();
        double maxX = firstShape.getMaxX();
        double maxY = firstShape.getMaxY();
        double maxZ = firstShape.getMaxZ();
        for (int i = 1; i < intersectablesList.size(); i++) {
            temp = intersectablesList.get(i).getBoundingBox();
            t = temp.getMinX();
            if (minX < t)
                minX = t;

            t = temp.getMinY();
            if (minY < t)
                minY = t;

            t = temp.getMinZ();
            if (minZ < t)
                minZ = t;

            t = temp.getMaxX();
            if (maxX > t)
                maxX = t;

            t = temp.getMaxY();
            if (maxY > t)
                maxY = t;

            t = temp.getMaxZ();
            if (maxZ > t)
                maxZ = t;
        }
        return new AxisBoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }
}

