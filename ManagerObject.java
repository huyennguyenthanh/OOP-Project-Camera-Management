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
    public boolean checkInRoom(Room room, Obj object){
        Point[] pointsObject = object.getPoints();
        int width = room.getWidth();
        int length = room.getLength();
        int height = room.getHeight();
        for(int i = 0 ; i <= 7 ; i++){
            if(pointsObject[i].getX() > width || pointsObject[i].getY() > length)
                return false;
        }
        if (pointsObject[0].getZ() <= height )
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
