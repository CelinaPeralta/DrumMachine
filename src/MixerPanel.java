import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by celinaperalta on 5/17/17.
 */
public class MixerPanel extends JPanel {

    private Player player;

    public MixerPanel(Player player) {

        this.player = player;

        setLayout(new GridLayout(2, 3));

        //Instrument Labels
        add(new JLabel("Hihat"));
        add(new JLabel("Bass Drum"));
        add(new JLabel("Snare"));

        //Gain Sliders
        JSlider hhGainSlider = new JSlider(1, -40, 6, 0);
        hhGainSlider.setPaintTicks(true);
        hhGainSlider.addChangeListener(new GainChangeListener(0));
        add(hhGainSlider);

        JSlider bdGainSlider = new JSlider(1, -40, 6, 0);
        bdGainSlider.setPaintTicks(true);
        bdGainSlider.addChangeListener(new GainChangeListener(1));
        add(bdGainSlider);

        JSlider sdGainSlider = new JSlider(1, -40, 6, 0);
        sdGainSlider.setPaintTicks(true);
        sdGainSlider.addChangeListener(new GainChangeListener(2));
        add(sdGainSlider);

        JSlider hhSampleRateSlider = new JSlider();
    }

    public class GainChangeListener implements ChangeListener{

        private int instrument;

        public GainChangeListener(int instrument){
            this.instrument = instrument;
        }
        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider root = (JSlider) e.getSource();
            if (!root.getValueIsAdjusting()) {
                player.setGain(instrument, root.getValue());
            }
        }
    }
}