package manager;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adrien on 10/10/2016.
 */
public class Label {
    private List<Point> points = new ArrayList<>();

    public Label(List<Point> points) {
        this.points = points;
    }

    public int size() {
        return points.size();
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Point p : points) {
            sb.append((int)p.getX());
            sb.append(',');
            sb.append((int)p.getY());
            sb.append('\n');
        }
        return sb.toString();
    }
}
