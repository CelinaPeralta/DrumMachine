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
    private volatile boolean isPlaying = false;
    private boolean isChanged = false;
    private boolean[][] beatArray = new boolean[DrumSounds.NUM_SOUNDS][16];

    private JLabel instrumentLabel = new JLabel();
    private JLabel tempoLabel = new JLabel();
    private JSlider tempoSlider;
    private JSlider instrumentSlider;
    private JToggleButton startButton;

    private RhythmPanel rhythmPanel = DrumMachineUI.rhythmPanel;
    private MixerPanel mixerPanel = DrumMachineUI.mixerPanel;
    private Player player = DrumMachineUI.player;

    public ControlPanel() {

        tempo = 120;
        timeSignature4 = true;

        currentInstrument = 0;
        rhythmPanel.setInstrument(currentInstrument, beatArray[currentInstrument]);
        //rhythmPanel.updateBeats(beatArray[currentInstrument]);

        setLayout(new GridLayout(9, 1, 20, 5));
        setPreferredSize(new Dimension(325, getHeight()));

        add(new JLabel());


        tempoSlider = new JSlider(40, 300, tempo);
        tempoSlider.setMajorTickSpacing(5);
        tempoSlider.setPaintTicks(true);
        tempoSlider.setSnapToTicks(true);
        tempoSlider.addChangeListener(new TempoChangeListener());

        add(tempoLabel);

        tempoLabel.setHorizontalAlignment(JLabel.CENTER);
        tempoLabel.setFont(Style.control_font);
        tempoLabel.setText("Tempo: " + tempoSlider.getValue());

        add(tempoSlider);

        instrumentSlider = new JSlider(0, DrumSounds.NUM_SOUNDS - 1, 0);
        instrumentSlider.setMajorTickSpacing(1);
        instrumentSlider.setPaintTicks(true);
        instrumentSlider.setSnapToTicks(true);
        instrumentSlider.addChangeListener(new InstrumentChangeListener());

        add(instrumentLabel);

        add(instrumentSlider);


        String soundName = DrumSounds.audioNames[0];
        instrumentLabel.setHorizontalAlignment(JLabel.CENTER);
        instrumentLabel.setFont(Style.control_font);
        instrumentLabel.setText("Instrument: " + soundName.substring(0, soundName.length() - 4));

        JRadioButton time4 = new JRadioButton("4", true);
        JRadioButton time3 = new JRadioButton("3");
        time4.setActionCommand("4");
        time3.setActionCommand("3");

        time4.addActionListener(new TimeActionListener());
        time4.setHorizontalAlignment(0);
        time3.addActionListener(new TimeActionListener());
        time3.setHorizontalAlignment(0);

        ButtonGroup time = new ButtonGroup();
        time.add(time4);
        time.add(time3);

        JLabel timeSig = new JLabel("Time Signature");
        timeSig.setFont(Style.control_font);
        timeSig.setHorizontalAlignment(0);

        JPanel timePanel = new JPanel(new GridLayout(1, 2));

        add(timeSig);
        timePanel.add(time3);
        timePanel.add(time4);
        timePanel.setPreferredSize(new Dimension(getWidth(), 10));

        add(timePanel);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        startButton = new JToggleButton("Start", false);
        startButton.setActionCommand("start");
        startButton.addActionListener(new StartActionListener());


        buttonPanel.add(startButton);

        JButton resetButton = new JButton(("Reset"));
        resetButton.addActionListener(new ResetChangeListener());

        buttonPanel.add(resetButton);

        add(buttonPanel);

        add(new JLabel());
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

    public boolean[][] getBeatArray() {
        return beatArray;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setChangedFalse() {
        isChanged = false;
    }


    public class TempoChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider root = (JSlider) e.getSource();
            if (root.getValueIsAdjusting()) {
                tempo = root.getValue();
                tempoLabel.setText("Tempo: " + root.getValue());

            }
        }
    }

    public class TimeActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            timeSignature4 = !timeSignature4;
            player.setTimeSignature4(timeSignature4);
            rhythmPanel.clear3();
        }
    }

    public class StartActionListener implements ActionListener {

        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            isPlaying = !isPlaying;
        }
    }

    public class InstrumentChangeListener implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider root = (JSlider) e.getSource();
            if (root.getValueIsAdjusting()) {
                rhythmPanel.updateBeats(beatArray[currentInstrument]);
                currentInstrument = root.getValue();
                rhythmPanel.setInstrument(currentInstrument, beatArray[currentInstrument]);
                rhythmPanel.updateBeats(beatArray[currentInstrument]);
                String soundName = DrumSounds.audioNames[root.getValue()];
                instrumentLabel.setText("Instrument: " + soundName.substring(0, soundName.length() - 4));
            }
        }
    }

    public class ResetChangeListener implements ActionListener {

        @Override
        public synchronized void actionPerformed(ActionEvent e) {
            instrumentSlider.setValue(0);
            for (int y = 0; y < beatArray.length; y++) {
                for (int x = 0; x < beatArray[y].length; x++)
                    beatArray[y][x] = false;
            }

            tempoSlider.setValue(120);
            isPlaying = false;
            startButton.setSelected(false);
            rhythmPanel.clearBeats();
            rhythmPanel.clear3();
            mixerPanel.resetMixer();

            tempoLabel.setText("Tempo: 120");
            instrumentLabel.setText("Instrument: " + DrumSounds.audioNames[0].substring(0, DrumSounds.audioNames[0].length() - 4));


        }
    }
}