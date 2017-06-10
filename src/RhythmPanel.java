import javax.naming.ldap.Control;
import javax.sound.midi.Instrument;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by brian on 5/29/2017.
 */
public class RhythmPanel extends JPanel {

    private Player player = DrumMachineUI.player;
    private int instrument;
    private boolean[] beats = new boolean[16];
    private JToggleButton[] jCheckBoxes;


    public RhythmPanel() {

        instrument = 0;

        this.player = player;

        setLayout(new GridLayout(1, 16, 5, 30));

        jCheckBoxes = new JToggleButton[16];

        for (int x = 0; x < jCheckBoxes.length; x++) {
            JToggleButton beat = new JToggleButton();
            beat.addActionListener(new BeatButtonListener(x));
            beat.setOpaque(true);
            jCheckBoxes[x] = beat;
            this.add(beat);
        }
    }

    public void updateBeats(boolean[] beats) {
        for (int i = 0; i < jCheckBoxes.length; i++) {
            jCheckBoxes[i].setSelected(beats[i]);
        }
    }

    public void setInstrument(int instrument, boolean[] newBeats) {
        this.instrument = instrument;
        beats = newBeats;
    }

    public class BeatButtonListener implements ActionListener {

        private int beat;

        public BeatButtonListener(int beat) {
            this.beat = beat;
        }

        public void actionPerformed(ActionEvent e) {
            beats[beat] = !beats[beat];
            player.addLoop(instrument, beats);
        }
    }

    public void clearBeats() {
        for (int x = 0; x < jCheckBoxes.length; x++) {
            jCheckBoxes[x].setSelected(false);
            beats[x] = false;
        }
        player.clearLoop();
    }


    public void play() {
        player.play();
        int b = player.getBeat();


        jCheckBoxes[b].setBackground(Color.RED);

        if (b == 0) {
            jCheckBoxes[player.isTime4() ? 15 : 11].setBackground(null);
        }
        if (b > 0) {
            jCheckBoxes[b - 1].setBackground(null);
        }

    }
}
