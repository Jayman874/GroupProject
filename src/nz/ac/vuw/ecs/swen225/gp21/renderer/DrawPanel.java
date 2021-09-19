package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp21.app.App;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;

public class DrawPanel extends JPanel{
	
	public static final int TILE_SIZE = 50;
	public static final int OFFSET_X = 50;
	public static final int OFFSET_Y = 50;
	public static final int BOARD_SIZE = 25;
	
	private Image blueKeyPNG 	= loadImage("blue_key.png");
	private Image blueLockPNG	= loadImage("blue_lock.png");
	
	private Image greenKeyPNG 	= loadImage("green_key.png");
	private Image greenLockPNG 	= loadImage("green_lock.png");
	
	private Image redKeyPNG 	= loadImage("red_key.png");
	private Image redLockPNG 	= loadImage("red_lock.png");
	
	private Image freeTilePNG 	= loadImage("free_tile.png");
	private Image helpPNG 		= loadImage("help.png");
	private Image wallPNG 		= loadImage("wall.png");
	
	private Image exitPNG 		= loadImage("exit.png");
	private Image exitLockPNG 	= loadImage("exit_lock.png");
	private Image treasurePNG 	= loadImage("treasure.png");
	
	private Image chapUpPNG 	= loadImage("chap_up.png");
	private Image chapDownPNG 	= loadImage("chap_down.png");
	private Image chapLeftPNG 	= loadImage("chap_left.png");
	private Image chapRightPNG 	= loadImage("chap_right.png");
	
	private Tile outsideBoard = new FreeTile(); //maybe draw a new tile?
	
	public static final String PATH = "src/images/";
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}
	
	
	private void drawGame(Graphics g) {
		drawBoard(g);
		drawActor(g);
	}
		
	private void drawBoard(Graphics g) {
		int chapsXPos = 0; //get chaps x location
		int chapsYPos = 0; //get chaps y location
		
		int colIndex = 0;
		for(int col = chapsXPos - 4; col <= chapsXPos + 4; col++) {
			
			int rowIndex = 0; 
			for(int row = chapsYPos - 4; row <= chapsYPos + 4; row++) {
				
				if(row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE ) {
					drawTile(g, outsideBoard, colIndex, rowIndex);
					continue;
				} 
				
				Tile tile = Board.getBoard()[col][row]; // getTileAt(row,col);
				if(tile instanceof Chap) {
					drawChap(g);
				} else {
					drawTile(g, tile, colIndex, rowIndex);
				}
			}
		}
	}
	
	private void drawTile(Graphics g, Tile tile, int onScreenX, int onScreenY) {
		if(tile instanceof Door) {
			Door door = (Door) tile;
			String colour = door.getLockedDoorColour();
			if(colour.equals("green"))    {g.drawImage(greenLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else if(colour.equals("blue")){ g.drawImage(blueLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else {							g.drawImage(redLockPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		}
		else if(tile instanceof Key) {
			Key key = (Key) tile;
			String colour = key.getKeyColour();
			if(colour.equals("green"))    { g.drawImage(greenKeyPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else if(colour.equals("blue")){ g.drawImage(blueKeyPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else {							g.drawImage(redKeyPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		}
		else if(tile instanceof ExitLock) { g.drawImage(exitLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof ExitTile) { g.drawImage(exitPNG, 	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof InfoField){ g.drawImage(wallPNG, 	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof Treasure) { g.drawImage(treasurePNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof WallTile) { g.drawImage(wallPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else {								g.drawImage(freeTilePNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
	}
	
	private void drawChap(Graphics g) {
		Move latestMove = null; 
		String direction = null; //latestMove.getDirection;
		int chapXOffset = 10;
		int chapYOffset = 10;
		
		g.drawImage(freeTilePNG,(4*TILE_SIZE) + OFFSET_X, (4*TILE_SIZE) + OFFSET_Y, TILE_SIZE, TILE_SIZE, null);
		
		if(direction.equals("up")) {
			g.drawImage(chapUpPNG, 		(4*TILE_SIZE) + OFFSET_X + chapXOffset, (4*TILE_SIZE) + OFFSET_Y + chapYOffset, TILE_SIZE, TILE_SIZE, null);
		} else if(direction.equals("left")) {
			g.drawImage(chapLeftPNG, 	(4*TILE_SIZE) + OFFSET_X + chapXOffset, (4*TILE_SIZE) + OFFSET_Y + chapYOffset, TILE_SIZE, TILE_SIZE, null);
		} else if(direction.equals("right")) {
			g.drawImage(chapRightPNG, 	(4*TILE_SIZE) + OFFSET_X + chapXOffset, (4*TILE_SIZE) + OFFSET_Y + chapYOffset, TILE_SIZE, TILE_SIZE, null);
		} else {
			g.drawImage(chapDownPNG,	(4*TILE_SIZE) + OFFSET_X + chapXOffset, (4*TILE_SIZE) + OFFSET_Y + chapYOffset, TILE_SIZE, TILE_SIZE, null);
		}
	}
	
	private void drawActor(Graphics g) {
		//char direction = getDirection();
				//switch(direction) {
				//	case 'u':
				//		g.drawImage(actor_up);
				//	case 'l':
				//		g.drawImage(actor_left);
				//	case 'r':
				//		g.drawImage(actor_right);
				//	case 'd':
				//		g.drawImage(actor_down); }
	}
	
	private static Image loadImage(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		File imageFile = new File(PATH + filename);
		try {
			Image img = ImageIO.read(imageFile);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}
	
}
