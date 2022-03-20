package src.geometries;

import org.junit.jupiter.api.Test;
import src.primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void findIntersections() {
        Geometries gTest=new Geometries();

        //checks for an empty geometry list.
        assertNull(new Vector(1.0,0.0,0.0),gTest.findIntersections(new Ray(new Point(1.0,1.0,1.0)),"empty collection"));



    }
}