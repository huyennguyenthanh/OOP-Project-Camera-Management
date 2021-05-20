package Core;

import java.util.ArrayList;
import java.util.List;
import Exception.ExistedObjectException;

public class ManagerObject {
    private List<Obj> objects;
    public ManagerObject(){
        this.objects = new ArrayList<>();
    }
    public void addObject (Obj object) throws ExistedObjectException {
        if(!checkExistObject(object)) {
            this.objects.add(object);
        }else{
            throw new ExistedObjectException("Object already exist !");
        }
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
