package manager;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import static org.opencv.videoio.Videoio.*;

public class Video implements Media {
    private double fps;
    private int nbFrames;
    private long duration;
    private final VideoCapture videoCapture;
    private File file;
    private Dimension resolution;

    private static BufferedImage toBufferedImageOfType(BufferedImage original, int type) {
        if (original.getType() == type) {
            return original;
        }

        BufferedImage image = new BufferedImage(original.getWidth(), original.getHeight(), type);

        Graphics2D g = image.createGraphics();
        try {
            g.setComposite(AlphaComposite.Src);
            g.drawImage(original, 0, 0, null);
        }
        finally {
            g.dispose();
        }
        return image;
    }

    public static Mat bufferedImageToMat(BufferedImage bi) {
        BufferedImage image = toBufferedImageOfType(bi, BufferedImage.TYPE_3BYTE_BGR);
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }

    public static BufferedImage Mat2bufferedImage(Mat image) {
        int bufferSize = image.channels() * image.cols() * image.rows();
        byte[] bytes = new byte[bufferSize];
        image.get(0, 0, bytes);
        int type = 0;
        if (image.channels() == 3)
            type = BufferedImage.TYPE_3BYTE_BGR;
        else if (image.channels() == 4)
            type = BufferedImage.TYPE_4BYTE_ABGR;
        else if (image.channels() == 1)
            type = BufferedImage.TYPE_BYTE_GRAY;
        else
            System.err.println("UNKNOWN IMAGE TYPE");

        BufferedImage img = new BufferedImage(image.width(), image.height(), type);
        final byte[] targetPixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
        return img;
    }

    public Video(File f) {
        file = f;
        videoCapture = new VideoCapture(f.getAbsolutePath());
        double width = videoCapture.get(CAP_PROP_FRAME_WIDTH);
        double height = videoCapture.get(CAP_PROP_FRAME_HEIGHT);
        fps = videoCapture.get(CAP_PROP_FPS);
        double frameCount = videoCapture.get(CAP_PROP_FRAME_COUNT);
        nbFrames = (int) frameCount;
        resolution = new Dimension((int) width, (int) height);

        System.out.println("Width: " + width);
        System.out.println("Height: " + height);
        System.out.println("FPS: " + fps);
        System.out.println("Frame count: " + frameCount);
        if (fps > 0)
            duration = (long) (frameCount * 1000.0 / fps);
        else
            duration = nbFrames * 1000;
    }

    @Override
    public int getFrameFromMilliseconds(long time) {
        return (int) ((time / 1000.0) * fps);
    }

    @Override
    public Dimension getResolution() {
        return resolution;
    }

    public BufferedImage getImage(int frameNb) {
        ImageIO.setUseCache(false);
        Mat frame = new Mat();
        boolean success;
        synchronized (videoCapture) {
            videoCapture.set(CAP_PROP_POS_FRAMES, frameNb);
            success = videoCapture.read(frame);
        }

        if (success) {
            return Mat2bufferedImage(frame);
        } else {
            System.err.println("Impossible to read frame " + frameNb + " of video " + getName());
        }
        return null;
    }

    @Override
    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }

    public int getNbFrames() {
        return nbFrames;
    }

    public void setNbFrames(int nbFrames) {
        this.nbFrames = nbFrames;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getName() {
        return file.getName();
    }

    @Override
    public Object clone() {
        Media o = new Video(this.file);
        System.out.println("Cloned!");
        return o;
    }
}
