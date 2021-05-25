package model.space;

import java.util.ArrayList;

public class Calculation {


    // tích có hướng của 2 vector
    //x = b1c2 - b2c1, y = c1a2 - c2a1, z = a1b2 - a2b1
    public Vector3D DirectedVec(Vector3D v1, Vector3D v2){
        float x = v1.getVy()*v2.getVz() - v2.getVy()*v1.getVz();
        float y = v1.getVz()*v2.getVx() - v2.getVz()*v1.getVx();
        float z = v1.getVx()*v2.getVy() - v2.getVx()*v1.getVy();
        Vector3D v = new Vector3D(x,y,z);
        return v;
    }

    // tích vô hướng của 2 vector
    //v1*v2 = x1x2 + y1y2 + z1z2
    public float ScalarVec(Vector3D v1, Vector3D v2){
        return v1.getVx()*v2.getVx()+v1.getVy()*v2.getVy()+v1.getVz()*v2.getVz();
    }

    // khoảng cách giữa 2 điểm
    // distance between 2 point
    public float PointDistance(Point A, Point B){
        Vector3D v = new Vector3D(A,B);
        return v.Distance();
    }

    // Area of Triangle
    public float Area_Triangle(ArrayList<Point> p){
        Point A = p.get(0);
        Point B = p.get(1);
        Point C = p.get(2);
        float AB = PointDistance(A,B);
        float BC = PointDistance(B,C);
        float CA = PointDistance(C,A);
        float peri = (AB+BC+CA)/2; //half of perimeter
        return (float) Math.sqrt(peri*(peri - AB)*(peri - BC)*(peri - CA));//herong
    }

    // Area of Quadrilateral (tứ giác)
    public float Area_Quadrilateral(ArrayList<Point> p){
        Point A = p.get(0);
        Point B = p.get(1);
        Point C = p.get(2);
        Point D = p.get(3);
        ArrayList<Point> S1 = new ArrayList<Point>();
        S1.add(A); S1.add(B); S1.add(C);
        ArrayList<Point> S2 = new ArrayList<Point>();
        S2.add(A); S2.add(B); S2.add(D);
        ArrayList<Point> S3 = new ArrayList<Point>();
        S3.add(A); S3.add(C); S3.add(D);
        ArrayList<Point> S4 = new ArrayList<Point>();
        S4.add(B); S4.add(D); S4.add(C);
        return (Area_Triangle(S1) + Area_Triangle(S2) + Area_Triangle(S3) + Area_Triangle(S4))/2;
    }
    // Volume of pyramid
    public float Volume_Pyramid(ArrayList<Point> p,Point S){
        Plane P = new Plane(p.get(0),p.get(1),p.get(2));

        if(IsInPlane(S,P)){
            return (float) 0.0;
        }else{

            if(p.size() == 3){
                //chiều cao nhân cạnh đáy tam giác
                //System.out.println(GetTriArea(p));
                return PointToPlane(S,P)*Area_Triangle(p)/3;
            }else{
                // chiều cao nhân cạnh đáy hình chữ nhật
                //System.out.println(GetQuaAre(p));
                return PointToPlane(S,P)*Area_Triangle(p)/3;
            }
        }
    }

    //Get distance from point to Plane
    public float PointToPlane(Point A, Plane P){
        float dis = (float) Math.sqrt(P.getA()*P.getA()+P.getB()*P.getB()+P.getC()*P.getC());
        return Math.abs(P.getA()*A.getX()+P.getB()*A.getY()+P.getC()*A.getZ()+P.getD())/dis;
    }

    //check Point in line
    public boolean IsInLine(Point A, Line d){
        float t = (A.getX() - d.getXo())/d.getA();
        float y = d.getB()*t + d.getYo();
        float z = d.getC()*t + d.getZo();
        if(y == A.getY() && z == A.getZ()){ //if has t x = xo+at,y=yo+bt,z=zo+ct
            return true;
        }else{
            return false;
        }
    }
    //check Point in plane
    public boolean IsInPlane(Point A,Plane P){
        if(PointToPlane(A,P) == 0.0){
            return true;
        }else{
            return false;
        }
    }

    //relative between line and plane
    //A(xo + at) + B(yo + bt) + C(zo+ct) + D = 0
    //(Aa + Bb + Cc)t + (Axo + Byo + Czo + D) = 0
    public int RelLineToPlane(Line d, Plane P){
        float a = P.getA()*d.getA()+P.getB()*d.getB()+P.getC()*d.getC();
        float b = P.getA()*d.getXo()+P.getB()*d.getYo()+P.getC()+d.getZo() + P.getD();
        if(a == 0){
            if(b == 0){
                return -1;//d in P
            }else{
                return 1;//d // P
            }
        }else{
            return 0; // d intersect P
        }
    }
    // tìm giao điểm
    //get intersect point
    public Point GetIntPoint(Line d, Plane P){
        float a = P.getA()*d.getA()+P.getB()*d.getB()+P.getC()*d.getC();
        float b = P.getA()*d.getXo()+P.getB()*d.getYo()+P.getC()*d.getZo() + P.getD();
        if(RelLineToPlane(d,P) == 0){
            float t = -b/a;
            Point A = new Point(d.getXo()+d.getA()*t, d.getYo()+d.getB()*t,d.getZo()+d.getC()*t);
            return A;
        }else{
            return null;
        }
    }
}