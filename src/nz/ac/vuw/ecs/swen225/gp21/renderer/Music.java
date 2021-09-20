package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.io.File; 
import java.util.Scanner; 
import java.io.IOException; 

import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Clip;  
import javax.sound.sampled.UnsupportedAudioFileException; 

public class Music {
	Clip clip;
	long timePosition;
	boolean paused = false;
	
	public Music() {
		initMusic();
	}
	
	public void initMusic() {
		start("src/audio_clips/game_music.wav");
	}
	
	public void start(String fileName) {
		try {
			File file = new File(fileName);
			AudioInputStream music = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(music);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.setFramePosition(0);
		timePosition = 0;
		clip.start();
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	public void pause() {
		if(paused) return;
		timePosition = clip.getMicrosecondPosition();
		clip.stop();
		paused = true;
		
	}
	
	public void resume() {
		if(!paused) return;
		clip.setMicrosecondPosition(timePosition);
		clip.start();
		paused = false;
	}
}
