import java.util.Collections;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        double aspectRatio = (double) 16/9;

        SpheresList spheres = new SpheresList();
        spheres.add(new Sphere(new Vector3(0,0,-1), 0.5));
        spheres.add(new Sphere(new Vector3(0,-100.5,-1), 100));

        PPM ppm = new PPM();
        ppm.createImage(384, (int) (384/aspectRatio), spheres);
        ppm.writeImage("pic.ppm");
    }
}
