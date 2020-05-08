public class Vector3 {
    private double x;
    private double y;
    private double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 plus(Vector3 vector3) {
        return new Vector3(x + vector3.x, y + vector3.y, z + vector3.z);
    }

    public Vector3 minus(Vector3 vector3) {
        return new Vector3(x - vector3.x, y - vector3.y, z - vector3.z);
    }

    public Vector3 revert() {
        return new Vector3(-x, -y, -z);
    }

    public Vector3 multiply(Vector3 vector3) {
        return new Vector3(x * vector3.x, y * vector3.y, z * vector3.z);
    }

    public Vector3 multiply(double k) {
        return new Vector3(x * k, y * k, z * k);
    }

    public Vector3 divide(double k) {
        return new Vector3(x / k, y / k, z / k);
    }

    /**
     * Скалярное произведение.
     *
     * @param vector3 - вектор
     * @return скалярное произведение
     */
    public double dot(Vector3 vector3) {
        return x * vector3.x + y * vector3.y + z * vector3.z;
    }


    /**
     * Векторное произведение.
     *
     * @param vector3 - вектор
     * @return векторное произведение
     */
    public Vector3 cross(Vector3 vector3) {
        return new Vector3(
                y * vector3.z - z * vector3.y,
                z * vector3.x - x * vector3.z,
                x * vector3.y - y * vector3.x
        );
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vector3 unitVector() {
        return this.divide(this.length());
    }

    public static void print(Vector3 vector3) {
        System.out.println(vector3.x + " " + vector3.y + " " + vector3.z);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
