
package controller;

import framework.Application;
import framework.Controller;
import manager.Media;
import model.PreviewModel;
import view.PreviewView;

public final class PreviewController extends Controller<PreviewModel, PreviewView> {
  private static Media media;

  public PreviewController(final Application application) {
    super(application);
  }

  public static void setMedia(Media new_media) {
    media = new_media;
  }

  public static Media getMedia() {
    return media;
  }
}
