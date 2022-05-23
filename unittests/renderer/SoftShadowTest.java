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

    @Test
    void testInitialisedPoint() {
        PointLight pointLight = new PointLight(Color.BLACK, new Point(0, 0, 0)).setSize(1);
        pointLight.initializePoints(new Point(1,0,1));
    }

    @Test
    void testInitialisedSpot() {
        SpotLight spotLight = new SpotLight(Color.BLACK, new Point(0, 0, 0), new Vector(0, 0, 1)).setSize(3);
    }

    /**
     * Producing a picture with 10 shapes and 3 light sources
     */
    @Test
    void MiniProject_1() {
        Intersectable sphere = new Sphere(new Point(0, 0, -200), 60d) //
                .setEmission(new Color(BLUE)) //
                .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Material trMaterial = new Material().setKd(0.5).setKs(0.5).setShininess(30);
        Geometry triangle = new Triangle(new Point(-70, -40, 0), new Point(-40, -70, 0), new Point(-68, -68, -4))
                .setEmission(new Color(BLUE)).setMaterial(trMaterial);
        Scene scene = new Scene("TestScene");
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000) //
                .setRayTracer(new RayTracerBasic(scene));

        scene.geometries.add(sphere, triangle.setEmission(new Color(BLUE)).setMaterial(trMaterial));
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7).setSize(5));
        camera.setImageWriter(new ImageWriter("softShadowTest", 500, 500)) //
                .renderImage() //
                .writeToImage();

    }
}
