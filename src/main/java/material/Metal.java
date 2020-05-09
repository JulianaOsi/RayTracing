package material;

import common.HitRecord;
import common.Ray;
import common.Vector3;

public class Metal extends Material {
    private double fuzz;

    public Metal(Vector3 albedo, double fuzz) {
        this.albedo = albedo;
        this.fuzz = fuzz < 1 ? fuzz : 1;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Vector3 attenuation, Ray scattered) {
        Vector3 reflected = reflect(rayIn.getDirection().unitVector(), record.getNormal());
        //порождение отражающегося луча
        Ray ray = new Ray(
                record.getPoint(),
                reflected.plus(Vector3.randomInUnitSphere().multiply(fuzz)));
        scattered.set(ray);
        attenuation.set(albedo);
        //угол < 90
        return scattered.getDirection().dot(record.getNormal()) > 0;
    }

    private Vector3 reflect(Vector3 v, Vector3 n) {
        return v.minus(n.multiply(2 * v.dot(n)));
    }


}
