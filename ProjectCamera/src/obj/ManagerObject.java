package obj;

import java.util.ArrayList;
import java.util.List;


public class ManagerObject {
    private List<Obj> objects;
    
    
    public ManagerObject(){
        this.objects = new ArrayList<>();
    }

    public List<Obj> getObjects() {
        return objects;
    }
    public void setObjects(List<Obj> objects) {
        this.objects = objects;
    }

    public void addObject (Obj object){
         this.objects.add(object);
    }
    public boolean deleteObject(Obj object) {
        Obj obj = searchObject(object);
        if (obj == null) {
            return false;
        }
        this.objects.remove(obj);
        return true;
    }
    public boolean checkExistObject(Obj object){
        if(searchObject(object) != null)
            return true;
        else
            return false;
    }

    public Obj searchObject(Obj object){
        Obj obj = this.objects.stream()
                .filter(o -> o.equals(object))
                .findFirst().orElse(null);
        return obj;
    }
}
