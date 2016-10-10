package controller;

import framework.Application;
import framework.Controller;
import model.MenuModel;
import view.MenuView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public final class MenuController extends Controller<MenuModel, MenuView> {

    public MenuController(final Application application) {
        super(application);
    }

    public void handle(String name) {
        switch (name) {
            case "Exit":
                this.model().exit(0);
                break;
            case "New":
                try {
                    this.model().newFile();
                } catch (IllegalArgumentException | IOException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "Save":
                PreviewController.getInstance().saveToDisk();
                break;
            case "Goto":
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.add(new JLabel("Frame: "));
                int frame = PreviewController.getInstance().getFrame();
                JSpinner frameSpinner = new JSpinner(new SpinnerNumberModel(frame, 0, 100000, 10));
                panel.add(frameSpinner);

                int result = JOptionPane.showConfirmDialog(null, panel, "Goto", JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    frame = (int) frameSpinner.getValue();
                    PreviewController.getInstance().setFrame(frame);
                    emit("marker:changed", frame);
                }
                break;
        }
    }
}
