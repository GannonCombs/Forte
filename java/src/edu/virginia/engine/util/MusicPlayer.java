package edu.virginia.engine.util;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class MusicPlayer {
	
	public MusicPlayer() {	}
	
	public void playSong(String filename, float volume) {
		File file = new File("resources" + File.separator + filename);
		
		try {
			AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			Clip clip = AudioSystem.getClip();
            clip.open(audio);
            FloatControl gainControl = 
    			    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    			gainControl.setValue(volume);
            clip.start();
        }
        catch(UnsupportedAudioFileException uae) {
            System.out.println(uae);
        }
        catch(IOException ioe) {
            System.out.println(ioe);
        }
		catch(LineUnavailableException lua) {
            System.out.println(lua);
        }
	}
}
