package common;

import javafx.concurrent.Task;
import javafx.util.Pair;
import me.tongfei.progressbar.ProgressBar;
import me.tongfei.progressbar.ProgressBarStyle;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class PPM {
    private final int MAX_DEPTH = 50;
    private int samplesPerPixel = 100;
    final private int width;
    final private int height;
    private ProgressBar pb;

    public PPM(int width, int height) {
        this.width = width;
        this.height = height;
        pb = new ProgressBar("Render", width * height, ProgressBarStyle.ASCII);
    }

    public StringBuilder createImage(SpheresList spheres, Camera camera) throws InterruptedException, ExecutionException {
        StringBuilder image = new StringBuilder(String.format("P3\n%d %d\n255\n", width, height));
        ExecutorService exec = Executors.newFixedThreadPool(16);

        List<Future<StringBuilder>> futures = new ArrayList<>();

        for (int j = height - 1; j >= 0; --j) {
            Partitioner partitioner = new Partitioner(width, 16); // разделитель строки пикселей
            int finalJ = j;
            List<Callable<StringBuilder>> renderRowCall = new ArrayList<>(); // вызовы рендера каждой части строки пикселей
            for (int k = 0; k < 16; k++) {
                Pair<Integer, Integer> pair = partitioner.next();
                renderRowCall.add(() -> renderRow(pair.getKey(), pair.getValue(), camera, spheres, finalJ));
            }
            // параллельный рендеринг строки пикселей
            futures.addAll(exec.invokeAll(renderRowCall));
        }
        exec.shutdown();
        for (Future<StringBuilder> future : futures) {
            image.append(future.get());
        }

        pb.close();
        return image;
    }

    private StringBuilder renderRow(int start, int end, Camera camera, SpheresList spheres, int j) {
        StringBuilder image = new StringBuilder();
        for (int i = start; i < end; ++i) {
            pb.step();
            Vector3 pixelColor = new Vector3(0, 0, 0);
            for (int s = 0; s < samplesPerPixel; ++s) {
                double u = (i + new Random().nextDouble()) / (width - 1);
                double v = (j + new Random().nextDouble()) / (height - 1);
                Ray ray = camera.getRay(u, v);
                pixelColor = pixelColor.plus(ray.getColor(spheres, MAX_DEPTH));
            }
            writePixelColor(pixelColor, image);
        }
        return image;
    }

    private double clamp(double x, double min, double max) {
        if (x < min) return min;
        if (x > max) return max;
        return x;
    }

    public void writeImage(String filename, StringBuilder image) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(String.valueOf(image));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writePixelColor(Vector3 color, StringBuilder image) {
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
