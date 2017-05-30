import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;

/**
 * Created by brian on 5/29/2017.
 */
public class DrumMachineUI extends JFrame {

    public DrumMachineUI(){

        setTitle("Drum Machine");
        setSize(1000,500);

        Player player = new Player();

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

    public static void main(String[] args) {
        DrumMachineUI frame = new DrumMachineUI();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
