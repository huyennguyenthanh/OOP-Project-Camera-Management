package model;


import model.space.Point;
import model.space.Vector2D;
public class Obj  {
    private Point[] points = new Point[8];

    public Obj(Point[] points) {
        this.points = points;
    }
    public Obj(Point pointA, Point pointB, Point pointC, Point pointD, Point pointA1, Point pointB1, Point pointC1, Point pointD1){
        this.points[0] = pointA;
        this.points[1] = pointB;
        this.points[2] = pointC;
        this.points[3] = pointD;
        this.points[4] = pointA1;
        this.points[5] = pointB1;
        this.points[6] = pointC1;
        this.points[7] = pointD1;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
    public Point getPoint (int index)  {
        return points[index];
    }
    public void setPoint(int index, Point point) throws ArrayIndexOutOfBoundsException{
        this.points[index] = point;
    }
    public double getHeight(){
        return points[4].getZ() - points[0].getZ();
    }
    
    
    public boolean equals (Obj object){
        if(object != null){
            Point[] pointsObject = object.getPoints();
            for(int i = 0 ; i <= 7 ; i++){
                if(this.points[i] != pointsObject[i])
                    return false;
            }
            return true;
        }else {
            return false;
        }
    }
    
    
    // Kiểm tra xem vật thể có phải hình hộp chữ nhật
    public boolean checkRectangularBox (){
        // kiểm tra ABCD và A1B1C1D1 co la mat phang va song song voi mat day khong
        if(points[0].getZ() != points[1].getZ() || points[0].getZ() != points[2].getZ() ||points[0].getZ() != points[3].getZ())
            return false;
        if(points[4].getZ() != points[5].getZ() || points[4].getZ() != points[6].getZ() ||points[4].getZ() != points[7].getZ())
            return false;
        // kiem tra  hinh chieu cua ABCD va A1B1C1D1 len mat phang 0xy co trung nhau khong

        Point p1,p2 ;
        for(int i = 0 ; i <=3 ; i ++ ){
            p1 = points[i];
            p2 = points[i+4];
            if(p1.getX() != p2.getX() || p1.getY() != p2.getY())
                return false;
        }

        // kiem tra ABCD hoac A1B1C1 la hinh chu nhat
        // Vecto AB(x,y) ; DC(x1,y1) ; AD(x2,y2)
        Vector2D AB = new Vector2D(points[1].getX() - points[0].getX(),points[1].getY() - points[0].getY());
        Vector2D DC = new Vector2D(points[2].getX() - points[3].getX(),points[2].getY() - points[3].getY());
        Vector2D AD = new Vector2D(points[3].getX() - points[0].getX(),points[3].getY() - points[0].getY());

        // kiem tra AB vuong goc AD : x*x2 + y*y2 = 0 ;
        if(AB.checkPerpendicularVectors(AD))
            return false;
        // kiem tra AB // DC
        if(AB.checkEqualVectors(DC))
            return false;
        else
            return true;
    }
    public boolean checkInRoom(Room room){
        double width = room.getWidth();
        double length = room.getLength();
        double height = room.getHeight();
        for(int i = 0 ; i <= 7 ; i++){
            if(this.points[i].getX() > width || this.points[i].getY() > length)
                return false;
        }
        if (this.points[0].getZ() <= height )
            return true;
        else
            return false;
    }
}
