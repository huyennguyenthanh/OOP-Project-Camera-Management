package model.space;

public class Vector3D{
    private float vx;
    private float vy;
    private float vz; // vector have 3 para
    public float getVx() {
        return vx;
    }
    public void setVx(float vx) {
        this.vx = vx;
    }
    public float getVy() {
        return vy;
    }
    public void setVy(float vy) {
        this.vy = vy;
    }
    public float getVz() {
        return vz;
    }
    public void setVz(float vz) {
        this.vz = vz;
    }
    public Vector3D(float vx, float vy, float vz) { //vector A (x,y,z)
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }
    public Vector3D(Point A, Point B){ //vA = AB = (xB-xA;yB-yA;zB-zA)
        this.vx = B.getX() - A.getX();
        this.vy = B.getY() - A.getY();
        this.vz = B.getZ() - A.getZ();
    }
    public float Distance(){

        return (float) Math.sqrt(vx*vx + vy*vy + vz*vz);
    }
}