package src.geometries;

import src.primitives.*;

import java.util.List;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @auther Osher and Dov
 */

public class Geometries implements Intersectable {
    protected List<Intersectable>intersectablesList;
    public Geometries() {intersectablesList = new LinkedList<>();}
    public Geometries(Intersectable... geometries) {
        intersectablesList = new LinkedList<>();
        Collections.addAll(intersectablesList, geometries);

    }
    public void add(Intersectable... intersectables){Collections.addAll(intersectablesList,intersectables);}


    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result=null;
        for (var item: intersectablesList) {
            List<Point> itemList=item.findIntersections(ray);
            if(itemList!=null)
            {
                if(result==null)
                {
                    result=new LinkedList<>();
                }
                result.addAll(itemList);
            }

        }
        return result;
    }
}

