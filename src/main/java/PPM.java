import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class PPM {

    private StringBuilder image;
    private static Logger log = Logger.getLogger(PPM.class.getName());
    Vector3 origin = new Vector3(0, 0, 0);
    Vector3 horizontal = new Vector3(4, 0, 0);
    Vector3 vertical = new Vector3(0, 2.25, 0);
    Vector3 lowerLeftCorner = getLowerLeftCorner();

    public void createImage(int width, int height, SpheresList spheres) {
        image = new StringBuilder(String.format("P3\n%d %d\n255\n", width, height));

        for (int j = height - 1; j >= 0; --j) {
            for (int i = 0; i < width; ++i) {
                double u = (double) i / (width - 1);
                double v = (double) j / (height - 1);
                Ray ray = new Ray(origin, getDirection(u, v));
                Vector3 pixelColor = ray.getColor(spheres);
                writePixelColor(pixelColor);
            }
        }
        log.info("image successfully created");
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
        int ir = (int) (255.999 * color.getX());
        int ig = (int) (255.999 * color.getY());
        int ib = (int) (255.999 * color.getZ());
        image.append(String.format("%d %d %d\n", ir, ig, ib));
    }

    //lowerLeftCorner = origin - horizontal/2 - vertical/2 - {0, 0, 1}
    private Vector3 getLowerLeftCorner() {
        return origin
                .minus(horizontal.divide(2))
                .minus(vertical.divide(2))
                .minus(new Vector3(0, 0, 1));
    }

    //lowerLeftCorner + horizontal/u + vertical/v
    private Vector3 getDirection(double u, double v) {
        return lowerLeftCorner
                .plus(horizontal.multiply(u))
                .plus(vertical.multiply(v));
    }
}
