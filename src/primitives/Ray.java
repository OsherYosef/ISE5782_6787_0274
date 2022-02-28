package src.primitives;


/**
 * @author Osher and Dov
 * **/
public class Ray {
    final Point p0;
    final Vector dir;
    public Ray(Vector vector){
        dir= vector.normalize();
    }


    @Override
    public String toString() {
        return "Ray{" +
                "P0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

}
