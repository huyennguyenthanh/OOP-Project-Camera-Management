
package obj;

import point.Point;


public class Obj {


    // private ArrayList<Obj> mnOb = new ArrayList();
    private Point [] points = new Point[8];

    // public ArrayList<Obj> getManageObj() {
    //     return manageObj;
    // }

    // public void setManageObj(ArrayList<Obj> manageObj) {
    //     this.manageObj = manageObj;
    // }

    

    // public void addObj(Obj obj) {

    // }

    

    public Obj() {
        
    }

    public Obj(Point [] points) {
        this.points = points;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }

}
