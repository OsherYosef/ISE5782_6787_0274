package geometries;

import primitives.*;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Geometries class for geometries groups using Linked list
 *
 * @auther Osher and Dov
 */

public class Geometries extends Intersectable {
    private final List<Intersectable> intersectablesList = new LinkedList<>();

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
        for (var item : intersectablesList) {
            List<GeoPoint> itemList = item.findGeoIntersections(ray);
            if (itemList != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemList);
            }

        }
        return result;
    }
}

