package model.space;

public class Vector2D {
    private double x,y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    public Vector2D(double x,double y){
        this.x = x;
        this.y = y;
    }
    public boolean checkPerpendicularVectors(Vector2D vector){ // check 2 vector vuông góc
        if(x*vector.getX() + y*vector.getY() == 0)
            return true;
        else
            return false;
    }
    public boolean checkEqualVectors(Vector2D vector){ // check 2 vector bằng nhau
        if(x == vector.getX() && y == vector.getY())
            return true;
        else
            return false;
    }
    public boolean checkInSameDirectionVectors(Vector2D vector){ // check 2 vector cùng hướng
        if((x/vector.getX() == y/ vector.getY()) && x/vector.getX() >=0)
            return true;
        else
            return false;
    }
    public double dotVectors(Vector2D vector){
        return x*vector.getX() + y*vector.getY();
    }

}
