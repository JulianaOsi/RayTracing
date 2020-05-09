package shape;

import common.HitRecord;
import common.Ray;
import common.Vector3;
import material.Material;

public class Sphere {
    private Vector3 center;
    private double radius;
    private Material material;

    public Sphere(Vector3 center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public boolean isHitBy(Ray ray, double t_min, double t_max, HitRecord record) {
        Vector3 rayOrigin = ray.getOrigin();
        Vector3 rayDirection = ray.getDirection();

        Vector3 oc = rayOrigin.minus(center);
        double a = rayDirection.dot(rayDirection);
        double half_b = oc.dot(rayDirection);
        double c = oc.dot(oc) - radius * radius;
        double discriminant = half_b * half_b - a * c;

        if (discriminant > 0) {
            double root = Math.sqrt(discriminant);
            double temp = (-half_b - root) / a;

            if (temp < t_max && temp > t_min) {
                Vector3 point = ray.getRayPoint(temp);
                Vector3 outwardNormal = point.minus(center).divide(radius);
                record.set(point, temp, material);
                record.setFaceNormal(ray, outwardNormal);
                return true;
            }

            temp = (-half_b + root) / a;
            if (temp < t_max && temp > t_min) {
                Vector3 point = ray.getRayPoint(temp);
                Vector3 outwardNormal = point.minus(center).divide(radius);
                record.set(point, temp, material);
                record.setFaceNormal(ray, outwardNormal);
                return true;
            }
        }
        return false;
    }

}
