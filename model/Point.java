package model;

public class Point {
    private float x,y,z;
    public Point(){};
    public Point(float x){
        this.x = x;
    }
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Point(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }
    public boolean equals(Point point){
        if(point.getX() == x && point.getY() == y && point.getZ() == z)
            return true;
        else
            return false;
    }

}
