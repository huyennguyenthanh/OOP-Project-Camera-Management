package controller;

import model.Camera;
import model.Room;
import model.space.Point;

import java.util.ArrayList;
import java.util.List;

public class ManagerCamera {
    private List<Camera> cameras;
    private int num_cams = 0;
    
    public ManagerCamera(){
        this.cameras = new ArrayList<>();
    }
    
    public void addCamera(Camera camera, Room room) {
    	if(!checkExistCamera(camera)) {
            this.cameras.add(camera);
            this.cameras.get(num_cams).setIs_on_ceil(checkOnCeiling(camera, room)) ;
            this.cameras.get(num_cams).setIs_on_ceil(checkOnWall(camera, room));
            this.cameras.get(num_cams).cal_projection(room); ;
            this.num_cams += 1;
            
    	}
        
    }

    public Camera searchCamera(Camera camera){
        return this.cameras.stream()
                .filter(o -> o.getPoint().equals(camera.getPoint()) )
                .findFirst().orElse(null);
    }
    public boolean deleteCamera(Camera camera) {
        Camera cam = searchCamera(camera);
        if (cam == null) {
            return false;
        }
        this.cameras.remove(cam);
        return true;
    }
    public boolean checkExistCamera(Camera camera){
        if(searchCamera(camera) != null)
            return true;
        else
            return false;
    }
    
    
    public boolean checkOnWall(Camera camera, Room room){
    	Point point = camera.getPoint();
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
        // check thuộc mặt phẳng A1D1DA
        if(point.getX() == 0 && point.getY() <= room.getLength() && point.getZ() <= room.getHeight())
            return true;
        return false;

        
    }
    
    public boolean checkOnCeiling(Camera camera, Room room){
    	Point point = camera.getPoint();
        if(point.getZ() == 0 )
            return false;
        // check thuộc mặt phẳng trần (A1B1C1D1)
        if(point.getZ() == room.getHeight() && point.getX() <= room.getWidth() && point.getY() <= room.getLength())
            return true;
        else
            return false;
    }
    
    
    public String printInfo() {
    	String str = null;
//    	for(int i=0 ; i < this.num_cams ; i++) {
//            System.out.println("Camera " + (i+1));
//            
//                System.out.printf("%.0f cm %.0f cm  %.0f cm %n", this.getCameras().get(i).getPoint().getX(), 
//                                                    this.getCameras().get(i).getPoint().getY(), 
//                                                    this.getCameras().get(i).getPoint().getZ());
//            
//        }
    	str += "Number of cameras: " + num_cams + "cam \n";
    	for(int i=0 ; i< num_cams ; i++) {
            str += "Camera " + (i+1) + ":\n"
                + "("
                + this.getCameras().get(i).getPoint().getX()/100 + ", "
                + this.getCameras().get(i).getPoint().getZ()/100 + ", "
                + this.getCameras().get(i).getPoint().getX()/100 + ")\n"
                + "Height angle: " + this.getCameras().get(i).getHeight_angle() + "\n"
                + "Width angle: " + this.getCameras().get(i).getWidth_angle() + "\n\n";
        }
    	return str;
    }

	public List<Camera> getCameras() {
		return cameras;
	}

	public void setCameras(List<Camera> cameras) {
		this.cameras = cameras;
	}

	public int getNum_cams() {
		return num_cams;
	}

	public void setNum_cams(int num_cams) {
		this.num_cams = num_cams;
	}
}
