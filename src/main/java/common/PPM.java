package common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Logger;

public class PPM {

    private final int MAX_DEPTH = 50;
    private StringBuilder image;
    private static Logger log = Logger.getLogger(PPM.class.getName());
    private int samplesPerPixel = 100;

    public void createImage(int width, int height, SpheresList spheres) {
        image = new StringBuilder(String.format("P3\n%d %d\n255\n", width, height));
        Camera camera = new Camera();
        for (int j = height - 1; j >= 0; --j) {
            for (int i = 0; i < width; ++i) {
                Vector3 pixelColor = new Vector3(0, 0, 0);
                for (int s = 0; s < samplesPerPixel; ++s) {
                    double u = (i + new Random().nextDouble()) / (width - 1);
                    double v = (j + new Random().nextDouble()) / (height - 1);
                    Ray ray = camera.getRay(u, v);
                    pixelColor = pixelColor.plus(ray.getColor(spheres, MAX_DEPTH));
                }
                writePixelColor(pixelColor);
            }
        }
        log.info("image successfully created");
    }

    private double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }

    public void writeImage(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(String.valueOf(image));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        log.info("image successfully write");
    }

    private void writePixelColor(Vector3 color) {
        double r = color.getX();
        double g = color.getY();
        double b = color.getZ();

        double scale = (double) 1 / samplesPerPixel;
        r = Math.sqrt(scale * r);
        g = Math.sqrt(scale * g);
        b = Math.sqrt(scale * b);

        int resR = (int) (256 * clamp(r, 0, 0.999));
        int resG = (int) (256 * clamp(g, 0, 0.999));
        int resB = (int) (256 * clamp(b, 0, 0.999));

        image.append(String.format("%d %d %d\n", resR, resG, resB));
    }
}
