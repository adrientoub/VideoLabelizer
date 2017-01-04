package framework;

import controller.MouseLabelController;
import manager.Label;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class ImagePanel extends JPanel {
    private int width;
    private int height;
    private BufferedImage image;
    private JPanel containingPanel;
    private final boolean scaled = false;
    private List<Point> points = new ArrayList<>();

    /**
     * Create the ImagePanel
     *
     * @param width  the width of the previewImage
     * @param height the height of the previewImage
     */
    public ImagePanel(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
    }

    /**
     * Create the ImagePanel
     *
     * @param image: previewImage to display
     */
    public ImagePanel(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        setPreferredSize(new Dimension(width, height));
        addMouseListener(new MouseLabelController(this));
    }

    /**
     * Create the ImagePanel
     *
     * @param file: previewImage to display
     */
    public ImagePanel(File file) {
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        width = image.getWidth();
        height = image.getHeight();
        setPreferredSize(new Dimension(width, height));
    }

    public void setContainingPanel(JPanel containingPanel) {
        this.containingPanel = containingPanel;
    }

    private Dimension getScaledSize() {
        double ws = containingPanel.getWidth();
        double hs = containingPanel.getHeight();
        double rs = ws / hs;
        double ri = width / (double) height;
        if (rs > ri) {
            return new Dimension((int) (width * hs) / height, (int) hs);
        }
        else {
            return new Dimension((int) ws, (int) (height * ws) / width);
        }
    }

    @Override
    public int getWidth() {
        if (scaled) {
            if (containingPanel == null) {
                return width;
            }

            return (int) getScaledSize().getWidth();
        } else
            return width;
    }

    @Override
    public int getHeight() {
        if (scaled) {
            if (containingPanel == null) {
                return height;
            }

            return (int) getScaledSize().getHeight();
        } else {
            return height;
        }
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
        setPreferredSize(new Dimension(width, height));
        points = new ArrayList<>();
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }

    public void addOldLabel(Label label) {
        Graphics2D graphics2D = (Graphics2D) image.getGraphics();
        for (Point point: label.getPoints()) {
            graphics2D.setColor(Color.green);
            graphics2D.fillOval((int) point.getX() - 12, (int) point.getY() - 12, 15, 15);
        }
        graphics2D.dispose();
    }

    public void addPoint(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            Graphics2D graphics2D = (Graphics2D) image.getGraphics();
            graphics2D.setColor(Color.blue);
            graphics2D.fillOval(x - 12, y - 12, 15, 15);
            graphics2D.dispose();

            points.add(new Point(x, y));
            repaint();
        }
    }

    public List<Point> getPoints() {
        return points;
    }
}
