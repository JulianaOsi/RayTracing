public class Camera {
    Vector3 origin;
    Vector3 horizontal;
    Vector3 vertical;
    Vector3 lowerLeftCorner;

    public Camera() {
        origin = new Vector3(0, 0, 0);
        horizontal = new Vector3(4, 0, 0);
        vertical = new Vector3(0, 2, 0);
        lowerLeftCorner = new Vector3(-2, -1, -1);
    }

    public Ray getRay(double u, double v) {
        return new Ray(
                origin,
                lowerLeftCorner
                        .plus(horizontal.multiply(u))
                        .plus(vertical.multiply(v))
                        .minus(origin));
    }
}
