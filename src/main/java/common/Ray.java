package common;

public class Ray {
    private Vector3 origin;
    private Vector3 direction;

    /**
     * @param origin    - начало луча
     * @param direction - направление луча
     */
    public Ray(Vector3 origin, Vector3 direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray() {
    }

    /**
     * P(t) = origin + t * direction
     *
     * @param t - параметр, перемещающий точку вдоль луча
     * @return точка на луче
     */
    public Vector3 getRayPoint(double t) {
        return origin.plus(direction.multiply(t));
    }

    public Vector3 getColor(SpheresList spheres, int depth) {
        HitRecord record = new HitRecord();
        // предел количества отражений
        if (depth <= 0) {
            return new Vector3(0, 0, 0);
        }

        if (spheres.isHitBy(this, 0.001, Double.POSITIVE_INFINITY, record)) {
            Ray scattered = new Ray();
            Vector3 attenuation = new Vector3();
            if (record.material.scatter(this, record, attenuation, scattered)) {
                return scattered.getColor(spheres, depth - 1).multiply(attenuation);
            }
            return new Vector3(0, 0, 0);
        }

        Vector3 unitDirection = direction.unitVector();
        double t = 0.5 * (unitDirection.getY() + 1);
        return new Vector3(1, 1, 1).multiply(1 - t).plus(new Vector3(0.5, 0.7, 1).multiply(t));

    }

    public void set(Ray ray) {
        this.origin = ray.origin;
        this.direction = ray.direction;
    }

    public Vector3 getOrigin() {
        return origin;
    }

    public Vector3 getDirection() {
        return direction;
    }
}
