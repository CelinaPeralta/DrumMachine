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
    private boolean timeSignature4;  //beats per measure and each beat is a quarter note
    private boolean isPlaying = false;
    private boolean isChanged = false;
    private JLabel tempoLabel = new JLabel();
    private RhythmPanel player;
    private boolean[][] beatArray = new boolean[DrumSounds.NUM_SOUNDS][16];

    public ControlPanel(RhythmPanel player) {
        this.player = player;
        tempo = 120;
        timeSignature4 = true;

        setLayout(new GridLayout(5, 2));

        JSlider tempoSlider = new JSlider(40, 300, tempo);
        tempoSlider.setMajorTickSpacing(5);
        tempoSlider.setPaintTicks(true);
        tempoSlider.setSnapToTicks(true);
        tempoSlider.addChangeListener(new TempoChangeListener());

        add(tempoSlider);
        tempoLabel.setText("Tempo: " + tempoSlider.getValue());
        add(tempoLabel);

        JSlider instrumentSlider = new JSlider(0, DrumSounds.NUM_SOUNDS, 0);
        instrumentSlider.setMajorTickSpacing(1);
        instrumentSlider.setPaintTicks(true);
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

        add(startButton);
        add(new JLabel());

        JButton resetButton = new JButton(("Reset"));
        resetButton.addChangeListener(new ResetChangeListener());

        add(resetButton);
    }

    public int getCurrentInstrument() {
        return currentInstrument;
    }

    public int getTempo() {
        return tempo;
    }

    public boolean getTimeSignature() {
        return timeSignature4;
    }

    public boolean[][] getBeatArray(){
        return  beatArray;
    }

    public boolean isChanged(){
        return isChanged;
    }

    public void setChangedFalse(){
        isChanged = false;
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
            timeSignature4 = !timeSignature4;
            System.out.println("Test1");
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
                player.updateBeats(beatArray[currentInstrument]);
                currentInstrument = root.getValue();
                player.setInstrument(currentInstrument, beatArray[currentInstrument]);
                player.updateBeats(beatArray[currentInstrument]);
            }
        }
    }

    public class ResetChangeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {

        }
    }
}