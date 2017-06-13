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

        setPreferredSize( new Dimension(700, getHeight()));
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = getWidth() / 10;

//        c.gridheight = getHeight();

        //Instrument Labels
        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            String soundName = DrumSounds.audioNames[x];
            JLabel instrumentLabel = new JLabel(soundName.substring(0, soundName.length() - 4));
            instrumentLabel.setHorizontalAlignment(JLabel.CENTER);
            instrumentLabel.setFont(Style.mixer_font);
            instrumentLabels[x] = instrumentLabel;

            c.gridx = x;
            c.gridy = 0;
            c.ipady = 50;

            add(instrumentLabel, c);
        }

        //Mute Buttons
        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            String soundName = DrumSounds.audioNames[x];
            JToggleButton muteButton = new JToggleButton("Mute", false);
            muteButton.setFont(Style.mixer_font);
            muteButton.addActionListener(new MuteButtonListener(false, x));
            muteButtons[x] = muteButton;

            c.gridx = x;
            c.gridy = 1;
            c.ipady = 10;

            add(muteButton, c);
        }

        //Gain Sliders

        for (int x = 0; x < DrumSounds.NUM_SOUNDS; x++) {
            JSlider gainSlider = new JSlider(1, Constants.GAIN_MIN, 6, Constants.GAIN_MAX);
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
            gainSliders[x].setValue(Constants.GAIN_MAX);
            gainSliders[x].setValueIsAdjusting(true);
            ((MuteButtonListener)(muteButtons[x].getActionListeners()[0])).reset();
        }
    }

    public class GainChangeListener implements ChangeListener {

        private int instrument;

        public GainChangeListener(int instrument) {
            this.instrument = instrument;
        }

        @Override
        public synchronized void stateChanged(ChangeEvent e) {
            JSlider root = (JSlider) e.getSource();
            if (root.getValueIsAdjusting()) {
                player.setGain(instrument, root.getValue());

                if (muteButtons[instrument].isSelected()) {
                    muteButtons[instrument].setSelected(false);
                    ((MuteButtonListener) (muteButtons[instrument].getActionListeners()[0])).setFalse();
                }
            }
        }
    }

    public class MuteButtonListener implements ActionListener {

        private boolean muted;
        private int instrument;
        private int originalGain;

        public MuteButtonListener(boolean muted, int instrument) {
            this.muted = muted;
            this.instrument = instrument;
        }

        public void reset(){
            muted = false;
        }

        public void setFalse(){
            muted = false;
        }

        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            if (muted) {
                gainSliders[instrument].setValue(originalGain);
                gainSliders[instrument].setValueIsAdjusting(true);
                gainSliders[instrument].setValueIsAdjusting(false);
                muted = false;
            } else {
                originalGain = gainSliders[instrument].getValue();
                player.setGain(instrument, Constants.GAIN_MIN);
                muted = true;
            }
        }
    }
}