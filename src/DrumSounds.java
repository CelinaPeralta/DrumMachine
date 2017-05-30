/**
 * Created by celinaperalta on 5/22/17.
 */


public class DrumSounds {

    public static final int NUM_SOUNDS = 11;

    public Sound AC;
    public Sound BD;
    public Sound SD;
    public Sound LC;
    public Sound LT;
    public Sound MC;
    public Sound MT;
    public Sound HC;
    public Sound HT;
    public Sound CL;
    public Sound RS;
    public Sound MA;
    public Sound CP;
    public Sound CB;
    public Sound CY;
    public Sound OH;
    public Sound CH;
    public static Sound[] audioClips;
    public static String[] audioNames = new String[]{"HiHat1.wav", "Kick1.wav", "Snare1.wav","Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "Kick1.wav", "HiHat1.wav", "Kick1.wav"};


    public DrumSounds() throws Exception {
        this.AC = new Sound("Kick1.wav");
        this.BD = new Sound("Kick1.wav");
        this.SD = new Sound("Snare1.wav");
        this.LC = new Sound("Kick1.wav");
        this.LT = new Sound("Kick1.wav");
        this.MC = new Sound("Kick1.wav");
        this.MT = new Sound("Kick1.wav");
        this.HC = new Sound("Kick1.wav");
        this.HT = new Sound("Kick1.wav");
        this.CL = new Sound("Kick1.wav");
        this.RS = new Sound("Kick1.wav");
        this.MA = new Sound("Kick1.wav");
        this.CP = new Sound("Kick1.wav");
        this.CB = new Sound("Kick1.wav");
        this.CY = new Sound("Kick1.wav");
        this.OH = new Sound("HiHat1.wav");
        this.CH = new Sound("Kick1.wav");

        audioClips = new Sound[]{AC, BD, SD, LC, LT, MC, MT, HC, HT, CL, RS, MA, CP, CB, CY, OH, CH};

    }
}
