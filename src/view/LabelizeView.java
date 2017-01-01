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
    private JLabel currentFrame;
    private JLabel currentCount;

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
        JButton play = new JButton("Play");
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (PreviewController.getInstance().play(true))
                    play.setText(play.getText().equals("Play") ? "Pause" : "Play");
            }
        });
        panel.add(play);

        JButton prevSave = new JButton("Previous and Save");
        prevSave.addActionListener(e -> {
            PreviewController.getInstance().save();
            PreviewController.getInstance().prev();
        });
        panel.add(prevSave);

        JButton prev = new JButton("Previous");
        prev.addActionListener(e -> PreviewController.getInstance().prev());
        panel.add(prev);

        JButton next = new JButton("Next");
        next.addActionListener(e -> PreviewController.getInstance().next());
        panel.add(next);

        JButton nextSave = new JButton("Next and Save");
        nextSave.addActionListener(e -> {
            PreviewController.getInstance().save();
            PreviewController.getInstance().next();
        });
        panel.add(nextSave);

        currentFrame = new JLabel("Frame 0");
        currentCount = new JLabel("Labels: 0");
        panel.add(currentFrame);
        panel.add(currentCount);
    }

    public void setFrame(int frame) {
        currentFrame.setText("Frame " + frame);
    }

    public void setLabelsCount(int count) {
        currentCount.setText("Labels: " + count);
    }

    @Override
    public JComponent render() {
        return panel;
    }
}
