
package view;

// General utilities

import controller.PreviewController;
import framework.Application;
import framework.ImagePanel;
import framework.View;
import manager.Media;
import model.PreviewModel;
import process.GenerateFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The {@link PreviewView} class takes care of rendering the view for creating,
 * displaying, and completing todo items.
 */
public final class PreviewView extends View<PreviewModel, PreviewController> {
    ImagePanel imagePanel;

    public PreviewView(final Application application) {
        super(application);

        this.model(new PreviewModel(application));
        this.controller(new PreviewController(application));

        imagePanel = new ImagePanel(this.model().image());

    this.on("media:new", this::handle);
    this.on("marker:changed", this::handleMarker);
  }

    private void handle(Media m) {
        controller().setMedia(m);
        imagePanel.setImage(m.getImage(0));
        imagePanel.repaint();
    }

    public void handleMarker(int frame) {
        try {
            BufferedImage bi = new GenerateFrame(frame).call();
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
