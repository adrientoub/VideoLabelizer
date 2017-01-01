package manager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien on 10/10/2016.
 */
public class Label {
    private int frame;
    private List<Point> points = new ArrayList<>();

    public Label(int frame, List<Point> points) {
        this.points = points;
        this.frame = frame;
    }

    public int nbPoints() { return this.points.size(); }

    public int getFrame() {
        return frame;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frame ").append(frame).append("\n");
        for (Point p : points) {
            sb.append((int)p.getX());
            sb.append(',');
            sb.append((int)p.getY());
            sb.append('\n');
        }
        return sb.toString();
    }
}
