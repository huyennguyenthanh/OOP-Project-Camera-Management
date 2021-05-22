package camera;

import point.Point;

public class Camera {
    private Point point = new Point();
    private float height_angle;
    private float width_angle;

    public Camera() {

    }

    public Camera(Point point, float w_angle, float h_angle) {
        this.point = point;
        this.width_angle = w_angle;
        this.height_angle = h_angle;
    }

    public float getWidth_angle() {
        return width_angle;
    }

    public void setWidth_angle(float width_angle) {
        this.width_angle = width_angle;
    }

    public float getHeight_angle() {
        return height_angle;
    }

    public void setHeight_angle(float height_angle) {
        this.height_angle = height_angle;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
