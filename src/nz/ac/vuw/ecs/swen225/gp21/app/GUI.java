package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.persistency.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.*;

public class GUI extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame menuFrame;
	public JPanel panel;
	public static JMenuBar mb;

	//private boolean gameStarted = false;

	
	/**
	 * Setting up the menuScreen Frame
	 */
	public void menuScreen() {
		menuFrame = new JFrame("Menu Screen");
		JMenu menu = new JMenu("Menu");
		mb = new JMenuBar();
		mb.add(menu);
		menuFrame.setJMenuBar(mb);
		menuFrame.setSize(1200, 900);
		menuFrame.setVisible(true);
		menuFrame.setResizable(false);
	}
	
	/**
	 * Main Game Board Frame GUI
	 */
	public void gameBoard() {
		
	}

	/**
	 * Listens for button presses during the game
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
		
	/**
	 * Checks for key presses
	 * @param e
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




}
