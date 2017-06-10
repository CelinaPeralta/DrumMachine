import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Created by celinaperalta on 5/17/17.
 */
public class MixerPanel extends JPanel {

    private Player player = DrumMachineUI.player;
    private JLabel[] instrumentLabels = new JLabel[DrumSounds.NUM_SOUNDS];
    private JSlider[] gainSliders = new JSlider[DrumSounds.NUM_SOUNDS];

    public MixerPanel() {

        setLayout(new GridLayout(2, 10));



        //Instrument Labels
        for(int x = 0; x < DrumSounds.NUM_SOUNDS; x++){
            String soundName = DrumSounds.audioNames[x];
            JLabel instrumentLabel = new JLabel(soundName.substring(0, soundName.length()-4));
            instrumentLabel.setFont(DrumMachineUI.font);
            instrumentLabels[x] = instrumentLabel;

            add(instrumentLabel);
        }

        //Gain Sliders

        for(int x = 0; x < DrumSounds.NUM_SOUNDS; x++){
            JSlider gainSlider = new JSlider(1, -80, 6, -25);
            gainSlider.setPaintTicks(true);
            gainSlider.setMajorTickSpacing(5);
            gainSlider.addChangeListener(new GainChangeListener(x));
            gainSliders[x] = gainSlider;

            add(gainSlider);
        }

    }

    public void resetMixer(){
        for(int x = 0; x < DrumSounds.NUM_SOUNDS; x++){
            gainSliders[x].setValue(-25);
        }
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