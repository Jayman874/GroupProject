package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	public static final int TILE_SIZE = 50;
	
	private Image blueKey = loadImage("blue_key.png");
	private Image blueLock = loadImage("blue_lock.png");
	
	private Image greenKey = loadImage("green_key.png");
	private Image greenLock = loadImage("green_lock.png");
	
	private Image redKey = loadImage("red_key.png");
	private Image redLock = loadImage("red_lock.png");
	
	private Image freeTile = loadImage("free_tile.png");
	private Image help = loadImage("help.png");
	private Image wall = loadImage("wall.png");
	
	private Image exit = loadImage("exit.png");
	private Image exitLock = loadImage("exit_lock.png");
	private Image treasure = loadImage("treasure.png");
	
	public static String path = "src/images/";
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGame(g);
	}
	
	private void drawGame(Graphics g) {
		drawTiles(g);
		drawChip(g);
		drawActor(g);
	}
	
	private void drawTiles(Graphics g) {
		//for(Tile tile : tiles){
		//	if(isOnScreen(tile.x, tile.y)) draw tile
		//{
	}
	
	private boolean isOnScreen(int x, int y) {
		//chap.getPosition()
		//some sort of calculations 
		return true;
	}
	
	private void drawChip(Graphics g) {
		//char direction = getDirection();
		//switch(direction) {
		//	case 'u':
		//		g.drawImage(chip_up);
		//	case 'l':
		//		g.drawImage(chip_left);
		//	case 'r':
		//		g.drawImage(chip_right);
		//	case 'd':
		//		g.drawImage(chip_down); }
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
		File imageFile = new File(path + filename);
		try {
			Image img = ImageIO.read(imageFile);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}
	
	
//	public static void main (String [] args) {
//		DrawPanel dp = new DrawPanel();
//	}
}
