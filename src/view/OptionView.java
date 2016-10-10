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
    private JSpinner secondsSpinner;
    private double seconds = 2.0;

    public OptionView(final Application application) {
        super(application);

        fpsSpinner = new JSpinner(new SpinnerNumberModel(fps, 0.0, 60.0, 1.0));
        fpsSpinner.addChangeListener(changeEvent ->
                fps = ((SpinnerNumberModel)((JSpinner)changeEvent.getSource()).getModel()).getNumber().doubleValue());

        secondsSpinner = new JSpinner(new SpinnerNumberModel(seconds, 0.0, 10.0, 0.1));
        secondsSpinner.addChangeListener(changeEvent ->
                seconds = ((SpinnerNumberModel)((JSpinner)changeEvent.getSource()).getModel()).getNumber().doubleValue());

        this.model(new OptionModel(application));
        this.controller(new OptionController(application));
    }

    public JPanel render() {
        JPanel viewPanel = new JPanel();
        int optionCount = 2;
        viewPanel.setLayout(new GridLayout(optionCount, 2, 2, 5));
        viewPanel.add(new JLabel("FPS: "));
        viewPanel.add(fpsSpinner);
        viewPanel.add(new JLabel("Seconds: "));
        viewPanel.add(secondsSpinner);

        return viewPanel;
    }

    public double getFps() {
        return fps;
    }

    public double getSeconds() {
        return seconds;
    }
}
