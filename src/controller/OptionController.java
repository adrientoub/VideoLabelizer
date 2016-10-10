package controller;

import framework.Application;
import framework.Controller;
import model.MenuModel;
import view.MenuView;

public final class OptionController extends Controller<MenuModel, MenuView> {

  public OptionController(final Application application) {
    super(application);
  }
}
