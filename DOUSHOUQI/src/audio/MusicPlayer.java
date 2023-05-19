package audio;

import java.io.File;
import java.io.FileNotFoundException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class MusicPlayer implements Runnable{
    File soundFile;
    Thread thread;
    boolean circulate;

    public MusicPlayer(String filepath, boolean circulate) 
            throws FileNotFoundException {
        this.circulate = circulate;
        soundFile = new File(filepath);
        if(!soundFile.exists()) {
            throw new FileNotFoundException(filepath + "未找到");
        }
    }

    public void play() {
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        thread.interrupt();
    }

    public void run() {
        byte[] auBuffer = new byte[1024*128];
        do{
            AudioInputStream audioInputStream = null;
            SourceDataLine auline = null;
         try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
            auline.start();
            int nBytesRead = 0;
            while(nBytesRead != -1) {
                nBytesRead = audioInputStream.read(auBuffer, 0, auBuffer.length);
                if(nBytesRead >= 0) {
                    auline.write(auBuffer, 0, nBytesRead);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            auline.drain();
            auline.close();
        }
        }while(circulate);
    
    }
}

