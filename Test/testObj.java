package Test;

import org.junit.Assert;
import model.Obj;
import model.Point;
import model.Room;

public class testObj {
    public static void main(String [] argv){
        testCase1();
        testCase2();
        testCase3();
        testCase4();
    }
    public static void testCase1(){
        Point[] points = new Point[8];
//        Room r1 = new Room(50,100,100);
//        Room r2 = new Room(50,100,40);
//        Room r3 = new Room(10,20,100);
        points[0] = new Point(10,10,50);
        points[1] = new Point(10,30,50);
        points[2] = new Point(20,30,50);
        points[3] = new Point(20,10,50);
        points[4] = new Point(10,10,0);
        points[5] = new Point(10,30,0);
        points[6] = new Point(20,30,0);
        points[7] = new Point(20,10,0);
        Obj o =  new Obj(points);
        Obj o1 = new Obj(points);
//        Assert.assertTrue(o.checkRectangularBox());
//        Assert.assertTrue(o.equals(o1));
//        Assert.assertTrue(o.checkInRoom(r1));
//        Assert.assertFalse(o.checkInRoom(r2));
//        Assert.assertFalse(o.checkInRoom(r3));
    }
    public static void testCase2(){ // hình hộp đứng có đáy là hình bình hành
        Point[] points = new Point[8];
        points[0] = new Point(10,20,50);
        points[1] = new Point(10,30,50);
        points[2] = new Point(20,20,50);
        points[3] = new Point(20,10,50);
        points[4] = new Point(10,20,0);
        points[5] = new Point(10,30,0);
        points[6] = new Point(20,20,0);
        points[7] = new Point(20,10,0);
        Obj o =  new Obj(points);
//        Assert.assertFalse(o.checkRectangularBox());
        Point[] points2 = new Point[8];
        points2[0] = new Point(10,10,50);
        points2[1] = new Point(10,30,50);
        points2[2] = new Point(20,30,50);
        points2[3] = new Point(20,10,50);
        points2[4] = new Point(10,10,0);
        points2[5] = new Point(10,30,0);
        points2[6] = new Point(20,30,0);
        points2[7] = new Point(20,10,0);
        Obj o2 =  new Obj(points2);
        Assert.assertFalse(o.equals(o2));
    }
    public static void testCase3(){
        Point[] points = new Point[8];
        points[0] = new Point(10,10,50);
        points[1] = new Point(10,30,50);
        points[2] = new Point(20,30,50);
        points[3] = new Point(20,10,50);
        points[4] = new Point(10,10,10);
        points[5] = new Point(10,30,10);
        points[6] = new Point(20,30,10);
        points[7] = new Point(20,10,10);
        Obj o =  new Obj(points);
//        Assert.assertTrue(o.checkRectangularBox());
    }
    public static void testCase4(){
        Point[] points = new Point[8];
        points[0] = new Point(10,10,50);
        points[1] = new Point(10,30,50);
        points[2] = new Point(20,30,50);
        points[3] = new Point(20,10,50);
        points[4] = new Point(10,10,10);
        points[5] = new Point(10,30,10);
        points[6] = new Point(20,30,10);
        points[7] = new Point(20,10,10);
        Obj o =  new Obj(points);
//        Assert.assertTrue(o.checkRectangularBox());
        Point[] points2 = new Point[8];
        points2[0] = new Point(10,10,50);
        points2[1] = new Point(10,30,50);
        points2[2] = new Point(20,30,50);
        points2[3] = new Point(20,10,50);
        points2[4] = new Point(10,10,10);
        points2[5] = new Point(10,30,30);
        points2[6] = new Point(20,30,10);
        points2[7] = new Point(20,10,10);
        Obj o2 =  new Obj(points2);
//        Assert.assertFalse(o2.checkRectangularBox());
    }

}
