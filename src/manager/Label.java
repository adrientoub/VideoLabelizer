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

    public Label(int frame, List points) {
        this.points = points;
        this.frame = frame;
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Frame " + frame + "\n");
        for (Point p : points) {
            sb.append((int)p.getX());
            sb.append(',');
            sb.append((int)p.getY());
            sb.append('\n');
        }
        return sb.toString();
    }
}
