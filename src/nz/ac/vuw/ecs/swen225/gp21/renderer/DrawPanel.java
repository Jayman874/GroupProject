package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import nz.ac.vuw.ecs.swen225.gp21.app.GUI;
import nz.ac.vuw.ecs.swen225.gp21.app.InputUpdate;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;

/**
 * A Class that paints the game board onto a JPanel. 
 * All images drawn by Stefan Jenkins
 *
 * @author stefanjenkins
 *
 */
public class DrawPanel extends JPanel implements InputUpdate{
	private Audio audio; 
	
	public static final int TILE_SIZE = 70;
	public static final int BOARD_SIZE = boardSize(); 
	public static final int VIEW_WINDOW = 9; //must be odd number >= 3;
	
	private Image blueKeyPNG 	= loadImage("blue_key.png"); 
	private Image blueLockPNG	= loadImage("blue_lock.png");
	 
	private Image greenKeyPNG 	= loadImage("green_key.png");
	private Image greenLockPNG 	= loadImage("green_lock.png");
	
	private Image redKeyPNG 	= loadImage("red_key.png");
	private Image redLockPNG 	= loadImage("red_lock.png");
	
	private Image freeTilePNG 	= loadImage("free_tile.png");
	private Image helpPNG 		= loadImage("help.png");
	private Image wallPNG 		= loadImage("wall.png");
	private Image outsidePNG	= loadImage("outside_tile.png");
	
	private Image exitPNG 		= loadImage("exit.png");
	private Image exitLockPNG 	= loadImage("exit_lock.png");
	private Image treasurePNG 	= loadImage("treasure.png");
	
	private Image chapUpPNG 	= loadImage("chap_up.png");
	private Image chapDownPNG 	= loadImage("chap_down.png");
	private Image chapLeftPNG 	= loadImage("chap_left.png");
	private Image chapRightPNG 	= loadImage("chap_right.png");
	
	private Image EnemyPNG		= loadImage("enemy.png");
	
	private Move chapsLatestMove = null;
	private static Chap chap = findChap();
	
	public static final String PATH = "src/images/";
	
	/**
	 * Constructor for DrawPanel.
	 *
	 * @param gui
	 */
	public DrawPanel() {
		setBackground(Color.BLACK);
		audio = new Audio(chap);
	}
	
	@Override
	public void update(Move move) {
		if(move != null) {
			chapsLatestMove = move;
		}
		repaint();
		audio.update(move);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBoard(g);
		drawChap(g); 
	}
	
	/**
	 * Draws the current game board visible in the viewing window.
	 *
	 * @param g - graphics
	 */
	private void drawBoard(Graphics g) {
		try {
			chap = findChap();
			Location chapsLocation = chap.getLocation();
			int chapsXPos = chapsLocation.getX();
			int chapsYPos = chapsLocation.getY();

			int xIndex = 0;
			for(int x = chapsXPos - (VIEW_WINDOW/2); x <= chapsXPos + (VIEW_WINDOW/2); x++) {

				int yIndex = 0; 
				for(int y = chapsYPos - (VIEW_WINDOW/2); y <= chapsYPos + (VIEW_WINDOW/2); y++) {

					//if outside of the board:
					if(y < 0 || y > BOARD_SIZE || x < 0 || x > BOARD_SIZE ) {
						g.drawImage(outsidePNG,xIndex*TILE_SIZE, yIndex*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
						yIndex++;
						continue;
					} 

					Tile tile = Board.getBoard()[y][x]; 
					if(tile instanceof Chap) {
						//draw nothing
					} else {
						drawTile(g, tile, xIndex, yIndex);
					}
					yIndex++;
				}
				xIndex++;
			}
		} catch (Exception e)  {
			System.out.println("Chap not found");
		}
	}
	
	/**
	 * Draws a tile of a given type onto the board at a given location.
	 *
	 * @param g - graphics
	 * @param tile - type of tile to be drawn.
	 * @param onScreenX - the x coordinate of the tile relative to the view window.
	 * @param onScreenY - the y coordinate of the tile relative to the view window
	 */
	private void drawTile(Graphics g, Tile tile, int onScreenX, int onScreenY) {
		if(tile instanceof Door) {
			Door door = (Door) tile; 
			String colour = door.getLockedDoorColour();
			if(colour.equals("g"))    {		g.drawImage(greenLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else if(colour.equals("b")){ 	g.drawImage(blueLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else {							g.drawImage(redLockPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		}
		else if(tile instanceof Key) {
			Key key = (Key) tile;
			String colour = key.getKeyColour();
			if(colour.equals("g"))    { 	g.drawImage(greenKeyPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else if(colour.equals("b")){ 	g.drawImage(blueKeyPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else {							g.drawImage(redKeyPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		}
		else if(tile instanceof ExitLock) { g.drawImage(exitLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof ExitTile) { g.drawImage(exitPNG, 	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof InfoField){ g.drawImage(helpPNG, 	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof Treasure) { g.drawImage(treasurePNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof WallTile) { g.drawImage(wallPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		
		else if(tile instanceof Actor)	  {   drawActor(g, onScreenX, onScreenY); }
		
		else {								g.drawImage(freeTilePNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
	}
	
	/**
	 * Draws Chap onto the board in the direction of his latest move.
	 *
	 * @param g - graphics
	 */
	private void drawChap(Graphics g) {
		String direction = null;
		if(chapsLatestMove != null) { 
			direction = this.chapsLatestMove.getDirection();
		} else {
			direction = "down"; //if chap hasn't moved yet, the default position is down.
		}
		
		if(Board.getInfoTile()) { //if chap is on a help tile, draw that tile first
			g.drawImage(helpPNG,(4*TILE_SIZE), (4*TILE_SIZE), TILE_SIZE, TILE_SIZE, null);
		} else { //else draw a free tile under him.
			g.drawImage(freeTilePNG,(4*TILE_SIZE), (4*TILE_SIZE), TILE_SIZE, TILE_SIZE, null);
		}
			
		//draws chap facing the direction of his last move:
		if(direction.equals("up")) {
			g.drawImage(chapUpPNG, 		((VIEW_WINDOW/2)*TILE_SIZE) , ((VIEW_WINDOW/2)*TILE_SIZE) , TILE_SIZE, TILE_SIZE, null);
		} else if(direction.equals("left")) {
			g.drawImage(chapLeftPNG, 	((VIEW_WINDOW/2)*TILE_SIZE) , ((VIEW_WINDOW/2)*TILE_SIZE) , TILE_SIZE, TILE_SIZE, null);
		} else if(direction.equals("right")) {
			g.drawImage(chapRightPNG, 	((VIEW_WINDOW/2)*TILE_SIZE) , ((VIEW_WINDOW/2)*TILE_SIZE) , TILE_SIZE, TILE_SIZE, null);
		} else {
			g.drawImage(chapDownPNG,	((VIEW_WINDOW/2)*TILE_SIZE) , ((VIEW_WINDOW/2)*TILE_SIZE) , TILE_SIZE, TILE_SIZE, null);
		}
	}
	
	/**
	 * Draws the enemy onto the board. 
	 *
	 * @param g - graphics
	 * @param x - the on screen x coordinate of the actor.
	 * @param y - the on screen y coordinate of the actor.
	 */
	private void drawActor(Graphics g, int x, int y) {
		g.drawImage(freeTilePNG,x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
		g.drawImage(EnemyPNG,x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
	}
	
	//=======================================================================
	// UTILITY METHODS
	//=======================================================================
	
	/**
	 * Returns the size of the current board.
	 *
	 * @return Size as an int.
	 */
	public static int boardSize() {
		return Board.getBoard()[0].length-1;
	}
	
	/**
	 * Loads an image from given path.
	 *
	 * @param filename - name of image file.
	 * @return Image from path.
	 */
	private static Image loadImage(String filename) {
		File imageFile = new File(PATH + filename);
		try {
			Image img = ImageIO.read(imageFile);
			return img;
		} catch (IOException e) {
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}
	
	public static Chap findChap() {
		for(int i = 0; i < Board.getBoard().length; i++) {
			for(int j = 0; j < Board.getBoard().length; j++) {
				Tile[][] tiles = Board.getBoard();
				Tile board = tiles[i][j];
				if(board instanceof Chap) {
					chap = (Chap) Board.getBoard()[i][j];
				}
			}
		}
		return chap;
	}
	
}
