package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TitleDrawPanel extends JPanel implements ActionListener{
	boolean titleOn = false;
	private Image titleOnPNG = loadImage("src/images/title.png");
	private Image titleOffPNG = loadImage("src/images/title_off.png");
	public final int SIZE = 500;
	private static final int DELAY = 3000;
	
	private final Timer timer;
	
	public TitleDrawPanel() {
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(titleOn) {
			g.drawImage(titleOnPNG, 0, 0, SIZE, SIZE, null);
			Audio.playSignOn();
		} else {
			g.drawImage(titleOffPNG, 0, 0, SIZE, SIZE, null);
		}
		titleOn = !titleOn;
	}
	
	public static Image loadImage(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		File imageFile = new File(filename);
		try {
			Image img = ImageIO.read(imageFile);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer) {
			repaint();
		}
	}
	
	
}
