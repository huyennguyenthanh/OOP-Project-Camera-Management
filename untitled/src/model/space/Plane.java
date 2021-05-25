package model.space;

public class Plane { //Ax+By+Cz+D = 0
    // Cal c = new Cal();
    private float A;
    private float B;
    private float C;
    private float D;
    public float getA() {
        return A;
    }
    public void setA(float a) {
        A = a;
    }
    public float getB() {
        return B;
    }
    public void setB(float b) {
        B = b;
    }
    public float getC() {
        return C;
    }
    public void setC(float c) {
        C = c;
    }
    public float getD() {
        return D;
    }
    public void setD(float d) {
        D = d;
    }
    public Plane(float a, float b, float c, float d) {
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

        float x = AB.getVy()*AC.getVz() - AC.getVy()*AB.getVz();
        float y = AB.getVz()*AC.getVx() - AC.getVz()*AB.getVx();
        float z = AB.getVx()*AC.getVy() - AC.getVx()*AB.getVy();
        Vector3D v = new Vector3D(x,y,z);
        Plane P = new Plane(A,v);
        this.A = P.getA();
        this.B = P.getB();
        this.C = P.getC();
        this.D = P.getD();
        //System.out.println("is plane " + this.A+" " + this.B+" " + this.C + " " + this.D + " ");
    }
}