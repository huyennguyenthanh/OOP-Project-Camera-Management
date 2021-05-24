package model.space;

public class Point {
    private float x,y,z;
    
    // true is in obj, false is not in obj
    private boolean in_obj = true;
    
    // true is in visible area of camera
    private boolean in_camera = true;
    
    public Point(){};
    public Point(float x){
        this.x = x;
    }
    public Point(float x, float y){
        this.x = x;
        this.y = y;
    }
    public Point(float x, float y, float z){
        this.x = x * 100;
        this.y = y * 100;
        this.z = z * 100;
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
    
    
	public boolean isIn_obj() {
		return in_obj;
	}
	public void setIn_obj(boolean in_obj) {
		this.in_obj = in_obj;
	}
	public boolean isIn_camera() {
		return in_camera;
	}
	public void setIn_camera(boolean in_camera) {
		this.in_camera = in_camera;
	}
	

}
