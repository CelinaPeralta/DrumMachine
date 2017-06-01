import javax.sound.midi.Instrument;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brian on 5/29/2017.
 */

public class ControlPanel extends JPanel {

    private int currentInstrument;
    private int tempo;         //in BPM
    private int timeSignature;  //beats per measure and each beat is a quarter note
    private boolean isPlaying = false;
    private JLabel tempoLabel = new JLabel();
    private RhythmPanel player;
    private boolean[][] beatArray = new boolean[16][DrumSounds.NUM_SOUNDS];

    public ControlPanel(RhythmPanel player) {
        this.player = player;
        tempo = 120;
        timeSignature = 4;

        setLayout(new GridLayout(4, 2));

        JSlider tempoSlider = new JSlider(40, 300, tempo);
        tempoSlider.setMajorTickSpacing(5);
        tempoSlider.setSnapToTicks(true);
        tempoSlider.addChangeListener(new TempoChangeListener());

        add(tempoSlider);
        tempoLabel.setText("Tempo: " + tempoSlider.getValue());
        add(tempoLabel);

        JSlider instrumentSlider = new JSlider(0, 10, 0);
        instrumentSlider.setMajorTickSpacing(1);
        instrumentSlider.setSnapToTicks(true);
        instrumentSlider.addChangeListener(new InstrumentChangeListener());

        add(instrumentSlider);
        add(new JLabel("Instrument"));

        JRadioButton time4 = new JRadioButton("4", true);
        JRadioButton time3 = new JRadioButton("3");
        time4.setActionCommand("4");
        time3.setActionCommand("3");

        time4.addActionListener(new TimeActionListener());
        time3.addActionListener(new TimeActionListener());

        ButtonGroup time = new ButtonGroup();
        time.add(time4);
        time.add(time3);

        JPanel timePanel = new JPanel(new GridLayout(2, 1));
        timePanel.add(time3);
        timePanel.add(time4);

        add(timePanel);
        add(new JLabel("Time Signature"));


        JToggleButton startButton = new JToggleButton("Start", false);
        startButton.setActionCommand("start");

        startButton.addChangeListener(new StartChangeListener());
    }

    public int getCurrentInstrument() {
        return currentInstrument;
    }

    public int getTempo() {
        return tempo;
    }

    public int getTimeSignature() {
        return timeSignature;
    }

    public class TempoChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider root = (JSlider) e.getSource();
            if (!root.getValueIsAdjusting()) {
                tempo = (int) root.getValue();
                tempoLabel.setText("Tempo: " + root.getValue());

            }
        }
    }

    public class TimeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            tempo = Integer.parseInt(e.getActionCommand());
        }
    }

    public class StartChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            isPlaying = true;
        }
    }

    public class InstrumentChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider root = (JSlider) e.getSource();
            if (!root.getValueIsAdjusting()) {
                currentInstrument = root.getValue();
                player.updateBeats(beatArray[currentInstrument]);
            }
        }
    }
}

