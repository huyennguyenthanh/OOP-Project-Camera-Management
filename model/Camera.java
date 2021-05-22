package model;

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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public float getHeight_angle() {
        return height_angle;
    }

    public void setHeight_angle(float height_angle) {
        this.height_angle = height_angle;
    }

    public float getWidth_angle() {
        return width_angle;
    }

    public void setWidth_angle(float width_angle) {
        this.width_angle = width_angle;
    }
    public boolean checkOnWall(Room room){
        if(point.getZ() == 0 )
            return false;
        // check thuoc mat phang A1B1BA
        if(point.getY() == 0 && point.getX() <= room.getWidth() && point.getZ() <= room.getHeight())
            return true;
        // check thuộc mặt phẳng B1C1CB
        if(point.getX() == room.getWidth() && point.getY() <= room.getLength() && point.getZ() <= room.getHeight())
            return true;
        // check thuộc mặt phẳng C1D1DC
        if(point.getY() == room.getLength() && point.getX() <= room.getWidth() && point.getZ() <= room.getHeight())
            return true;
        return false;
    }
    public boolean checkAngle(){
        if(width_angle <= 90 && height_angle <= 90)
            return true;
        else
            return false;
    }

}
