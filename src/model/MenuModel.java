package model;

import framework.Application;
import framework.Model;
import manager.Image;
import manager.Media;
import manager.Video;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public final class MenuModel extends Model {
    private final String extension = "png";

    public MenuModel(final Application application) {
        super(application);
    }

    public static String getExtension(File file) {
        String[] splited = file.getName().split("\\.");
        return splited[splited.length - 1].toLowerCase();
    }

    public void newFile() throws IllegalArgumentException, IOException {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setMultiSelectionEnabled(true);
        jFileChooser.showOpenDialog(null);
        File[] files = jFileChooser.getSelectedFiles();
        for (File selected: files) {
            String extension = getExtension(selected);
            Media m;
            if (extension.equals("jpg") || extension.equals("png") || extension.equals("bmp") || extension.equals("jpeg") || extension.equals("gif"))
                m = new Image(selected);
            else
                m = new Video(selected);
            this.emit("media:new", m);
        }
    }

    public void exit(int i) {
        System.exit(i);
    }
}
