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

    /**
     * P(t) = origin + t * direction
     *
     * @param t - параметр, перемещающий точку вдоль луча
     * @return точка на луче
     */
    public Vector3 getRayPoint(double t) {
        return origin.plus(direction.multiply(t));
    }

    public Vector3 getColor(SpheresList spheres) {
        HitRecord record = new HitRecord();
        if (spheres.isHitBy(this, 0, Double.POSITIVE_INFINITY, record)) {
            return record.getNormal().plus(new Vector3(1, 1, 1)).multiply(0.5);
        }

        Vector3 unitDirection = direction.unitVector();
        double t = 0.5 * (unitDirection.getY() + 1);
        return new Vector3(1,1,1).multiply(1-t).plus(new Vector3(0.5,0.7,1).multiply(t));

    }

//    private double hitSphere(Vector3 center, double radius) {
//        Vector3 oc = origin.minus(center);
//        double a = direction.dot(direction);
//        double half_b = oc.dot(direction);
//        double c = oc.dot(oc) - radius * radius;
//        double discriminant = half_b * half_b - a * c;
//        if (discriminant < 0) {
//            return -1;
//        } else {
//            return (-half_b - Math.sqrt(discriminant)) / a;
//        }
//    }

    public Vector3 getOrigin() {
        return origin;
    }

    public Vector3 getDirection() {
        return direction;
    }
}
