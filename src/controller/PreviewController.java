package controller;

import app.VideoLabelizer;
import framework.Application;
import framework.Controller;
import manager.Media;
import model.PreviewModel;
import view.OptionView;
import view.PreviewView;

import javax.swing.*;

public final class PreviewController extends Controller<PreviewModel, PreviewView> {
    private Media media;
    private int frame = 0;
    private static PreviewController instance;
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

    public void setFrame(int frame) {
        this.frame = frame;
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
        OptionView optionView = ((VideoLabelizer)application()).getOptionView();
        frame += optionView.getFps() * optionView.getSeconds();
        emit("marker:changed", frame);
        if (t == null) {
            t = new Timer((int) (optionView.getSeconds() * 1000.0), e -> play(false));
            t.setRepeats(true);
            t.start();
        }
        return true;
    }

    public int getFrame() {
        return frame;
    }

    public void next() {
        if (media == null)
            return;

        OptionView optionView = ((VideoLabelizer)application()).getOptionView();
        frame += optionView.getFps() * optionView.getSeconds();
        emit("marker:changed", frame);
    }

    public void prev() {
        if (media == null)
            return;

        OptionView optionView = ((VideoLabelizer)application()).getOptionView();
        frame -= optionView.getFps() * optionView.getSeconds();
        if (frame < 0)
            frame = 0;
        emit("marker:changed", frame);
    }

    public void save() {
        if (media == null)
            return;
        emit("save:points");
    }

    public void saveToDisk() {
        emit("save:disk");
    }
}
