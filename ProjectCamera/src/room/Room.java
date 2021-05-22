package room;

import point.Point;

public class Room {
    private Point [] points = new Point[8];

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
}
