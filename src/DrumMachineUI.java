import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.lang.management.ThreadMXBean;

/**
 * Created by brian on 5/29/2017.
 */
public class DrumMachineUI extends JFrame {

    public static Player player;

    public DrumMachineUI() throws Exception {

        setTitle("Drum Machine");
        setSize(1000, 500);

        player = new Player();

        //HeaderPanel headerPanel = new HeaderPanel();
        //ControlPanel controlPanel = new ControlPanel();
        //MixerPanel mixerPanel = new MixerPanel();
        RhythmPanel rhythmPanel = new RhythmPanel(player);

        setLayout(new BorderLayout());

        //add(headerPanel, BorderLayout.NORTH);
        //add(controlPanel, BorderLayout.WEST);
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
        loop.run();
    }

    public static class LoopThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("Looping");
                player.play();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
