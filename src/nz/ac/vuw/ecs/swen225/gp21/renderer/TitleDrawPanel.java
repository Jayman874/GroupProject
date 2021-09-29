package nz.ac.vuw.ecs.swen225.gp21.renderer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.Timer;

public class TitleDrawPanel extends JPanel{
	boolean titleOn = false;
	private Image titleOnPNG = DrawPanel.loadImage("src/image/title.png");
	private Image titleOffPNG = DrawPanel.loadImage("src/image/title_off.png");
	private final int SIZE = 500;
	private static final int DELAY = 2000;
	
	public TitleDrawPanel() {
		final Timer timer = new Timer(DELAY, new ActionListener() {
	        @Override
	        public void actionPerformed(final ActionEvent e) {
	            repaint();
	        }
	    });
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(titleOn) {
			g.drawImage(titleOnPNG, 0, 0, SIZE, SIZE, null);
		} else {
			g.drawImage(titleOffPNG, 0, 0, SIZE, SIZE, null);
		}
		titleOn = !titleOn;
	}
	
	
}
