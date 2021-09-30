package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * 
 * A Class that plays audio clip sound effects for the game. 
 * All sound effects composed by Stefan Jenkins.
 *
 * @author stefanjenkins
 *
 */
public class Audio {
	private static String chapMoveWAV 	= "chap_move.wav";
	private static String helpWAV 		= "help.wav";
	private static String exitLockWAV 	= "exit_lock.wav";
	private static String exitWAV 		= "exit.wav";
	private static String unlockWAV 	= "lock.wav";
	private static String pickUpWAV 	= "pick_up.wav";
	private static String signOnWAV 	= "sign_on.wav";
	private static String blockedWAV 	= "blocked.wav";
	
	private static String path 			= "src/audio_clips/";
	
	/**
	 * Plays chap's moving sound effect.
	 */
	public static void playChapMove() {
		playAudio(chapMoveWAV);
	}
	
	/**
	 * Plays chap moving onto the help tile sound effect.
	 */
	public static void playHelp() {
		playAudio(helpWAV);
	}
	
	/**
	 * Plays chap unlocking the exit lock sound effect.
	 */
	public static void playExitLock() {
		playAudio(exitLockWAV);
	}
	
	/**
	 * Plays chap moving onto the exit tile sound effect.
	 */
	public static void playExit() {
		playAudio(exitWAV);
	}
	
	/**
	 * Plays chap unlocking a lock tile sound effect.
	 */
	public static void playUnlock() {
		playAudio(unlockWAV);
	}
	
	/**
	 * Plays chap picking up a key or treasure piece sound effect.
	 */
	public static void playPickUp() {
		playAudio(pickUpWAV);
	}
	
	/**
	 * Plays title screen neon sign sound effect.
	 */
	public static void playSignOn() {
		playAudio(signOnWAV);
	}
	
	/**
	 * Plays chap trying to move onto a blocked square sound effect.
	 */
	public static void playBlocked() {
		playAudio(blockedWAV);
	}
	
	/**
	 * Opens an audio file as a clip and plays it.
	 * Method written with help from DelftStack.com article 'Play Sound in Java'.
	 *
	 * @param fileName - name of audio file.
	 */
	private static void playAudio(String fileName) {
		try {
			File audioFile = new File(path + fileName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
