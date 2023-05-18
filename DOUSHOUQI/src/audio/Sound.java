package audio;

import java.io.FileNotFoundException;

public class Sound {
    static final String BACKGROUND = "DOUSHOUQI\\resource\\background.wav";
    static final String MOVE = "DOUSHOUQI\\resource\\move.wav";
    static final String EAT = "DOUSHOUQI\\resource\\eat.wav";
    static final String WIN = "DOUSHOUQI\\resource\\win.wav";
    
    public static void play(String file, boolean circulate) {
        try {
            MusicPlayer musicPlayer = new MusicPlayer(file, circulate);
            musicPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void stop() throws FileNotFoundException {
        MusicPlayer musicPlayer = new MusicPlayer(BACKGROUND, false);
        musicPlayer.stop();
    }

    public static void background() {
        play(BACKGROUND, true);
    }
    
    public static void move() {
        play(MOVE, false);
    }

    public static void eat() {
        play(EAT, false);
    }

    public static void win() {
        play(WIN, false);
    }
}
