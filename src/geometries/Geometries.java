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

    //protected List<AxisBoundingBox> bounds = new ArrayList<>();
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
        List<AxisBoundingBox> temp = new ArrayList<>();
        for (Intersectable i : intersectables)
            temp.add(i.getBoundingBox());
        boundingBox = new AxisBoundingBox(temp);
    }


    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
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
        return boundingBox;
    }


}

