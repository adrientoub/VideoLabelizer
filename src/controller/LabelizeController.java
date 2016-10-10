package controller;

import framework.Application;
import framework.Controller;
import model.LabelizeModel;
import view.LabelizeView;

/**
 * Created by Adrien on 10/10/2016.
 */
public class LabelizeController extends Controller<LabelizeModel, LabelizeView> {
    /**
     * Initialize a new {@link Controller} instance for the specified
     * {@link Application}.
     *
     * @param application The {@link Application} that the {@link Controller} is
     *                    associated with.
     */
    public LabelizeController(Application application) {
        super(application);
    }
}
