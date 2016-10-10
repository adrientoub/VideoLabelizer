package process;

import controller.PreviewController;
import manager.Media;
import view.PreviewView;

import java.awt.image.BufferedImage;
import java.util.concurrent.Callable;

public class GenerateFrame implements Callable<BufferedImage> {
    public int frame;

    public GenerateFrame(int frame) {
        this.frame = frame;
    }

    @Override
    public BufferedImage call() throws Exception {
        Media m = PreviewController.getInstance().getMedia();
        return m.getImage(frame);
    }
}
