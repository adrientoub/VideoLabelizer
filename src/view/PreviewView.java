
package view;

// General utilities

import app.VideoLabelizer;
import controller.PreviewController;
import framework.Application;
import framework.ImagePanel;
import framework.View;
import manager.Label;
import manager.Media;
import model.PreviewModel;
import process.GenerateFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Point;
import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.*;

/**
 * The {@link PreviewView} class takes care of rendering the view for creating,
 * displaying, and completing todo items.
 */
public final class PreviewView extends View<PreviewModel, PreviewController> {
    private ImagePanel imagePanel;
    private Map<Integer, Label> labels = new TreeMap<>();

    public PreviewView(final Application application) {
        super(application);

        this.model(new PreviewModel(application));
        this.controller(new PreviewController(application));

        // Create the imagePanel with a base image
        imagePanel = new ImagePanel(this.model().previewImage());

        this.on("media:new", this::handle);
        this.on("marker:changed", this::handleMarker);
        this.on("save:points", this::savePoints);
        this.on("save:disk", this::saveToDisk);
    }

    private void saveToDisk(PreviewView previewView) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(controller().getMedia().getName() + "-" + Date.from(Instant.now()).getTime() + ".txt", false));
            for (Map.Entry<Integer, Label> label: labels.entrySet()) {
                bw.write("Frame " + label.getKey() + "\n");
                bw.write(label.getValue().toString());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePoints(PreviewView previewView) {
        List<Point> points = imagePanel.getPoints();
        int currentFrame = controller().getFrame();
        labels.put(currentFrame, new Label(points));
    }

    private void handle(Media m) {
        controller().setMedia(m);
        imagePanel.setImage(m.getImage(0));
        imagePanel.repaint();
    }

    public void handleMarker(int frame) {
        try {
            BufferedImage bi = new GenerateFrame(frame).call();
            ((VideoLabelizer)application()).getLabelizeView().setFrame(frame);
            ((VideoLabelizer)application()).getLabelizeView().setLabelsCount(labels.values().stream().mapToInt(Label::size).sum());
            if (bi != null)
                imagePanel.setImage(bi);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Render the {@link PreviewView}.
     */
    public JPanel render() {
        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        imagePanel.setContainingPanel(viewPanel);
        viewPanel.add(imagePanel);

        return viewPanel;
    }
}
