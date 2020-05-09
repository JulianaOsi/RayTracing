package material;

import common.HitRecord;
import common.Ray;
import common.Vector3;

public abstract class Material {
    protected Vector3 albedo;
    public abstract boolean scatter(Ray rayIn, HitRecord record, Vector3 attenuation, Ray scattered);
}
