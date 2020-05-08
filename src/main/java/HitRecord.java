public class HitRecord {
    private Vector3 point;
    private Vector3 normal;
    private double t;
    boolean isFrontFace;

    public void set(Vector3 point, double t) {
        this.point = point;
        this.t = t;
    }

    public void set(HitRecord record) {
        this.point = record.point;
        this.normal = record.normal;
        this.t = record.t;
    }

    public void setFaceNormal(Ray ray, Vector3 outwardNormal) {
        isFrontFace = ray.getDirection().dot(outwardNormal) < 0;
        normal = isFrontFace ? outwardNormal : outwardNormal.revert();
    }

    public Vector3 getPoint() {
        return point;
    }

    public void setPoint(Vector3 point) {
        this.point = point;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public void setNormal(Vector3 normal) {
        this.normal = normal;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

}
