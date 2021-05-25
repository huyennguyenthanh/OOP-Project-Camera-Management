package Test;

import Exception.*;
import controller.ManagerObject;
import model.Obj;
import model.Point;
import model.Room;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class testManagerObject {
    public static void main(String[]argv) throws InvalidObject {
        Point[] points = new Point[8];
        points[0] = new Point(30,10,0);
        points[1] = new Point(50,10,0);
        points[2] = new Point(50,30,0);
        points[3] = new Point(30,30,0);
        points[4] = new Point(30,10,10);
        points[5] = new Point(50,10,10);
        points[6] = new Point(50,30,10);
        points[7] = new Point(30,30,10);
        Obj obj = new Obj(points); // height = 10

        Point[] points1 = new Point[8];
        points1[0] = new Point(20,30,0);
        points1[1] = new Point(60,30,0);
        points1[2] = new Point(60,50,0);
        points1[3] = new Point(20,50,0);
        points1[4] = new Point(20,30,20);
        points1[5] = new Point(60,30,20);
        points1[6] = new Point(60,50,20);
        points1[7] = new Point(20,50,20);
        Obj obj1 = new Obj(points1); // height = 20;

        Point[] points2 = new Point[8];
        points2[0] = new Point(10,60,0);
        points2[1] = new Point(50,60,0);
        points2[2] = new Point(50,100,0);
        points2[3] = new Point(10,100,0);
        points2[4] = new Point(10,60,30);
        points2[5] = new Point(50,60,30);
        points2[6] = new Point(50,100,30);
        points2[7] = new Point(10,100,30);
        Obj obj2 = new Obj(points2); // height = 30;

        Point[] points3 = new  Point[8];
        points3[0] = new Point(60,60,0);
        points3[1] = new Point(80,40,0);
        points3[2] = new Point(90,50,0);
        points3[3] = new Point(70,70,0);
        points3[4] = new Point(60,60,10);
        points3[5] = new Point(80,40,10);
        points3[6] = new Point(90,50,10);
        points3[7] = new Point(70,70,10);
        Obj obj3 = new Obj(points3); // chéo . height = 10;

        Point[] points4 = new Point[8];
        points4[0] = new Point(20,70,30);
        points4[1] = new Point(30,70,30);
        points4[2] = new Point(30,80,30);
        points4[3] = new Point(20,80,30);
        points4[4] = new Point(20,70,40);
        points4[5] = new Point(30,70,40);
        points4[6] = new Point(30,80,40);
        points4[7] = new Point(20,80,40);
        Obj obj4 = new Obj(points4); // height = 10;

        Point[] points5 = new Point[8];
        points5[0] = new Point(30,80,30);
        points5[1] = new Point(50,80,30);
        points5[2] = new Point(50,100,30);
        points5[3] = new Point(30,100,30);
        points5[4] = new Point(30,80,40);
        points5[5] = new Point(50,80,40);
        points5[6] = new Point(50,100,40);
        points5[7] = new Point(30,100,40);
        Obj obj5 = new Obj(points5);

        Point[] points6 = new Point[8];
        points6[0] = new Point(40,90,40);
        points6[1] = new Point(50,90,40);
        points6[2] = new Point(50,100,40);
        points6[3] = new Point(40,100,40);
        points6[4] = new Point(40,90,50);
        points6[5] = new Point(50,90,50);
        points6[6] = new Point(50,100,50);
        points6[7] = new Point(40,100,50);
        Obj obj6 = new Obj(points6);

        ManagerObject objectsList = new ManagerObject();
        objectsList.addObject(obj);
        objectsList.addObject(obj1);
        objectsList.addObject(obj2);
        objectsList.addObject(obj3);
        objectsList.addObject(obj4);
        objectsList.addObject(obj5);
        objectsList.addObject(obj6);

        Point A = new Point(3,1,0);
        Point B = new Point(3,3,0);
        Point C = new Point(5,3,0);
        Point D = new Point(5,1,0);


        checkOverLap1(objectsList);
        checkOverLap2(objectsList);
        checkOverLap3(objectsList);
        checkOverLap4(objectsList);
        checkOverLap5(objectsList);
        checkOverLap6(objectsList);
        checkOverLap7(objectsList);
        checkOverLap8(objectsList);
    }
    public static void checkOverLap1(ManagerObject objectsList){
        // không bị overlap
        Point[] points = new Point[8];
        points[0] = new Point(10,10,0);
        points[1] = new Point(30,10,0);
        points[2] = new Point(30,30,0);
        points[3] = new Point(10,30,0);
        points[4] = new Point(10,10,10);
        points[5] = new Point(30,10,10);
        points[6] = new Point(30,30,10);
        points[7] = new Point(10,30,10);

        Obj obj = new Obj(points);
        Assert.assertFalse(objectsList.checkOverlapObject(obj));

    }
    public static void checkOverLap2(ManagerObject objectsList){
        // bị overlap vs obj ;
        Point[] points = new Point[8];
        points[0] = new Point(30,10,0);
        points[1] = new Point(50,10,0);
        points[2] = new Point(50,30,0);
        points[3] = new Point(30,30,0);
        points[4] = new Point(30,10,10);
        points[5] = new Point(50,10,10);
        points[6] = new Point(50,30,10);
        points[7] = new Point(30,30,10);
        Obj obj = new Obj(points);
        Assert.assertTrue(objectsList.checkOverlapObject(obj));

    }

    public static void checkOverLap3(ManagerObject objectsList){
        // vật bị lơ lửng
        Point[] points = new Point[8];
        points[0] = new Point(10,10,10);
        points[1] = new Point(30,10,10);
        points[2] = new Point(30,30,10);
        points[3] = new Point(10,30,10);
        points[4] = new Point(10,10,20);
        points[5] = new Point(30,10,20);
        points[6] = new Point(30,30,20);
        points[7] = new Point(10,30,20);
        Obj obj = new Obj(points);
        Assert.assertTrue(objectsList.checkOverlapObject(obj));

    }
    public static void checkOverLap4(ManagerObject objectsList){
        // overlap obj
        Point[] points = new Point[8];
        points[0] = new Point(20,0,0);
        points[1] = new Point(40,0,0);
        points[2] = new Point(40,20,0);
        points[3] = new Point(20,20,0);
        points[4] = new Point(20,0,10);
        points[5] = new Point(40,0,10);
        points[6] = new Point(40,20,10);
        points[7] = new Point(20,20,10);
        Obj obj = new Obj(points);
        Assert.assertTrue(objectsList.checkOverlapObject(obj));

    }
    public static void checkOverLap5(ManagerObject objectsList){
        // vật chồng lên obj
        Point[] points = new Point[8];
        points[0] = new Point(30,10,10);
        points[1] = new Point(50,10,10);
        points[2] = new Point(50,30,10);
        points[3] = new Point(30,30,10);
        points[4] = new Point(30,10,20);
        points[5] = new Point(50,10,20);
        points[6] = new Point(50,30,20);
        points[7] = new Point(30,30,20);
        Obj obj = new Obj(points);
        Assert.assertFalse(objectsList.checkOverlapObject(obj));

    }

    public static void checkOverLap6(ManagerObject objectsList){
        // vật chồng lên obj3 nhưng không tiếp xúc hoàn toàn
        Point[] points = new Point[8];
        points[0] = new Point(70,40,10);
        points[1] = new Point(80,40,10);
        points[2] = new Point(80,50,10);
        points[3] = new Point(70,50,10);
        points[4] = new Point(70,40,20);
        points[5] = new Point(80,40,20);
        points[6] = new Point(80,50,20);
        points[7] = new Point(70,50,20);
        Obj obj = new Obj(points);
        Assert.assertTrue(objectsList.checkOverlapObject(obj));

    }
    public static void checkOverLap7(ManagerObject objectsList){
        // chồng lên obj6;
        Point[] points6 = new Point[8];
        points6[0] = new Point(40,90,50);
        points6[1] = new Point(50,90,50);
        points6[2] = new Point(50,100,50);
        points6[3] = new Point(40,100,50);
        points6[4] = new Point(40,90,60);
        points6[5] = new Point(50,90,60);
        points6[6] = new Point(50,100,60);
        points6[7] = new Point(40,100,60);
        Obj obj = new Obj(points6);
        Assert.assertFalse(objectsList.checkOverlapObject(obj));

    }
    public static void checkOverLap8(ManagerObject objectsList){
        // overlap với obj6
        Point[] points5 = new Point[8];
        points5[0] = new Point(30,80,40);
        points5[1] = new Point(50,80,40);
        points5[2] = new Point(50,100,40);
        points5[3] = new Point(30,100,40);
        points5[4] = new Point(30,80,50);
        points5[5] = new Point(50,80,50);
        points5[6] = new Point(50,100,50);
        points5[7] = new Point(30,100,50);
        Obj obj5 = new Obj(points5);
        Assert.assertTrue(objectsList.checkOverlapObject(obj5));

    }



}
