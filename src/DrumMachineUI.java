import javax.swing.*;
import java.awt.*;

import static javafx.scene.input.KeyCode.F;

/**
 * Created by brian on 5/29/2017.
 */
public class DrumMachineUI extends JFrame {

    public static Player player;
    public static HeaderPanel headerPanel;
    public static ControlPanel controlPanel;
    public static MixerPanel mixerPanel;
    public static RhythmPanel rhythmPanel;


    public DrumMachineUI() throws Exception {

        setTitle("Drum Machine");
        setSize(1200, 500);

        player = new Player();

        headerPanel = new HeaderPanel();
        mixerPanel = new MixerPanel();
        rhythmPanel = new RhythmPanel();
        controlPanel = new ControlPanel();

        headerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mixerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rhythmPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.WEST);
        add(mixerPanel, BorderLayout.CENTER);
        add(rhythmPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws Exception {

        DrumMachineUI frame = new DrumMachineUI();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(true);

        LoopThread loopThread = new LoopThread();
        loopThread.setDaemon(true);
        loopThread.start();

    }

    public static class LoopThread extends Thread {
        @Override
        public synchronized void run() {
            while (true) {
                if(controlPanel.isPlaying()) {
                    rhythmPanel.play();
                    try {
                        Thread.sleep((60000 / controlPanel.getTempo()) / 4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
