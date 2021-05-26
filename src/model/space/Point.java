package model.space;

public class Point {
    private double x,y,z;
    
    // true is in obj, false is not in obj
    private boolean in_obj = true;
    
    // true is in visible area of camera
    private boolean in_camera = true;
    
    public Point(){};
    public Point(double x){
        this.x = x * 100;
    }
    public Point(double x, double y){
        this.x = x * 100;
        this.y = y * 100;
    }
    public Point(double x, double y, double z){
        this.x = x * 100;
        this.y = y * 100;
        this.z = z * 100;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
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
	public String printInfo()
    {
    	String str = "";
        str += "(" + this.getX()/100 + " , "
                        + this.getY()/100 + ", "
                        + this.getZ()/100 + ") \n";

    	return str;
    }

}
