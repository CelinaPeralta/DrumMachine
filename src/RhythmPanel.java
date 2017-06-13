import javax.naming.ldap.Control;
import javax.sound.midi.Instrument;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicToggleButtonUI;
import javax.swing.plaf.metal.MetalToggleButtonUI;
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

        setLayout(new GridLayout(1, 16, 5, 50));
        setPreferredSize(new Dimension(getWidth(), 85));

        jCheckBoxes = new JToggleButton[16];

        for (int x = 0; x < jCheckBoxes.length; x++) {
            JToggleButton beat = new JToggleButton();
            beat.addActionListener(new BeatButtonListener(x));
            beat.setOpaque(true);
            beat.setText(Integer.toString(x + 1));
            if ((x) % 4 == 0) {
                beat.setForeground(Color.CYAN);
                beat.setBorderPainted(true);
                beat.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.YELLOW));
            } else {
                beat.setForeground(Color.CYAN);
            }
            beat.setBackground(Color.DARK_GRAY);
            beat.setUI(new BasicToggleButtonUI());
            jCheckBoxes[x] = beat;
            this.add(beat);
        }
    }

    public void updateBeats(boolean[] beats) {
        for (int i = 0; i < jCheckBoxes.length; i++) {
            jCheckBoxes[i].setSelected(beats[i]);
            if (beats[i]) {
                jCheckBoxes[i].setBackground(Color.RED);
            } else {
                jCheckBoxes[i].setBackground(Color.DARK_GRAY);
            }
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
            JToggleButton root = (JToggleButton) e.getSource();
            beats[beat] = !beats[beat];
            if (beats[beat]) {
                root.setBackground(Color.RED);
            } else {
                root.setBackground(Color.DARK_GRAY);
            }
            player.addLoop(instrument, beats);
        }
    }

    public void clearBeats() {
        for (int x = 0; x < jCheckBoxes.length; x++) {
            jCheckBoxes[x].setSelected(false);
            jCheckBoxes[x].setBackground(Color.DARK_GRAY);
            beats[x] = false;
        }
        player.clearLoop();
    }

    public void clear3() {
        for (int x = 12; x < jCheckBoxes.length; x++) {
            jCheckBoxes[x].setSelected(false);
            jCheckBoxes[x].setBackground(Color.DARK_GRAY);
            beats[x] = false;
        }
    }


    public void play() {
        player.play();

        int b = player.getBeat();

        jCheckBoxes[b].setBackground(Color.GREEN);

        if (b == 0) {
            int beat = player.isTime4() ? 15 : 11;
            jCheckBoxes[beat].setBackground(beats[beat] ? Color.RED : Color.DARK_GRAY);
        }
        if (b > 0) {
            jCheckBoxes[b - 1].setBackground(beats[b - 1] ? Color.RED : Color.DARK_GRAY);
        }
    }
}
