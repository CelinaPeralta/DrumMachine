import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by celinaperalta on 5/17/17.
 */
public class MixerPanel extends JPanel {

    private Player player = DrumMachineUI.player;
    private JLabel[] instrumentLabels = new JLabel[DrumSounds.NUM_SOUNDS];
    private JSlider[] gainSliders = new JSlider[DrumSounds.NUM_SOUNDS];
    private JToggleButton[] muteButtons = new JToggleButton[DrumSounds.NUM_SOUNDS];


    public MixerPanel() {


        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = getWidth() / 10 - 5;

//        c.gridheight = getHeight();

        //Instrument Labels
        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            String soundName = DrumSounds.audioNames[x];
            JLabel instrumentLabel = new JLabel(soundName.substring(0, soundName.length() - 4));
            instrumentLabel.setHorizontalAlignment(JLabel.CENTER);
            instrumentLabel.setFont(DrumMachineUI.font);
            instrumentLabels[x] = instrumentLabel;

            c.gridx = x;
            c.gridy = 0;
            c.ipady = 10;
            add(instrumentLabel, c);
        }

        //Mute Buttons
        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            String soundName = DrumSounds.audioNames[x];
            JToggleButton muteButton = new JToggleButton("Mute", false);
            muteButton.setFont(DrumMachineUI.font);
            muteButton.addActionListener(new MuteButtonListener(false, x));
            muteButtons[x] = muteButton;

            c.gridx = x;
            c.gridy = 1;
            c.ipady = 10;

            add(muteButton, c);
        }

        //Gain Sliders

        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            JSlider gainSlider = new JSlider(1, -80, 6, -25);
            gainSlider.setPaintTicks(true);
            gainSlider.setMajorTickSpacing(5);
            gainSlider.addChangeListener(new GainChangeListener(x));
            gainSliders[x] = gainSlider;

            c.gridx = x;
            c.gridy = 2;
            c.ipady = 100;

            add(gainSlider, c);
        }

    }

    public void resetMixer() {
        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            gainSliders[x].setValue(-25);
        }
    }

    public class GainChangeListener implements ChangeListener {

        private int instrument;

        public GainChangeListener(int instrument) {
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

    public class MuteButtonListener implements ActionListener {

        private boolean muted;
        private int instrument;
        private int originalGain = -25;

        public MuteButtonListener(boolean muted, int instrument) {
            this.muted = muted;
            this.instrument = instrument;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (muted) {
                player.setGain(instrument, originalGain);
                muted = false;
            } else {
                player.setGain(instrument, -80);
                muted = true;
            }
        }
    }
}