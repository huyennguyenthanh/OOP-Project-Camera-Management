package Core;

import Core.Point;

public class Room {
    private Point pointA,pointB,pointC,pointD,pointA1,pointB1,pointC1,pointD1;

    public Room(){
        this.pointA1 = new Point(0,0,0);
    }
    public Room(Point pointA,Point pointB,Point pointC,Point pointD,Point pointB1,Point pointC1,Point pointD1){
        this.pointA1 = new Point(0,0,0);
        this.pointA = pointA;
        this.pointB = pointB;
        this.pointC = pointC;
        this.pointD = pointD;
        this.pointB1 = pointB1;
        this.pointC1 = pointC1;
        this.pointD1 = pointD1;
    }
    public Room(int width, int length, int height){ // width :AD <=> Ox ; length : AB <=> Oy:  ; height : AA1 <=> Oz
        this.pointA = new Point(0,0,height);
        this.pointB = new Point(0,length,height);
        this.pointC = new Point(width,length,height);
        this.pointD = new Point(width,0,height);
        this.pointA1 = new Point(0,0,0);
        this.pointB1 = new Point(0,length,0);
        this.pointC1 = new Point(width,length,0);
        this.pointD1 = new Point(width,0,0);
    }
    public Point getPointA() {
        return pointA;
    }

    public void setPointA(Point pointA) {
        this.pointA = pointA;
    }

    public Point getPointB() {
        return pointB;
    }

    public void setPointB(Point pointB) {
        this.pointB = pointB;
    }

    public Point getPointC() {
        return pointC;
    }

    public void setPointC(Point pointC) {
        this.pointC = pointC;
    }

    public Point getPointD() {
        return pointD;
    }

    public void setPointD(Point pointD) {
        this.pointD = pointD;
    }

    public Point getPointA1() {
        return pointA1;
    }

    public Point getPointB1() {
        return pointB1;
    }

    public void setPointB1(Point pointB1) {
        this.pointB1 = pointB1;
    }

    public Point getPointC1() {
        return pointC1;
    }

    public void setPointC1(Point pointC1) {
        this.pointC1 = pointC1;
    }

    public Point getPointD1() {
        return pointD1;
    }

    public void setPointD1(Point pointD1) {
        this.pointD1 = pointD1;
    }
    public int getHeight(){
        return this.pointC.getZ();
    }
    public int getWidth(){
        return this.pointC.getX();
    }
    public int getLength(){
        return this.pointC.getZ();
    }
}
