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

        HeaderPanel headerPanel = new HeaderPanel();
        MixerPanel mixerPanel = new MixerPanel();
        rhythmPanel = new RhythmPanel(player);
        controlPanel = new ControlPanel(rhythmPanel);

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

        LoopThread loop = new LoopThread();
        loop.setDaemon(true);

        UpdateThread updateThread = new UpdateThread();
        updateThread.setDaemon(true);

        //updateThread.run();
        loop.start();

    }

    public static class LoopThread extends Thread {
        @Override
        public synchronized void run() {
            while (true) {
                player.setTimeSignature4(controlPanel.getTimeSignature());

                if(controlPanel.isPlaying()) {
                    System.out.println("Test1");
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

    public static class UpdateThread extends Thread {
        @Override
        public void run() {
            while (true) {
                //Make boolean methods for isChanged for the panels
                rhythmPanel.setInstrument(controlPanel.getCurrentInstrument(), controlPanel.getBeatArray()[controlPanel.getCurrentInstrument()]);
                player.setTimeSignature4(controlPanel.getTimeSignature());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
