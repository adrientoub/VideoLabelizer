package controller;

import framework.ImagePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Adrien on 10/10/2016.
 */
public class MouseLabelController extends MouseAdapter {
    private ImagePanel imagePanel;

    public MouseLabelController(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mouseClicked(e);
        Graphics2D graphics2D = (Graphics2D) imagePanel.getImage().getGraphics();
        graphics2D.setColor(Color.blue);
        graphics2D.fillOval(e.getX() - 12, e.getY() - 12, 15, 15);
        graphics2D.dispose();
        imagePanel.getPoints().add(new Point(e.getX(), e.getY()));
        imagePanel.repaint();
    }
}
