package manager;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image implements Media {
    private BufferedImage image;
    private long duration = 5000;
    private String name;

    public Image(File file) throws IOException {
        image = ImageIO.read(file);
        name = file.getName();
    }

    public static BufferedImage deepCopyBufferedImage(BufferedImage image) {
        BufferedImage copy = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        int[] buffer = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), buffer, 0, image.getWidth());
        copy.setRGB(0, 0, image.getWidth(), image.getHeight(), buffer, 0, image.getWidth());
        return copy;
    }

    @Override
    public int getFrameFromMilliseconds(long time) {
        return 0;
    }

    @Override
    public Dimension getResolution() {
        return new Dimension(image.getWidth(), image.getHeight());
    }

    @Override
    public BufferedImage getImage(int frameNb) {
        return image;
    }

    @Override
    public long getDuration() {
        return duration;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getFps() {
        return 30;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
