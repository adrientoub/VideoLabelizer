package model;

import framework.Application;
import framework.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public final class PreviewModel extends Model {
    private BufferedImage previewImage;

    public PreviewModel(final Application application) {
        super(application);

        try {
            previewImage = ImageIO.read(new File("assets/preview.png"));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public BufferedImage previewImage() {
        return previewImage;
    }
}
