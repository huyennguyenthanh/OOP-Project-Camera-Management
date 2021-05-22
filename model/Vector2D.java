package model;

public class Vector2D {
    private float x,y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    public Vector2D(float x,float y){
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
    public float dotVectors(Vector2D vector){
        return x*vector.getX() + y*vector.getY();
    }

}
