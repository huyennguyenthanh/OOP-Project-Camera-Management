package model;

import model.space.Point;
import model.space.Vector2D;

public class Room {
    private Point [] points = new Point[8];
    
    

    public Room(Point[] points){
        this.points = points;
    }
    public Room(Point B, Point C, Point D, Point A1, Point B1, Point C1, Point D1){
        this.points[0] = new Point(0,0,0);
        this.points[1] = B;
        this.points[2] = C;
        this.points[3] = D;
        this.points[4] = A1;
        this.points[5] = B1;
        this.points[6] = C1;
        this.points[7] = D1;
    }
    public Point[] getPoints() {
        return points;
    }
    public void setPoints(Point[] points) {
        this.points = points;
    }

    public float getWidth(){
        return this.points[6].getX();
    }

    public float getLength(){
        return this.points[6].getY();
    }

    public float getHeight(){
        return this.points[6].getZ();
    }
    public float get_V () {
    	return this.getHeight() * this.getLength() * this.getWidth();
    }

    public boolean checkListPoints(){
        // check điểm A trùng gốc tọa độ
        if(this.points[0].getX() != 0 || this.points[0].getZ() !=0 || this.points[0].getZ() !=0)
            return false;
        // check các giá trị dương
        for (int i = 0 ; i <=7 ; i++){
            if(this.points[i].getX() <0 || this.points[i].getY() <0 || this.points[i].getZ() <0)
                return false;
        }
        // check hình hộp chữ nhật

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
    
    public String printInfo()
    {
    	String str = "";
    	System.out.println("ROOM: ");
    	str += "ROOM (m): \n";
        for(int i = 0; i < 8 ; i++) {
            str += "(" + this.getPoints()[i].getX()/100 + " , "
                        + this.getPoints()[i].getY()/100 + ", "
                        + this.getPoints()[i].getZ()/100 + ") \n";
        }
//    	for(int i=0 ; i<8 ; i++) {
//            System.out.printf("(%.2f %.2f %.2f) %n",this.getPoints()[i].getX(), 
//                                                    this.getPoints()[i].getY(), 
//                                                    this.getPoints()[i].getZ());
//        }
    	return str;
    }
    
}
