import common.PPM;
import common.SpheresList;
import common.Vector3;
import material.Diffuse;
import material.Metal;
import shape.Sphere;

public class Main {
    public static void main(String[] args) {
        double aspectRatio = (double) 16 / 9;

        SpheresList spheres = new SpheresList();

        Vector3 silver = new Vector3(0.8, 0.8, 0.8);
        Vector3 pink = new Vector3(0.7, 0.3, 0.3);
        Vector3 blue = new Vector3(0.1, 0.5, 0.3);

        Diffuse pinkDiffuse = new Diffuse(pink);
        Metal silverMetal = new Metal(silver);
        Metal blueMetal = new Metal(blue);

        spheres.add(new Sphere(new common.Vector3(0, 0, -1), 0.5, silverMetal));
        spheres.add(new Sphere(new common.Vector3(-0.8, -0.3, -0.7), 0.2, blueMetal));
        spheres.add(new Sphere(new common.Vector3(0, -100.5, -1), 100, pinkDiffuse));
        spheres.add(new Sphere(new common.Vector3(1, 0, -1), 0.5, pinkDiffuse));

        PPM ppm = new PPM();
        ppm.createImage(384, (int) (384 / aspectRatio), spheres);
        ppm.writeImage("pic.ppm");
    }
}
