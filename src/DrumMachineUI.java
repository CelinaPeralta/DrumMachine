import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brian on 5/29/2017.
 */
public class DrumMachineUI extends JFrame {

    public static Player player;
    public static RhythmPanel rhythmPanel;
    public static ControlPanel controlPanel;
    public static JButton start;
    public static Thread looper;

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
        start = new JButton("Start/Stop");
        start.setEnabled(true);
        add(start);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(looper.isAlive())
                    looper.stop();
                else
                    looper.start();

            }
        });
    }

    public static void main(String[] args) throws Exception {

        DrumMachineUI frame = new DrumMachineUI();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        LoopThread loop = new LoopThread();
        looper = new Thread(loop);
        looper.setDaemon(true);

//        updateThread.run();
        if (start.isSelected()) {
            if (looper.isAlive())
                looper.interrupt();
            else
                looper.start();
        }

    }

    public static class LoopThread implements Runnable {
        @Override
        public void run() {
            while (true) {
                rhythmPanel.play();
                System.out.println("loop");
                try {
                    Thread.sleep((60000 / controlPanel.getTempo()) / 8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }


}
