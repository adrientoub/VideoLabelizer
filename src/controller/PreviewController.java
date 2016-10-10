
package controller;

import framework.Application;
import framework.Controller;
import manager.Media;
import model.PreviewModel;
import view.PreviewView;

import javax.swing.*;

public final class PreviewController extends Controller<PreviewModel, PreviewView> {
  private Media media;
  private int frame = 0;
  private final int diff = 30;
  private static PreviewController instance;
  private final double seconds = 0.2;
  private Timer t;

  public PreviewController(final Application application) {
    super(application);
    instance = this;
  }

  public static PreviewController getInstance() {
    return instance;
  }

  public void setMedia(Media new_media) {
    media = new_media;
    frame = 0;
  }

  public Media getMedia() {
    return media;
  }

  public boolean play(boolean pressed) {
    if (media == null)
      return false;
    if (pressed) {
      if (t != null) {
        t.stop();
        t = null;
        System.out.println("Paused the video");
        return true;
      } else {
        System.out.println("Played the video");
      }
    }
    frame += diff * seconds;
    emit("marker:changed", frame);
    if (t == null) {
      t = new Timer((int) (seconds * 1000), e -> play(false));
      t.setRepeats(true);
      t.start();
    }
    return true;
  }
}
