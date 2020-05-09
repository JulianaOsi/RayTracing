package material;

import common.HitRecord;
import common.Ray;
import common.Vector3;

public class Diffuse extends Material {
    public Diffuse(Vector3 albedo) {
        this.albedo = albedo;
    }

    @Override
    public boolean scatter(Ray rayIn, HitRecord record, Vector3 attenuation, Ray scattered) {
        Vector3 scatterDirection = record.getNormal().plus(Vector3.randomInUnitSphere());
        scattered.set(new Ray(record.getPoint(), scatterDirection));
        attenuation.set(albedo);
        return true;
    }
}
