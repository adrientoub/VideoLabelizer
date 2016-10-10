package view;

import controller.OptionController;
import framework.Application;
import framework.View;
import model.OptionModel;

import javax.swing.*;
import java.awt.*;

public final class OptionView extends View<OptionModel, OptionController> {
    private JSpinner fpsSpinner;
    private double fps = 30.0;
    private JSpinner framesSpinner;
    private int frames = 100;

    public OptionView(final Application application) {
        super(application);

        fpsSpinner = new JSpinner(new SpinnerNumberModel(fps, 0.0, 60.0, 1.0));
        fpsSpinner.addChangeListener(changeEvent ->
                fps = ((SpinnerNumberModel)((JSpinner)changeEvent.getSource()).getModel()).getNumber().doubleValue());

        framesSpinner = new JSpinner(new SpinnerNumberModel(frames, 0, 60000, 5));
        framesSpinner.addChangeListener(changeEvent ->
                frames = ((SpinnerNumberModel)((JSpinner)changeEvent.getSource()).getModel()).getNumber().intValue());

        this.model(new OptionModel(application));
        this.controller(new OptionController(application));
    }

    public JPanel render() {
        JPanel viewPanel = new JPanel();
        int optionCount = 2;
        viewPanel.setLayout(new GridLayout(optionCount, 2, 2, 5));
        viewPanel.add(new JLabel("FPS: "));
        viewPanel.add(fpsSpinner);
        viewPanel.add(new JLabel("Frames to render: "));
        viewPanel.add(framesSpinner);

        return viewPanel;
    }

    public double getFps() {
        return fps;
    }

    public int getFrames() {
        return frames;
    }
}
