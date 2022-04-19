package scene;

import geometries.*;
import lighting.*;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Class scene for creation of a scene with geometrical objects,
 * colors and light
 *
 * @auther Osher and Dov
 */
public class Scene {
    /**
     * The name of the scene
     */
    public String name;
    /**
     * Represents the color of the scene's background
     */
    public Color background = Color.BLACK;
    /**
     * The ambient light- same light for every point in the scene
     */
    public AmbientLight ambientLight = new AmbientLight();
    /**
     * Group of intersect-able geometry shapes
     */
    public Geometries geometries = new Geometries();
    /**
     * Group of lights that effect the scene
     */
    public List<LightSource> lights = new LinkedList<>();

    /**
     * Constructor for class scene.
     * Background color, ambient light, and geometries will be set to default values.
     *
     * @param n The name of the scene
     */
    public Scene(String n) {
        name = n;
    }
    //*********Setters**********//

    /**
     * Set a new Background Color for the scene
     *
     * @param background The new Background color
     * @return The scene with updated Background Color(Build Pattern)
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * Add a group of geometries to the scene
     *
     * @param geometries given group of Geometry shapes
     * @return The scene with updated Geometries group(Build Pattern)
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * Set the ambientLight of the scene
     *
     * @param ambientLight given ambient Light
     * @return The scene with updated Ambient Light(Build Pattern)
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * Set the Lights of the scene
     *
     * @param lights given list of lights
     * @return The scene with updated lights list
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
