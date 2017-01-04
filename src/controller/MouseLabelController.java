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
        imagePanel.addPoint(e.getX(), e.getY());
    }
}
