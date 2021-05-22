package controller;

import model.Camera;

import java.util.ArrayList;
import java.util.List;

public class ManagerCamera {
    private List<Camera> cameras;
    public ManagerCamera(){
        this.cameras = new ArrayList<>();
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
}
