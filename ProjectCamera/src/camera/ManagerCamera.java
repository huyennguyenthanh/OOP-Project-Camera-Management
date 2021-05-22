package camera;

import java.util.ArrayList;
import java.util.List;

public class ManagerCamera {
    private List<Camera> cameras;
    
    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }

    public ManagerCamera(){
        this.cameras = new ArrayList<>();
    }

    public void add(Camera camera) {
        this.cameras.add(camera);
    }


    public Camera searchCamera(Camera camera){
        return this.cameras.stream()
                .filter(o -> o.equals(camera))
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
}