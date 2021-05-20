package Core;

public class Camera {
    private Point point = new Point();
    private int height_angle;
    private int width_angle;

    public Camera() {

    }

    public Camera(Point point, int w_angle, int h_angle) {
        this.point = point;
        this.width_angle = w_angle;
        this.height_angle = h_angle;
    }

    public int getWidth_angle() {
        return width_angle;
    }

    public void setWidth_angle(int width_angle) {
        this.width_angle = width_angle;
    }

    public int getHeight_angle() {
        return height_angle;
    }

    public void setHeight_angle(int height_angle) {
        this.height_angle = height_angle;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
