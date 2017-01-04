package app;
// Swing utilities

import framework.Application;
import view.*;

import javax.swing.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public final class VideoLabelizer extends Application {
    private OptionView optionView;
    private PreviewView previewView;
    private LabelizeView labelizeView;

    /**
     * Start the {@link VideoLabelizer}.
     *
     * @param frame The main frame of the {@link VideoLabelizer}.
     */
    protected void start(final JFrame frame) {
        optionView = new OptionView(this);
        previewView = new PreviewView(this);
        labelizeView = new LabelizeView(this);

        frame.setTitle("Video Labelizer");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        loadLibraries();

        // Set the menu bar of the application frame.
        frame.setJMenuBar(new MenuView(this).render());
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        JSplitPane top = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, optionView.render(), previewView.render());
        jSplitPane.setTopComponent(top);
        jSplitPane.setBottomComponent(labelizeView.render());
        frame.setContentPane(jSplitPane);
    }

    private static ArrayList<File> listEndsWith(String path, String ext) {
        File dir = new File(path);
        File[] files = dir.listFiles((d, name) -> name.endsWith('.' + ext));
        return new ArrayList<>(Arrays.asList(files));
    }

    public LabelizeView getLabelizeView() {
        return labelizeView;
    }

    public PreviewView getPreviewView() {
        return previewView;
    }

    private static void loadLibraries() {
        String lib_ext;
        String separator;
        if (System.getProperty("os.name").contains("Windows")) {
            separator = "\\";
            lib_ext = "dll";
        } else {
            separator = "/";
            lib_ext = "so";
        }

        ArrayList<File> files = new ArrayList<>();
        files.addAll(listEndsWith("lib" + separator + "opencv", lib_ext));
        files.addAll(listEndsWith("lib" + separator + "ffmpeg", lib_ext));
        files.addAll(listEndsWith("lib" + separator + "h264", lib_ext));
        for (File f : files) {
            System.out.println("Loading external library: " + f.getAbsolutePath());
            System.load(f.getAbsolutePath());
        }
    }

    /**
     * Boot up the {@link VideoLabelizer}.
     *
     * @param args Runtime arguments.
     */
    public static void main(final String[] args) {
        new VideoLabelizer();
    }

    public OptionView getOptionView() {
        return optionView;
    }

}
