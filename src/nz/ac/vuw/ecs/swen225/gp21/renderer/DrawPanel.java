package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import nz.ac.vuw.ecs.swen225.gp21.app.App;
import nz.ac.vuw.ecs.swen225.gp21.app.GUI;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;

public class DrawPanel extends JPanel {
	private GUI gui;
	private Audio audio;
	
	public static final int TILE_SIZE = 70;
	public static final int BOARD_SIZE = boardSize(); //need to calculate
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
	
	private Move chapsLatestMove = null;
	
	public static final String PATH = "src/images/";
	
	public DrawPanel(GUI gui) {
		setBackground(Color.BLACK);
		this.gui = gui;
	}
	
	public void update(Move move) {
		chapsLatestMove = move;
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}
	
	
	private void drawGame(Graphics g) {
		drawBoard(g);
		drawChap(g); 
		drawActor(g);
	}
		
	private void drawBoard(Graphics g) {
		try {
			Location chapsLocation = gui.findChap().getLocation();
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
	
	private void drawTile(Graphics g, Tile tile, int onScreenX, int onScreenY) {
		if(tile instanceof Door) {
			Door door = (Door) tile; 
			String colour = door.getLockedDoorColour();
			if(colour.equals("g"))    {g.drawImage(greenLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else if(colour.equals("b")){ g.drawImage(blueLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else {							g.drawImage(redLockPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		}
		else if(tile instanceof Key) {
			Key key = (Key) tile;
			String colour = key.getKeyColour();
			if(colour.equals("g"))    { g.drawImage(greenKeyPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else if(colour.equals("b")){ g.drawImage(blueKeyPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
			else {							g.drawImage(redKeyPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		}
		else if(tile instanceof ExitLock) { g.drawImage(exitLockPNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof ExitTile) { g.drawImage(exitPNG, 	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof InfoField){ g.drawImage(helpPNG, 	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof Treasure) { g.drawImage(treasurePNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else if(tile instanceof WallTile) { g.drawImage(wallPNG,	onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
		else {								g.drawImage(freeTilePNG,onScreenX*TILE_SIZE, onScreenY*TILE_SIZE, TILE_SIZE, TILE_SIZE, null);}
	}
	
	private void drawChap(Graphics g) {
		String direction = null;
		if(chapsLatestMove != null) {
			direction = this.chapsLatestMove.getDirection();
		} else {
			direction = "down";
		}
		
		if(Board.getInfoTile()) {
			g.drawImage(helpPNG,(4*TILE_SIZE), (4*TILE_SIZE), TILE_SIZE, TILE_SIZE, null);
		} else {
			g.drawImage(freeTilePNG,(4*TILE_SIZE), (4*TILE_SIZE), TILE_SIZE, TILE_SIZE, null);
		}
			
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
	
	//=======================================================================
	// UTILITY METHODS
	//=======================================================================
	
	public static int boardSize() {
		return Board.getBoard()[0].length-1;
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
