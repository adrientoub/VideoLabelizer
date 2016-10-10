package controller;

import framework.ImagePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Adrien on 10/10/2016.
 */
public class PointController extends MouseAdapter {
    private ImagePanel imagePanel;

    public PointController(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        Graphics2D graphics2D = (Graphics2D) imagePanel.getImage().getGraphics();
        graphics2D.setColor(Color.blue);
        graphics2D.fillOval(e.getX() - 12, e.getY() - 12, 24, 24);
        graphics2D.dispose();
        imagePanel.repaint();
    }
}
