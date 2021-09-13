package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	private void drawGame(Graphics g) {
		drawTiles(g);
		drawChip(g);
		drawActor(g);
	}
	
	private void drawTiles(Graphics g) {
		//for(Tile tile : tilesOnScreen) {
		//	tile.drawTile(); }
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
		java.net.URL imageURL = DrawPanel.class.getResource(filename);
		try {
			Image img = ImageIO.read(imageURL);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
  }
}
