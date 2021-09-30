package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.io.File; 
import java.util.Scanner; 
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;  
import javax.sound.sampled.UnsupportedAudioFileException; 

/**
 * 
 * A class that plays the sound track for the game.
 * Music composed by Stefan Jenkins.
 * Class written with help from DelftStack.com article 'Play Sound in Java'.
 *
 * @author stefanjenkins
 *
 */
public class Music {
	Clip clip;
	long timePosition;
	boolean paused = false;
	
	public Music() {
		initMusic("src/audio_clips/game_music.wav");
	}
	
	/**
	 * Initializes music audio file as a clip.
	 *
	 * @param fileName - path to audio file.
	 */
	public void initMusic(String fileName) {
		try {
			File file = new File(fileName);
			AudioInputStream music = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(music);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays audio clip from the beginning.
	 */
	public void play() {
		clip.setFramePosition(0);
		timePosition = 0;
		clip.start();
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Pauses audio clip and records position as a long.
	 */
	public void pause() {
		if(paused) return;
		timePosition = clip.getMicrosecondPosition();
		clip.stop();
		paused = true;
		
	}
	
	/**
	 * Resumes audio clip from pause position if paused.
	 */
	public void resume() {
		if(!paused) return;
		clip.setMicrosecondPosition(timePosition);
		clip.start();
		paused = false;
	}
}
