package model.space;

public class Plane { //Ax+By+Cz+D = 0
    // Cal c = new Cal();
    private double A;
    private double B;
    private double C;
    private double D;
    public double getA() {
        return A;
    }
    public void setA(double a) {
        A = a;
    }
    public double getB() {
        return B;
    }
    public void setB(double b) {
        B = b;
    }
    public double getC() {
        return C;
    }
    public void setC(double c) {
        C = c;
    }
    public double getD() {
        return D;
    }
    public void setD(double d) {
        D = d;
    }
    public Plane(double a, double b, double c, double d) {
        A = a;
        B = b;
        C = c;
        D = d;
    }
    
    // create plane from a point and a vector
    public Plane(Point A, Vector3D v){
        this.A = v.getVx();
        this.B = v.getVy();
        this.C = v.getVz();
        this.D = -A.getX()*v.getVx() - A.getY()*v.getVy() - A.getZ()*v.getVz(); 
    }
    
    // khởi tạo mặt phẳng từ 3 điểm A B C
    public Plane(Point A, Point B, Point C){
        Vector3D AB = new Vector3D(A,B);
        Vector3D AC = new Vector3D(A,C);
        // Vector3D v = c.DirectedVec(AB,AC);
        
        double x = AB.getVy()*AC.getVz() - AC.getVy()*AB.getVz();
        double y = AB.getVz()*AC.getVx() - AC.getVz()*AB.getVx();
        double z = AB.getVx()*AC.getVy() - AC.getVx()*AB.getVy();
        Vector3D v = new Vector3D(x,y,z);
        Plane P = new Plane(A,v);
        this.A = P.getA();
        this.B = P.getB();
        this.C = P.getC();
        this.D = P.getD();
        //System.out.println("is plane " + this.A+" " + this.B+" " + this.C + " " + this.D + " ");
    }
}