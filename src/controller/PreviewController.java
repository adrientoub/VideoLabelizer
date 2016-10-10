
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
  private boolean playing = false;
  private final double seconds = 0.2;

  public PreviewController(final Application application) {
    super(application);
    instance = this;
  }

  public static PreviewController getInstance() {
    return instance;
  }

  public void setMedia(Media new_media) {
    media = new_media;
    playing = false;
    frame = 0;
  }

  public Media getMedia() {
    return media;
  }

  public void play() {
    if (media == null)
      return;
    frame += diff * seconds;
    emit("marker:changed", frame);
    if (!playing) {
      Timer t = new Timer((int) (seconds * 1000), e -> play());
      t.setRepeats(true);
      t.start();
      playing = true;
    }
  }
}
