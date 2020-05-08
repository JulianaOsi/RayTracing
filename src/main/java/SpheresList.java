import java.util.ArrayList;

public class SpheresList extends ArrayList<Sphere> {
    public boolean isHitBy(Ray ray, double t_min, double t_max, HitRecord record) {
        HitRecord tempRecord = new HitRecord();
        boolean isHitAnything = false;
        double nearest = t_max;

        for (Sphere sphere : this) {
            if (sphere.isHitBy(ray, t_min, nearest, tempRecord)) {
                isHitAnything = true;
                nearest = tempRecord.getT();
                record.set(tempRecord);
            }
        }
        return isHitAnything;

    }
}
