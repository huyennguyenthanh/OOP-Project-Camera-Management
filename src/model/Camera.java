package model;

import model.space.Point;
import model.space.Plane;


import java.util.ArrayList;

import model.space.*;

public class Camera {
    private Point point = new Point();
    private double height_angle;
    private double width_angle;
    
    private boolean is_on_wall = true;
    private boolean is_on_ceil = true;
    

    private ArrayList<Point> projection = new ArrayList<>();
    private double V;
    public Camera() {

    }

    public Camera(Point point, double w_angle, double h_angle) {
        this.point = point;
        this.width_angle = (double) Math.toRadians(h_angle/2);
        this.height_angle = (double) Math.toRadians(h_angle/2);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getHeight_angle() {
        return height_angle;
    }

    public void setHeight_angle(double height_angle) {
        this.height_angle = height_angle;
    }

    public double getWidth_angle() {
        return width_angle;
    }

    public void setWidth_angle(double width_angle) {
        this.width_angle = width_angle;
    }
   
	public ArrayList<Point> getProjection() {
		return projection;
	}

	public void setProjection(ArrayList<Point> projection) {
		this.projection = projection;
	}

	public double getV() {
		return V;
	}

	public void setV(double v) {
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
    
    public void cal_projection(Room room) {
    	

    	double length = (double) Math.round(this.point.getZ() * Math.tan(this.height_angle*Math.PI/360));
    	double width = (double) Math.round(this.point.getZ() * Math.tan(this.width_angle*Math.PI/360));
    	
    	if (this.is_on_ceil) {
    	// t??m h??nh chi???u khi camera t??? tr??n tr???n chi???u xu???ng
    	// center of projection
    	Point center_point = new Point(this.point.getX(), this.point.getY(), 0);
    	//System.out.print("\nlength v?? width :"+ length +" "+  width);
    	
    	// 4 ?????nh c???a h??nh chi???u camera tr??n m???t ?????t
    	this.projection.add(new Point(center_point.getX() - length, center_point.getY() - width, 0)); //A
    	this.projection.add(new Point(center_point.getX() + length, center_point.getY() - width, 0)); // B
    	this.projection.add(new Point(center_point.getX() + length, center_point.getY() + width, 0)); // C
    	this.projection.add(new Point(center_point.getX() - length, center_point.getY() + width, 0)); // D
    	
    	return;
    	}
    	
    	 if (this.is_on_wall) {
             // t??m h??nh chi???u khi camera t??? t?????ng chi???u ngang
             // n???u camera n???m ??? t?????ng A1B1BA (y = 0)
             if(this.point.getY() == 0){
                 Point center_point = new Point(this.point.getX(), room.getLength(),this.point.getZ());
                 this.projection.add(new Point(center_point.getX()+width,room.getLength(),center_point.getZ()+length));
                 this.projection.add(new Point(center_point.getX()-width,room.getLength(),center_point.getZ()+length));
                 this.projection.add(new Point(center_point.getX()-width,room.getLength(), center_point.getZ()-length));
                 this.projection.add(new Point(center_point.getX()+width,room.getLength(), center_point.getZ()-length));
                 return;
             }
             // n???u camera n???m ??? t?????ng B1C1CB (x = room.getWidth)
             if(this.point.getX() == room.getWidth()){
                 Point center_point = new Point(0,this.point.getY(),this.point.getZ());
                 this.projection.add(new Point(0,center_point.getY()-width, center_point.getZ()+length));
                 this.projection.add(new Point(0,center_point.getY()+width,center_point.getZ()+length));
                 this.projection.add(new Point(0, center_point.getY()+width, center_point.getZ()-length));
                 this.projection.add(new Point(0, center_point.getY()-width, center_point.getZ()-length));
                 return;
             }
             // n???u camera n???m ??? t?????ng C1D1DC ( y = room.getLength)
             if(this.point.getY() == room.getLength()){
                 Point center_point = new Point(this.point.getX(), 0,this.point.getZ());
                 this.projection.add(new Point(center_point.getX()+width,0,center_point.getZ()+length));
                 this.projection.add(new Point(center_point.getX()-width,0, center_point.getZ()+length));
                 this.projection.add(new Point(center_point.getX()-width,0, center_point.getZ()-length));
                 this.projection.add(new Point(center_point.getX()+width,0, center_point.getZ()-length));
                 return;
             }
             // n???u camera n???m ??? t?????ng A1D1DA (x = 0)
             if(this.point.getX() == 0){
                 Point center_point = new Point(room.getWidth(), this.point.getY(),this.point.getZ());
                 this.projection.add(new Point(room.getWidth(), center_point.getY()-width, center_point.getZ()+length));
                 this.projection.add(new Point(room.getWidth(), center_point.getY()+width, center_point.getZ()+length));
                 this.projection.add(new Point(room.getWidth(), center_point.getY()+width, center_point.getZ()-length));
                 this.projection.add(new Point(room.getWidth(),center_point.getY()-width, center_point.getZ()-length));
                 return;
             }
    	 }	

    }
    
    // volumne of visible area of camera
    public double volume_visible_area()
    {
    	Calculation c = new Calculation();
    	//System.out.println("hereee ");
    	Plane projection_plane = new Plane(projection.get(0), projection.get(1), projection.get(2));
        //System.out.println("is one pyramid");
        if(c.IsInPlane(this.point, projection_plane)){
            return (double) 0.0;
        }else{
        	// chi???u cao nh??n di???n t??ch ????y
        	//System.out.println("hereee ");
        	return c.PointToPlane(this.point,projection_plane)*c.Area_Quadrilateral(this.projection)/3;
        }
    }
    
    public String printInfo()
    {
    	String str = "\n";
    	str += "("
                + this.getPoint().getX()/100 + ", "
                + this.getPoint().getY()/100 + ", "
                + this.getPoint().getZ()/100 + ")\n"
                + "Height angle: " + this.getHeight_angle() + "\n"
                + "Width angle: " + this.getWidth_angle() + "\n\n";
    	if (this.is_on_ceil)
    		str += "Camera g???n tr??n tr???n.\n\n";
    	else if (this.is_on_wall)
    		str += "Camera g???n tr??n t?????ng.\n\n";
    	return str;
    }
    
    public String printInfoProjection() {
    	String str = "\nH??nh chi???u camera: ";
    	for (int i = 0; i < 4; i++)
    	{
    		str += "(" + this.projection.get(i).getX() + ", "
    				+  this.projection.get(i).getY() + ", "
    						+ this.projection.get(i).getZ() + ")\n";
    	}
    	
    	return str;
    }
    
    


}
