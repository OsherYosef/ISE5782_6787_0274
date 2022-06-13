package geometries;

import primitives.*;

import java.nio.file.attribute.AttributeView;
import java.util.*;

/**
 * Geometries class for geometries groups using Linked list
 *
 * @auther Osher and Dov
 */

public class Geometries extends Intersectable {
    private final List<Intersectable> intersectablesList = new LinkedList<>();

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
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> result = null;
        if (AABB) {
            return getBoundingBox().createTree(intersectablesList).findGeoIntersections(ray);
        } else {
            for (var item : intersectablesList) {
                List<GeoPoint> itemList = item.findGeoIntersections(ray);
                if (itemList != null) {
                    if (result == null)
                        result = new LinkedList<>(itemList);
                    else
                        result.addAll(itemList);
                }

            }
        }
        return result;
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
        AxisBoundingBox firstIntersection = intersectablesList.get(0).getBoundingBox();
        AxisBoundingBox temp;
        double minX = firstIntersection.getMinX();
        double minY = firstIntersection.getMinY();
        double minZ = firstIntersection.getMinZ();
        double maxX = firstIntersection.getMaxX();
        double maxY = firstIntersection.getMaxY();
        double maxZ = firstIntersection.getMaxZ();
        for (int i = 1; i < intersectablesList.size(); i++) {
            temp = intersectablesList.get(i).getBoundingBox();
            if (minX < temp.getMinX())
                minX = temp.getMinX();

            if (minY < temp.getMinY())
                minY = temp.getMinY();

            if (minZ < temp.getMinZ())
                minZ = temp.getMinZ();

            if (maxX > temp.getMaxX())
                maxX = temp.getMaxX();

            if (maxY > temp.getMaxY())
                maxY = temp.getMaxY();

            if (maxZ > temp.getMaxZ())
                maxZ = temp.getMaxZ();
        }
        return new AxisBoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }
}

