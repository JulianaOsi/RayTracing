package common;

public class Camera {
    Vector3 origin;
    Vector3 horizontal;
    Vector3 vertical;
    Vector3 lowerLeftCorner;

    /**
     * @param lookFrom            - позиция камеры
     * @param lookAt              - точка, на которую смотрим
     * @param vup                 - view up
     * @param verticalFieldOfView - угол поля зрения в градусах
     * @param aspectRatio         - соотношение сторон
     */
    public Camera(
            Vector3 lookFrom,
            Vector3 lookAt,
            Vector3 vup,
            double verticalFieldOfView,
            double aspectRatio) {
        origin = lookFrom;
        Vector3 u, v, w; //базис

        double theta = Math.toRadians(verticalFieldOfView);
        double halfHeight = Math.tan(theta / 2);
        double halfWidth = aspectRatio * halfHeight;

        w = lookFrom.minus(lookAt).unitVector(); //z
        u = vup.cross(w).unitVector(); //x
        v = w.cross(u); ///y

        lowerLeftCorner = origin
                .minus(u.multiply(halfWidth))
                .minus(v.multiply(halfHeight))
                .minus(w);
        horizontal = u.multiply(2 * halfWidth);
        vertical = v.multiply(2 * halfHeight);
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
