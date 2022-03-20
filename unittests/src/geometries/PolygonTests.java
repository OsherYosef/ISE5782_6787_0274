/**
 * 
 */
package src.geometries;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import src.geometries.*;
import src.primitives.*;

/**
 * Testing Polygons
 * 
 * @author Dan
 *
 */
public class PolygonTests {

	/**
	 * Test method for @link src.geometries.Polygon#Polygon(primitives.Point...).
	 */
	@Test
	public void testConstructor() {
		// ============ Equivalence Partitions Tests ==============

		// TC01: Correct concave quadrangular with vertices in correct order
		try {
			new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d), new Point(-1d, 1d, 1d));
		} catch (IllegalArgumentException e) {
			fail("Failed constructing a correct polygon");
		}

		// TC02: Wrong vertices order
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0d, 0d, 1d), new Point(0d, 1d, 0d), new Point(1d, 0d, 0d), new Point(-1d, 1d, 1d)), //
				"Constructed a polygon with wrong order of vertices");

		// TC03: Not in the same plane
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d), new Point(0d, 2d, 2d)), //
				"Constructed a polygon with vertices that are not in the same plane");

		// TC04: Concave quadrangular
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d),
						new Point(0.5, 0.25, 0.5)), //
				"Constructed a concave polygon");

		// =============== Boundary Values Tests ==================

		// TC10: Vertex on a side of a quadrangular
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d), new Point(0d, 0.5, 0.5)),
				"Constructed a polygon with vertix on a side");

		// TC11: Last point = first point
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d), new Point(0d, 0d, 1d)),
				"Constructed a polygon with vertice on a side");

		// TC12: Co-located points
		assertThrows(IllegalArgumentException.class, //
				() -> new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d), new Point(0d, 1d, 0d)),
				"Constructed a polygon with vertice on a side");

	}

	/**
	 * Test method for @link geometries.Polygon#getNormal(primitives.Point)
	 */
	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		// TC01: There is a simple single test here
		Polygon pl = new Polygon(new Point(0d, 0d, 1d), new Point(1d, 0d, 0d), new Point(0d, 1d, 0d), new Point(-1d, 1d, 1d));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals(new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point(0d, 0d, 1d)), "Bad normal to trinagle");
	}
	/**
	 * Test method for {@link src.geometries.Polygon#findIntersections(Ray)}
	 */
	@Test
	void testFindIntersections(){

	}
}
