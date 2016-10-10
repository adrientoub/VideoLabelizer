package controller;

import framework.Application;
import framework.Controller;
import model.MenuModel;
import view.MenuView;

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
        }
    }
}
