package model.space;

public class Line { 
	
	//x = xo + at,y=yo+bt, z= zo +ct
	
    private double xo;
    private double yo;
    private double zo;
    private double a;
    private double b;
    private double c;
    public double getXo() {
        return xo;
    }
    public void setXo(double xo) {
        this.xo = xo;
    }
    public double getYo() {
        return yo;
    }
    public void setYo(double yo) {
        this.yo = yo;
    }
    public double getZo() {
        return zo;
    }
    public void setZo(double zo) {
        this.zo = zo;
    }
    public double getA() {
        return a;
    }
    public void setA(double a) {
        this.a = a;
    }
    public double getB() {
        return b;
    }
    public void setB(double b) {
        this.b = b;
    }
    public double getC() {
        return c;
    }
    public void setC(double c) {
        this.c = c;
    }
    public Line(double xo, double yo, double zo, double a, double b, double c) {
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