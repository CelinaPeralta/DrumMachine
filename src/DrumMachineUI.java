import javax.swing.*;
import java.awt.*;

/**
 * Created by brian on 5/29/2017.
 */
public class DrumMachineUI extends JFrame {

    public static Player player;
    public static RhythmPanel rhythmPanel;
    public static ControlPanel controlPanel;

    public DrumMachineUI() throws Exception {

        setTitle("Drum Machine");
        setSize(1000, 500);

        player = new Player();

        //HeaderPanel headerPanel = new HeaderPanel();

        //MixerPanel mixerPanel = new MixerPanel();
        rhythmPanel = new RhythmPanel(player);
        controlPanel = new ControlPanel(rhythmPanel);

        setLayout(new BorderLayout());

        //add(headerPanel, BorderLayout.NORTH);
        add(controlPanel, BorderLayout.WEST);
        //add(mixerPanel, BorderLayout.CENTER);
        add(rhythmPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws Exception {

        DrumMachineUI frame = new DrumMachineUI();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        LoopThread loop = new LoopThread();
        loop.setDaemon(true);

        UpdateThread updateThread = new UpdateThread();
        updateThread.setDaemon(true);

//        updateThread.run();
        loop.run();

    }

    public static class LoopThread extends Thread {
        @Override
        public void run() {
            while (true) {
                rhythmPanel.play();
                System.out.println("loop");
                try {
                    Thread.sleep((60000 / controlPanel.getTempo()) / 16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static class UpdateThread extends Thread {
        @Override
        public void run() {
            while (true) {
                rhythmPanel.setInstrument(controlPanel.getCurrentInstrument());
                System.out.println("update");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
