package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import exception.*;
import model.*;
import model.Obj;
import model.space.Point;
import model.space.Vector2D;

public class ManagerObject {
    private List<Obj> objects;
    
    private int num_objs = 0;
    
    public ManagerObject(){
        this.objects = new ArrayList<>();
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
	private List<Obj> getListObject(Obj object,List<Obj> objList ){
        List<Obj> objList1 = objList.stream()
                .filter(o -> (object.getPoints()[4].getZ() == o.getPoints()[0].getZ()) && checkFullyContiguous(o,object))
                .collect(Collectors.toList());
        return objList1;
    }
	
	
    public void addObject (Obj object, Room room) throws InvalidObject {
        if(checkInRoom(room,object)){
            if(checkRectangularBox(object)){
                if(checkOverlapObject(object))
                    throw new InvalidObject("Invalid Object");
                else{
                    this.objects.add(object);
                    this.num_objs += 1;
                }
            }else
                throw new InvalidObject("Object is not Rectangular Box");
        }
        else{
            throw new InvalidObject("Object is in Room");
        }
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
    // Kiểm tra xem vật thể có phải hình hộp chữ nhật, nếu phải --> true
    private boolean checkRectangularBox (Obj object){
        // kiểm tra ABCD và A1B1C1D1 co la mat phang va song song voi mat day khong
        Point[] points = object.getPoints();
        if(points[0].getZ() != points[1].getZ() || points[0].getZ() != points[2].getZ() ||points[0].getZ() != points[3].getZ())
        {
            return false;
        }
        if(points[4].getZ() != points[5].getZ() || points[4].getZ() != points[6].getZ() ||points[4].getZ() != points[7].getZ())
        {
            return false;
        }
        if(points[4].getZ() <= points[0].getZ())
            return false;
        
        System.out.print("Check 2 mặt phẳng song song đáy đúng.\n");
        // kiem tra  hinh chieu cua ABCD va A1B1C1D1 len mat phang 0xy co trung nhau khong
        Point p1,p2 ;
        for(int i = 0 ; i <=3 ; i ++ ){
            p1 = points[i];
            p2 = points[i+4];
            if(p1.getX() != p2.getX() || p1.getY() != p2.getY())
                return false;
        }
        // kiem tra toa do duong
        for(int i = 0 ; i <=3 ; i ++ ){
            if(points[i].getX() <0 || points[i].getY() <0 || points[i].getZ() <0)
                return false;
        }

        System.out.print("Check hình chiếu trùng đúng.\n");
        // kiem tra ABCD hoac A1B1C1 la hinh chu nhat
        // Vecto AB(x,y) ; DC(x1,y1) ; AD(x2,y2)
        Vector2D AB = new Vector2D(points[1].getX() - points[0].getX(),points[1].getY() - points[0].getY());
        Vector2D DC = new Vector2D(points[2].getX() - points[3].getX(),points[2].getY() - points[3].getY());
        Vector2D AD = new Vector2D(points[3].getX() - points[0].getX(),points[3].getY() - points[0].getY());

        // kiem tra AB vuong goc AD : x*x2 + y*y2 = 0 ;
        if(!AB.checkPerpendicularVectors(AD))
        {
            return false;
        }
        System.out.print("Check AB vuông góc AD đúng.\n");
        // kiem tra AB // DC
        if(!AB.checkEqualVectors(DC))
        {
            return false;
        }
        System.out.print("Check song song đúng.\n");
        return true;
    }
    
    public boolean checkOverlapObject(Obj object) {
        if (!checkRectangularBox(object)) // kiểm tra vật thể có phải hình hộp chữ nhật hay không
        {
            return true;
        }
        if (object.getPoints()[0].getZ() == 0) // vật nằm trên sàn
        {
            // check overlap với những vật ở dưới sàn
            for (int i = 0 ;i < this.objects.size()  ; i++)
            {
                if(this.objects.get(i).getPoint(0).getZ() == 0){
                    if(is_Overlap_Projection(this.objects.get(i),object))
                    {
                        return true;
                    }
                }
            }
            return false;
        } // vật nằm trên vật khác thì luôn luôn ko bị overlap;
        else{
            if(checkOnObject(object,this.objects))
                return false;
            else
            {
                return true;
            }
        }
    }
    
    
//    public boolean checkOverlapObject(Obj object) throws ArrayIndexException {
//        for (int i = 0 ;i < this.objects.size() - 1; i++)
//        {
//            // nêu 1 vật nằm trên luôn hoặc nằm dưới luôn vật khác thì ko bị overlap.
//            if (this.objects.get(i).getPoint(0).getZ() <= object.getPoint(7).getZ() ||
//                    this.objects.get(i).getPoint(7).getZ() >= object.getPoint(0).getZ())
//                if (is_Overlap_Projection(this.objects.get(i), object))
//                    return false;
//        }
//        return true;
//    }
//
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

//    // check xem 1 obj có nằm trên obj nào không.
//    // nếu Z của obj > 0 thì mới call hàm dưới
//    // true là hàm thỏa mãn
//    public boolean checkHighObject(Obj object) throws ArrayIndexException {
//
//        for (int i = 0 ;i < this.objects.size() ; i++)
//        {
//            // nêu 1 vật có đáy bằng mặt trên của vật khác, và hình chiếu 2 vật overlap.
//            if (this.objects.get(i).getPoint(0).getZ() == object.getPoint(7).getZ())
//                if (is_Overlap_Projection(this.objects.get(i), object))
//                    return true;
//        }
//        return false;
//    }
    
    private boolean checkOnObject(Obj object,List<Obj> list) { // check vật nằm  nằm trên vật khác
        List<Obj> objList = list.stream()
                .filter(o -> object.getPoints()[0].getZ() == o.getPoints()[4].getZ())
                .collect(Collectors.toList());

        if (objList.size() == 0) {
            return false; // vật lơ lửng
        }
        else {
            int cnt = 0;
            for(int i = 0 ; i < objList.size();i++){
                Obj obj = objList.get(i);
                // check mặt đáy object(MNPQ)  tiếp xúc hoàn toàn với mặt trên obj(ABCD)
                Vector2D AB = new Vector2D(obj.getPoints()[1].getX() - obj.getPoints()[0].getX(), obj.getPoints()[1].getY() - obj.getPoints()[0].getY());
                Vector2D BC = new Vector2D(obj.getPoints()[2].getX() - obj.getPoints()[1].getX(), obj.getPoints()[2].getY() - obj.getPoints()[1].getY());
                // 1 điểm M nằm trong hcn ABCD <=>  0 <= dot(AB,AM) <= dot(AB,AB) &&  0 <= dot(BC,BM) <= dot(BC,BC)
                for (int j = 0; j <= 3; j++) {
                    Vector2D AM = new Vector2D(object.getPoints()[j].getX() - obj.getPoints()[0].getX(), object.getPoints()[i].getY() - obj.getPoints()[0].getY());
                    Vector2D BM = new Vector2D(object.getPoints()[j].getX() - obj.getPoints()[1].getX(), object.getPoints()[i].getY() - obj.getPoints()[1].getY());
                    if((0 <= AB.dotVectors(AM) && AB.dotVectors(AM) <= AB.dotVectors(AB)) && (0 <= BC.dotVectors(BM) && BC.dotVectors(BM) <= BC.dotVectors(BC)))
                        continue;
                    else {
                        cnt ++;
                        break;
                    }
                }
                if(cnt == 0){
                    List<Obj> list1 =  getListObject(obj,this.objects);
                    if(list1.size() ==0 ){
                        return true;
                    }
                    else{
                        Obj objOverlapProjection = list1.stream().filter(o -> is_Overlap_Projection(obj,o)).findFirst().orElse(null);
                        if(objOverlapProjection != null){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }
                else{
                    cnt = 0;
                }
            }
            return false; // vật nằm lơ lửng => return false
        }
    }
    private boolean checkFullyContiguous(Obj obj1,Obj obj2 ){ // check mặt đáy obj1 tiếp xúc hoàn toàn với mặt trên obj2  (obj1 trên obj2 )
        Vector2D AB = new Vector2D(obj2.getPoints()[1].getX() - obj2.getPoints()[0].getX(), obj2.getPoints()[1].getY() - obj2.getPoints()[0].getY());
        Vector2D BC = new Vector2D(obj2.getPoints()[2].getX() - obj2.getPoints()[1].getX(), obj2.getPoints()[2].getY() - obj2.getPoints()[1].getY());
        // 1 điểm M nằm trong hcn ABCD <=>  0 <= dot(AB,AM) <= dot(AB,AB) &&  0 <= dot(BC,BM) <= dot(BC,BC)
        for (int i = 0; i <= 3; i++) {
            Vector2D AM = new Vector2D(obj1.getPoints()[i].getX() - obj2.getPoints()[0].getX(), obj1.getPoints()[i].getY() - obj2.getPoints()[0].getY());
            Vector2D BM = new Vector2D(obj1.getPoints()[i].getX() - obj2.getPoints()[1].getX(), obj1.getPoints()[i].getY() - obj2.getPoints()[1].getY());
            if((0 <= AB.dotVectors(AM) && AB.dotVectors(AM) <= AB.dotVectors(AB)) && (0 <= BC.dotVectors(BM) && BC.dotVectors(BM) <= BC.dotVectors(BC)))
                continue;
            else {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkInRoom(Room room, Obj object){
        double width = room.getWidth();
        double length = room.getLength();
        double height = room.getHeight();
        for(int i = 0 ; i <= 7 ; i++){
            if(object.getPoints()[i].getX() > width || object.getPoints()[i].getY() > length)
                return false;
        }
        if (object.getPoints()[0].getZ() <= height )
            return true;
        else
            return false;
    }
    
    public String printInfo()
    {
//    	for(int i=0 ; i< this.num_objs; i++) {
//            System.out.println("Obj " + (i+1));
//            for(int j=0 ; j<8; j++) {
//                System.out.printf("%.0f cm %.0f cm %.0f cm %n", this.getObjects().get(i).getPoints()[j].getX(), 
//                                                        this.getObjects().get(i).getPoints()[j].getY(), 
//                                                        this.getObjects().get(i).getPoints()[j].getZ());
//            }
//        }
    	String str = "";
        for(int i = 0; i < num_objs ; i++) {
            str += "Object " + (i+1) + ":\n";
            for(int j=0; j<8 ; j++){
                str += this.getObjects().get(i).getPoints()[j].printInfo();
            }
        }
        return str;
    }

}
