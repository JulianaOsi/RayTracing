import common.Camera;
import common.PPM;
import common.SpheresList;
import common.Vector3;
import material.Diffuse;
import material.Metal;
import shape.Sphere;

import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //149945286500 без многопоточности
        //55643382900 с многопоточностью
        long start = System.nanoTime();

        double aspectRatio = (double) 16 / 9;
        PPM ppm = new PPM(1024, (int) (1024 / aspectRatio));
        Vector3 lookFrom = new Vector3(13, 2, 3);
        Vector3 lookAt = new Vector3(0,0,0);
        Vector3 vup = new Vector3(0,1,0);

        Camera camera = new Camera(lookFrom, lookAt, vup, 20, aspectRatio);
        SpheresList spheres = randomScene();


        StringBuilder image = ppm.createImage(spheres, camera);
        ppm.writeImage("pic.ppm", image);

        System.out.println(System.nanoTime()-start);
    }

    private static SpheresList randomScene() {
        SpheresList spheres = new SpheresList();

        Vector3 gray = new Vector3(0.5, 0.5, 0.5);
        spheres.add(new Sphere(
                new Vector3(0, -1000, 0),
                1000,
                new Diffuse(gray)
        ));
        for (int a = -8; a < 8; a++) {
            for (int b = -4; b < 4; b++) {
                Random random = new Random();
                double chooseMaterial = random.nextDouble();
                Vector3 center = new Vector3(a + 0.9 * random.nextDouble(), 0.2, b + 0.9 * random.nextDouble());
                if (center.minus(new Vector3(4, 0.2, 0)).length() > 0.9) {
                    if (chooseMaterial < 0.7) { //diffuse
                        Vector3 albedo = Vector3.random(0, 1).multiply(Vector3.random(0, 1));
                        spheres.add(new Sphere(
                                center,
                                0.2,
                                new Diffuse(albedo)
                        ));
                    } else { //metal
                        Vector3 albedo = Vector3.random(0.5, 1);
                        double fuzz = Vector3.randomDouble(0, 0.5);
                        spheres.add(new Sphere(
                                center,
                                0.2,
                                new Metal(albedo, fuzz)
                        ));
                    }
                }
            }
        }

        spheres.add(new Sphere(
                new Vector3(0, 1, 0),
                1,
                new Diffuse(new Vector3(0.4, 0.2, 0.1))
        ));

        spheres.add(new Sphere(
                new Vector3(4, 1, 0),
                1,
                new Metal(new Vector3(0.7, 0.6, 0.5), 0)
        ));

        return spheres;
    }
}
