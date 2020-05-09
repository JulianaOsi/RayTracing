package material;

import common.HitRecord;
import common.Ray;
import common.Vector3;

public class Metal extends Material {
    public Metal(Vector3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Vector3 attenuation, Ray scattered) {
        Vector3 reflected = reflect(rayIn.getDirection().unitVector(), record.getNormal());
        //порождение отражающегося луча
        Ray ray = new Ray(record.getPoint(), reflected);
        scattered.set(ray);
        attenuation.set(albedo);
        //угол < 90
        return scattered.getDirection().dot(record.getNormal()) > 0;
    }

    private Vector3 reflect(Vector3 v, Vector3 n) {
        return v.minus(n.multiply(2 * v.dot(n)));
    }
}
