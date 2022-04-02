package scene;

import geometries.*;
import lighting.*;
import primitives.*;

import java.util.LinkedList;

/**
 * Class scene for creation of a scene with geometrical objects,
 * colors and light
 */
public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;

    /**
     * Constructor for class scene.
     * Background color, ambient light, and geometries will be set to default values.
     *
     * @param n the name of the scene
     */
    public Scene(String n) {
        name = n;
        background = Color.BLACK;
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    //*********Setters**********//
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries.add(geometries);
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
}
