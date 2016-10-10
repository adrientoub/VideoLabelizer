package manager;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Media extends Cloneable {
    int getFrameFromMilliseconds(long time);

    /**
     * Returns the media resolution
     *
     * @return The resolution in pixel
     */
    Dimension getResolution();

    /**
     * Returns the previewImage at the selected frame.
     *
     * @param frameNb The frame number
     * @return A filtered previewImage
     */
    BufferedImage getImage(int frameNb);

    double getFps();

    /**
     * Get the duration of the Media
     *
     * @return the duration in milliseconds
     */
    long getDuration();

    /**
     * Get the name of the Media
     *
     * @return the name of the Media
     */
    String getName();

    Object clone();
}
