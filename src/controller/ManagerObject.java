package controller;

import java.util.ArrayList;
import java.util.List;
import Exception.*;
import model.Obj;
import model.Room;
import model.space.Point;
import model.space.Vector2D;

public class ManagerObject {
    private List<Obj> objects;
    
    private int num_objs = 0;
    
    public ManagerObject(){
        this.objects = new ArrayList<>();
    }
    public void addObject (Obj object, Room room) throws ExistedObjectException {
        if(!checkExistObject(object)) {
            this.objects.add(object);
        }else{
            throw new ExistedObjectException("Object already exist !");
        }
        this.num_objs += 1;
    }
    public Obj searchObject(Obj object){
        Obj obj = this.objects.stream()
                .filter(o -> o.equals(object))
                .findFirst().orElse(null);
        return obj;
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
    public boolean checkSetPoint(Obj object) { // check vật nằm trên sàn hoặc nằm trên vật khác
        if (!object.checkRectangularBox()) // kiểm tra vật thể có phải hình hộp chữ nhật hay không
            return false;
        if (object.getPoints()[0].getZ() == 0) // vật nằm trên sàn
        {
            return true;
        }
            // vật nằm trên vật khác
        else {
            Obj obj = this.objects.stream()
                    .filter(o -> object.getPoints()[4].getZ() == (object.getHeight() + o.getHeight()))
                    .findFirst().orElse(null);
            if (obj == null)
                return false; // vật nằm lơ lửng => return false
            else {
                // check mặt đáy object(MNPQ)  tiếp xúc hoàn toàn với mặt trên obj(ABCD)
                Vector2D AB = new Vector2D(obj.getPoints()[1].getX() - obj.getPoints()[0].getX(), obj.getPoints()[1].getY() - obj.getPoints()[0].getY());
                Vector2D BC = new Vector2D(obj.getPoints()[2].getX() - obj.getPoints()[1].getX(), obj.getPoints()[2].getY() - obj.getPoints()[1].getY());
                // 1 điểm M nằm trong hcn ABCD <=>  0 <= dot(AB,AM) <= dot(AB,AB) &&  0 <= dot(BC,BM) <= dot(BC,BC)
                for (int i = 0; i <= 3; i++) {
                    Vector2D AM = new Vector2D(object.getPoints()[i].getX() - obj.getPoints()[0].getX(), object.getPoints()[i].getY() - obj.getPoints()[0].getY());
                    Vector2D BM = new Vector2D(object.getPoints()[i].getX() - obj.getPoints()[1].getX(), object.getPoints()[i].getY() - obj.getPoints()[1].getY());
                    if((0 <= AB.dotVectors(AM) && AB.dotVectors(AM) <= AB.dotVectors(AB)) && (0 <= BC.dotVectors(BM) && BC.dotVectors(BM) <= BC.dotVectors(BC)))
                        continue;
                    else
                        return false;
                }
            }
            return true;
        }
    }
    public boolean checkOverlapObject(Obj object) throws ArrayIndexException {
        for (int i = 0 ;i < this.objects.size() - 1; i++)
        {
            // nêu 1 vật nằm trên luôn hoặc nằm dưới luôn vật khác thì ko bị overlap.
            if (this.objects.get(i).getPoint(0).getZ() <= object.getPoint(7).getZ() ||
                    this.objects.get(i).getPoint(7).getZ() >= object.getPoint(0).getZ())
                if (is_Overlap_Projection(this.objects.get(i), object))
                    return false;
        }
        return true;
    }

    //check overlap giữa 2 hình chiếu. true là bị overlap
    private boolean is_Overlap_Projection(Obj o1, Obj o2)
    {
        Point[] points1 = o1.getPoints();
        Point[] points2 = o2.getPoints();
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
            {
                if (is_2line_intersect(points1[i], points1[i == 3 ? 0 : i+1], points2[j], points2[j == 3? 0 : j+1]))
                    return false;
            }
        return true;
    }

    //counter clock wise order
    private boolean ccw(Point A, Point B, Point C) {
        return (((C.getY() - A.getY()) * (B.getX() - A.getX())) >= ((B.getY() - A.getY()) * (C.getX() - A.getX())));
    }

    // if 2 line segment intersect --> true
    private boolean is_2line_intersect(Point A, Point B, Point C, Point D) {

        return (ccw(A,C,D) != ccw(B,C,D) && ccw(A, B, C) != ccw(A,B,D));

    }


    // check xem 1 obj có nằm trên obj nào không.
    // nếu Z của obj > 0 thì mới call hàm dưới
    // true là hàm thỏa mãn
    public boolean checkHighObject(Obj object) throws ArrayIndexException {

        for (int i = 0 ;i < this.objects.size() ; i++)
        {
            // nêu 1 vật có đáy bằng mặt trên của vật khác, và hình chiếu 2 vật overlap.
            if (this.objects.get(i).getPoint(0).getZ() == object.getPoint(7).getZ())
                if (is_Overlap_Projection(this.objects.get(i), object))
                    return true;
        }
        return false;
    }
    
    public void printInfo()
    {
    	for(int i=0 ; i< this.num_objs; i++) {
            System.out.println("Obj " + (i+1));
            for(int j=0 ; j<8; j++) {
                System.out.printf("%.0f cm %.0f cm %.0f cm %n", this.getObjects().get(i).getPoints()[j].getX(), 
                                                        this.getObjects().get(i).getPoints()[j].getY(), 
                                                        this.getObjects().get(i).getPoints()[j].getZ());
            }
        }

    }
	public List<Obj> getObjects() {
		return objects;
	}
	public void setObjects(List<Obj> objects) {
		this.objects = objects;
	}
	public int getNum_objs() {
		return num_objs;
	}
	public void setNum_objs(int num_objs) {
		this.num_objs = num_objs;
	}
}
