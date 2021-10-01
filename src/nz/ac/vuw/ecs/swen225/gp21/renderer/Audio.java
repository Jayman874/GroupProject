package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.io.File;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import nz.ac.vuw.ecs.swen225.gp21.app.InputUpdate;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;

/**
 * 
 * A Class that plays audio clip sound effects for the game. 
 * All sound effects composed by Stefan Jenkins.
 *
 * @author jenkinstef 300485100
 *
 */
public class Audio implements InputUpdate{
	private static String chapMoveWAV 	= "chap_move.wav";
	private static String helpWAV 		= "help.wav";
	private static String exitLockWAV 	= "exit_lock.wav";
	private static String exitWAV 		= "exit.wav";
	private static String unlockWAV 	= "lock.wav";
	private static String pickUpWAV 	= "pick_up.wav";
	private static String signOnWAV 	= "sign_on.wav";
	private static String chapDeathWAV	= "chap_dead.wav";
	
	private static final String PATH 	= "src/audio_clips/";
	private Chap chap;
	private String[][] tilesAroundChap;
	
	/**
	 * Audio constructor.
	 *
	 * @param c
	 */
	public Audio(Chap c) {
		chap = c;
		tilesAroundChap = getTilesAroundChap();
	}
	
	/**
	 * Resets chap and tilesAroundChap if level ends or chap dies.
	 *
	 * @param c - the new chap.
	 */
	public void resetAudio(Chap c) {
		chap = c;
		tilesAroundChap = getTilesAroundChap();
	}
	
	/**
	 * Plays a sound effect by getting getting the tile chap moved onto.
	 *
	 * @param move - chaps latest move.
	 */
	private void playNextSoundFX(Move move) {
		
		if(chap.isDead()) {
			playChapDeath();
		}
		
		if(move == null) return;
		String direction = move.getDirection();
		String newTile = null;
		switch(direction) {
			case("up"):
				newTile = tilesAroundChap[0][1];
				break;
			case("down"):
				newTile = tilesAroundChap[2][1];
				break;
			case("left"):
				newTile = tilesAroundChap[1][0];
				break;
			default:
				newTile = tilesAroundChap[1][2];
		}
		
		String[][] newTiles = getTilesAroundChap();
		switch(newTile) {
			case("l"): //door
				playUnlock();
				break;
			case("q"): //exit lock
				playExitLock();
				break;
			case("e"): //exit
				playExit();
				break;
			case("i"): //help
				playHelp();
				break;
			case("k"): //key
			case("t"): //treasure
				playPickUp();
				break;
			case("w"): //wall
			case("ob")://outside
				break;
			default:
				playChapMove();			
		}
		tilesAroundChap = newTiles;
	}

	/**
	 * Returns the toString form of the tiles surrounding Chap as a 2D Array.
	 *
	 * @return - tile Strings as 2D Array.
	 */
	private String[][] getTilesAroundChap() {
		String[][] tiles = new String[3][3];
		int chapX = chap.getLocation().getX();
		int chapY = chap.getLocation().getY();
		
		for(int x = chapX - 1, tilesX = 0; x <= chapX + 1; x++, tilesX++) {
			for(int y = chapY - 1, tilesY = 0; y <= chapY + 1; y++, tilesY++) {
				if(y < 0 || y > DrawPanel.BOARD_SIZE || x < 0 || x > DrawPanel.BOARD_SIZE ) {
					tiles[tilesY][tilesX] = "ob";
				} else {
					tiles[tilesY][tilesX] = Board.getBoard()[y][x].toString();
				}
			}	
		}
		return tiles;
	}
	
	
	/**
	 * Plays chap's moving sound effect.
	 */
	public void playChapMove() {
		playAudio(chapMoveWAV);
	}
	
	/**
	 * Plays chap moving onto the help tile sound effect.
	 */
	public void playHelp() {
		playAudio(helpWAV);
	}
	
	/**
	 * Plays chap unlocking the exit lock sound effect.
	 */
	public void playExitLock() {
		playAudio(exitLockWAV);
	}
	
	/**
	 * Plays chap death sound effect.
	 */
	public void playChapDeath() {
		playAudio(chapDeathWAV);
	}
	
	/**
	 * Plays chap moving onto the exit tile sound effect.
	 */
	public void playExit() {
		playAudio(exitWAV);
	}
	
	/**
	 * Plays chap unlocking a lock tile sound effect.
	 */
	public void playUnlock() {
		playAudio(unlockWAV);
	}
	
	/**
	 * Plays chap picking up a key or treasure piece sound effect.
	 */
	public void playPickUp() {
		playAudio(pickUpWAV);
	}
	
	/**
	 * Plays title screen neon sign sound effect.
	 */
	public static void playSignOn() {
		playAudio(signOnWAV);
	}
	
	/**
	 * Opens an audio file as a clip and plays it.
	 * Method written with help from DelftStack.com article 'Play Sound in Java'.
	 *
	 * @param fileName - name of audio file.
	 */
	private static void playAudio(String fileName) {
		try {
			File audioFile = new File(PATH + fileName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);
			clip.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(Move move) {
		playNextSoundFX(move);
	}
	
	
	//=======================================================================
	// UTILITY METHODS
	//=======================================================================
	
	/**
	 * Prints tilesAroundChap.
	 */
	private void printTiles() {
		for(int x = 0; x < 3; x++) {
			for(int y = 0; y <3; y++) {
				System.out.print(tilesAroundChap[x][y] + " ");
			}
			System.out.println();
		}
	}
	
}
