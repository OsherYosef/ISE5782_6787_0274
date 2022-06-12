package geometries;

/*
*
* Interface that creates Bounding Boxes
*
 */

public interface Bounds {
    /**
     * creates the bounding box around a object and adds the object to a list
     * @return bounding box
     */

    AxisBoundingBox getBoundingBox();
}
