package model;

import model.space.Point;
import model.space.Plane;
import model.space.Line;

import java.util.ArrayList;

import model.space.*;

public class Camera {
    private Point point = new Point();
    private float height_angle;
    private float width_angle;
    
    private boolean is_on_wall = true;
    private boolean is_on_ceil = true;
    

    private ArrayList<Point> projection = new ArrayList<>();
    private float V;
    public Camera() {

    }

    public Camera(Point point, float w_angle, float h_angle) {
        this.point = point;
        this.width_angle = (float) Math.toRadians(h_angle/2);
        this.height_angle = (float) Math.toRadians(h_angle/2);
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
   
	public ArrayList<Point> getProjection() {
		return projection;
	}

	public void setProjection(ArrayList<Point> projection) {
		this.projection = projection;
	}

	public float getV() {
		return V;
	}

	public void setV(float v) {
		V = v;
	}
	public boolean isIs_on_wall() {
		return is_on_wall;
	}

	public void setIs_on_wall(boolean is_on_wall) {
		this.is_on_wall = is_on_wall;
	}

	public boolean isIs_on_ceil() {
		return is_on_ceil;
	}

	public void setIs_on_ceil(boolean is_on_ceil) {
		this.is_on_ceil = is_on_ceil;
	}
    
    

	
	public boolean checkAngle(){
	        if(width_angle <= 90 && height_angle <= 90)
	            return true;
	        else
	            return false;
	    }
    
    private void cal_projection() {
    	

    	float length = (float) (this.point.getZ() * Math.tan(this.height_angle));
    	float width = (float) (this.point.getZ() * Math.tan(this.width_angle));
    	
    	if (this.is_on_ceil) {
    	// tìm hình chiếu khi camera từ trên trần chiếu xuống
    	// center of projection
    	Point center_point = new Point(this.point.getX(), this.point.getY(), 0);
    	
    	// 4 đỉnh của hình chiếu camera trên mặt đất
    	this.projection.add(new Point(center_point.getX() - length, center_point.getY() - width, 0)); //A
    	this.projection.add(new Point(center_point.getX() + length, center_point.getY() - width, 0)); // B
    	this.projection.add(new Point(center_point.getX() + length, center_point.getY() + width, 0)); // C
    	this.projection.add(new Point(center_point.getX() - length, center_point.getY() + width, 0)); // D
    	
    	return;
    	}
    	
    	if (this.is_on_wall) {
    		
    	// 
    	// tìm hình chiếu khi camera từ tường chiếu ngang
    	Point center_point = new Point(this.point.getX(), this.point.getY(), 0);
    	this.projection.add(new Point(center_point.getX() - length, center_point.getY() - width, 0)); //A
    	this.projection.add(new Point(center_point.getX() + length, center_point.getY() - width, 0)); // B
    	this.projection.add(new Point(center_point.getX() + length, center_point.getY() + width, 0)); // C
    	this.projection.add(new Point(center_point.getX() - length, center_point.getY() + width, 0)); // D
    	
    	return;
    	}

    	

    }
    
    // volumne of visible area of camera
    public float volume_visible_area()
    {
    	Calculation c = new Calculation();
    	Plane projection_plane = new Plane(projection.get(0), projection.get(1), projection.get(2));
        //System.out.println("is one pyramid");
        if(c.IsInPlane(this.point, projection_plane)){
            return (float) 0.0;
        }else{
        	// chiều cao nhân diện tích đáy
        	// System.out.println(Area_Triangle(p));
        	return c.PointToPlane(this.point,projection_plane)*c.Area_Quadrilateral(this.projection)/3;
        }
    }
    

	


}
