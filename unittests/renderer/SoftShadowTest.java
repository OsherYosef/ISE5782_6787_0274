package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;
import lighting.*;

import static java.awt.Color.*;

/**
 * Test class for Soft Shadows
 *
 * @auther Dov and Osher
 */
public class SoftShadowTest {

    /**
     * Test for initialising the points in PointLight
     */
    @Test
    void testInitialisedPoint() {
        //The test is to see if the array is actually constructed
        PointLight pointLight = new PointLight(Color.BLACK, new Point(0, 0, 0)).setSize(1);
        pointLight.initializePoints(new Point(1, 0, 1));
    }

    /**
     * Test for initialising the points in SpotLight
     */
    @Test
    void testInitialisedSpot() {
        //The test is to see if the array is actually constructed
        SpotLight spotLight = new SpotLight(Color.BLACK, new Point(0, 0, 0), new Vector(0, 0, 1)).setSize(3);
    }

    /**
     * Producing a picture with 10 shapes and 3 light sources
     */
    @Test
    void MiniProject_1() {
        Scene scene = new Scene("TestScene");
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene));

        Intersectable sphere = new Sphere(new Point(0, 0, -200), 60d) //
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);
        Geometry triangle = new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4))
                .setEmission(new Color(BLUE)).setMaterial(trMaterial);


        scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
        scene.lights.add(
                new SpotLight(new Color(400, 240, 0), new Point(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setSize(5));
        scene.lights.add(
                new PointLight(new Color(GREEN), new Point(-70, -125, 200)).setKl(1E-5).setKq(1.5E-7).setSize(5));  //, new Vector(1, 1, -3)) //

        camera.setImageWriter(new ImageWriter("softShadowTest", 500, 500)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    void test() {
        Scene scene = new Scene("TestScene");
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene));
        Material roomMaterial = new Material().setKs(0.3).setKd(0.5);
        Material bodyMaterial = new Material().setKs(0.8).setKd(0.5);
        scene.geometries.add(
                //Room
                //Right wall
                new Polygon(new Point(100, 100, 0), new Point(100, -80, 0), new Point(40, -80, -250), new Point(40, 100, -250))
                        .setEmission(new Color(200, 200, 100)).setMaterial(roomMaterial),
                //Left wall
                new Polygon(new Point(-100, 100, 0), new Point(-100, -80, 0), new Point(40, -80, -250), new Point(40, 100, -250))
                        .setEmission(new Color(252, 247, 135)).setMaterial(roomMaterial),
                //Ceiling
                new Triangle(new Point(100, 100, 0), new Point(-100, 100, 0), new Point(40, 99, -250))
                        .setEmission(new Color(240, 240, 130).scale(0.8)).setMaterial(roomMaterial),
                //Floor
                new Triangle(new Point(40, -80, -250), new Point(185, -100, 0), new Point(-265, -100, 0))
                        .setEmission(new Color(lightGray)).setMaterial(new Material().setShininess(5).setKs(0.2)),
                //Lamp
                new Sphere(new Point(40, 100, -100), 15).setMaterial(new Material().setKs(0.3).setKd(0.5).setShininess(20).setKt(0.9))
                        .setEmission(new Color(GRAY))
        );
        Point sptPoint = new Point(-100, -40, 0);
        scene.lights.add(new SpotLight(new Color(YELLOW), sptPoint, new Point(-10, -45, -50).subtract(sptPoint)).setKl(1000).setKq(1.5E-6).setSize(0));
        scene.lights.add(new PointLight(new Color(240, 140, 0), new Point(40, 85, -100)).setKl(1E-4).setKq(1.5E-7).setSize(0));
        camera.setImageWriter(new ImageWriter("softShadowTest2", 500, 500)) //
                .renderImage() //
                .writeToImage();
    }
}
