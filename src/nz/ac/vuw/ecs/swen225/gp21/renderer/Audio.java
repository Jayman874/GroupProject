package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Audio {
	private static String chapMoveWAV = "src/audio_clips/chap_move.wav";
	private static String helpWAV = "src/audio_clips/help.wav";
	private static String exitLockWAV = "src/audio_clips/exit_lock.wav";
	private static String exitWAV = "src/audio_clips/exit.wav";
	private static String unlockWAV = "src/audio_clips/lock.wav";
	private static String pickUpWAV = "src/audio_clips/pick_up.wav";
	private static String signOnWAV = "src/audio_clips/sign_on.wav";
	private static String blockedWAV = "src/audio_clips/sign_on.wav";
	
	public static void playChapMove() {
		playAudio(chapMoveWAV);
	}
	
	public static void playHelp() {
		playAudio(helpWAV);
	}
	
	public static void playExitLock() {
		playAudio(exitLockWAV);
	}
	
	public static void playExit() {
		playAudio(exitWAV);
	}
	
	public static void playUnlock() {
		playAudio(unlockWAV);
	}
	
	public static void playPickUp() {
		playAudio(pickUpWAV);
	}
	
	public static void playSignOn() {
		playAudio(signOnWAV);
	}
	
	public static void playBlocked() {
		playAudio(blockedWAV);
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


//NOTES:
//	- use chap class to find what tile he is on