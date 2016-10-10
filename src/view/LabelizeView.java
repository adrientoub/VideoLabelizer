package view;

import controller.LabelizeController;
import controller.PreviewController;
import framework.Application;
import framework.View;
import model.LabelizeModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adrien on 10/10/2016.
 */
public class LabelizeView extends View<LabelizeModel, LabelizeController> {
    private JPanel panel;

    /**
     * Initialize a new {@link View} instance for the specified
     * {@link Application}.
     *
     * @param application The {@link Application} that the {@link View} is
     *                    associated with.
     */
    public LabelizeView(Application application) {
        super(application);
        panel = new JPanel();
        JButton button = new JButton("Play");
        button.setActionCommand("Play");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreviewController.getInstance().play();
            }
        });
        panel.add(button);
    }

    @Override
    public JComponent render() {
        return panel;
    }
}
