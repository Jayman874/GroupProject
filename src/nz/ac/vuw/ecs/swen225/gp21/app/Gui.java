package nz.ac.vuw.ecs.swen225.gp21.app;

import java.awt.event.KeyEvent;

import javax.swing.*;

public class Gui {
	
	
	
	public static void main(String args[]) {
		//Creating the frame
		JFrame frame = new JFrame("Chip Vs Chap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400); //Need to work out actual dimensions
		JPanel panel = new JPanel();
		
		//Creating the Menubar
		JMenuBar mb = new JMenuBar();
		JMenu m1 = new JMenu("Game");
		JMenu m2 = new JMenu("Options");
		JMenu m3 = new JMenu("Level");
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
		
		//Displaying what level player is on
		
		
		
		//Setting up first keystrokes 
		KeyStroke keyStroke = KeyStroke.getKeyStroke("ctrl X");
	}
	
	public void keyPressed(KeyEvent e) {
		
	}

}
