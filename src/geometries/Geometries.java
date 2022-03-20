package src.geometries;

import src.primitives.*;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Geometries class for geometries groups using Linked list
 *
 * @auther Osher and Dov
 */

public class Geometries implements Intersectable {
    private List<Intersectable> intersectablesList;
    //**************Constructors*************//

    /**
     * Default constructor for class Geometries
     */
    public Geometries() {
        intersectablesList = new LinkedList<>();
    }

    /**
     * Constructor for class Geometries
     *
     * @param geometries list of Geometrical shapes
     */
    public Geometries(Intersectable... geometries) {
        intersectablesList = new LinkedList<>();
        Collections.addAll(intersectablesList, geometries);

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
    public List<Point> findIntersections(Ray ray) {
        List<Point> result = null;
        for (var item : intersectablesList) {
            List<Point> itemList = item.findIntersections(ray);
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

