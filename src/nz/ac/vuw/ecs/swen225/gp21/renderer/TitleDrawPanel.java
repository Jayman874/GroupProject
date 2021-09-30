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

/**
 * A class that draws the title image on the start screen.
 * Images drawn by Stefan Jenkins.
 *
 * @author stefanjenkins
 *
 */
public class TitleDrawPanel extends JPanel implements ActionListener{
	boolean titleOn = false;
	private Image titleOnPNG = loadImage("src/images/title.png");
	private Image titleOffPNG = loadImage("src/images/title_off.png");
	public final int SIZE = 500;
	private static final int DELAY = 3000;
	
	private final Timer timer;
	
	/**
	 * Constructor for TitleDrawPanel.
	 */
	public TitleDrawPanel() {
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	@Override
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer) {
			repaint();
		}
	}
	
	//=======================================================================
	// UTILITY METHODS
	//=======================================================================
	
	/**
	 * Loads an Image from a given path.
	 * @param filename - name of image file.
	 * @return Image from path.
	 */
	public static Image loadImage(String filename) {
		File imageFile = new File(filename);
		try {
			Image img = ImageIO.read(imageFile);
			return img;
		} catch (IOException e) {
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}

	
}
