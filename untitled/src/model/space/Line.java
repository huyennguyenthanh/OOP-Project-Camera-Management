package model.space;

public class Line {

    //x = xo + at,y=yo+bt, z= zo +ct

    private float xo;
    private float yo;
    private float zo;
    private float a;
    private float b;
    private float c;
    public float getXo() {
        return xo;
    }
    public void setXo(float xo) {
        this.xo = xo;
    }
    public float getYo() {
        return yo;
    }
    public void setYo(float yo) {
        this.yo = yo;
    }
    public float getZo() {
        return zo;
    }
    public void setZo(float zo) {
        this.zo = zo;
    }
    public float getA() {
        return a;
    }
    public void setA(float a) {
        this.a = a;
    }
    public float getB() {
        return b;
    }
    public void setB(float b) {
        this.b = b;
    }
    public float getC() {
        return c;
    }
    public void setC(float c) {
        this.c = c;
    }
    public Line(float xo, float yo, float zo, float a, float b, float c) {
        this.xo = xo;
        this.yo = yo;
        this.zo = zo;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public Line(Point A, Point B){
        this.xo = A.getX();
        this.yo = A.getY();
        this.zo = A.getZ();
        this.a = B.getX() - A.getX();
        this.b = B.getY() - A.getY();
        this.c = B.getZ() - A.getZ();
    }
    public Line(Point A, Vector3D v){
        this.xo = A.getX();
        this.yo = A.getY();
        this.zo = A.getZ();
        this.a = v.getVx();
        this.b = v.getVy();
        this.c = v.getVz();
    }
    public Vector3D getVector(){
        Vector3D v = new Vector3D(a,b,c);
        return v;
    }
}