package controller;

import model.Camera;
import model.Room;

import java.util.ArrayList;
import java.util.List;

import Exception.ExistedObjectException;

public class ManagerCamera {
    private List<Camera> cameras;
    private int num_cams;
    
    public ManagerCamera(){
        this.cameras = new ArrayList<>();
    }
    
    public void addCamera(Camera camera) {
    	if(!checkExistCamera(camera)) {
            this.cameras.add(camera);
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
    	return true;
        
    }
    public boolean checkOnCeiling(Camera camera, Room room){
    	return true;
        

    }
    
    public void printInfo() {
    	for(int i=0 ; i < this.num_cams ; i++) {
            System.out.println("Camera " + (i+1));
            
                System.out.printf("%.0f cm %.0f cm  %.0f cm %n", this.getCameras().get(i).getPoint().getX(), 
                                                    this.getCameras().get(i).getPoint().getY(), 
                                                    this.getCameras().get(i).getPoint().getZ());
            
        }
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
