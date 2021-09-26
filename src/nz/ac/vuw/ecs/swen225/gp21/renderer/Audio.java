package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	private static String chapMoveWAV = "src/audio_clips/chap_move.wav";
	private static String helpWAV = "src/audio_clips/help.wav";
	
	public static void playChapMove() {
		playAudio(chapMoveWAV);
	}
	
	public static void playHelp() {
		playAudio(helpWAV);
	}
	
	private static void playAudio(String fileName) {
		try {
			File audioFile = new File(fileName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
