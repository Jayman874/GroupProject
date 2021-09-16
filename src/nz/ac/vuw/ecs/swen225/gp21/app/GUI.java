package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.persistency.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

import javax.swing.*;

public class GUI extends JFrame implements ActionListener, KeyListener {

	public GUI(){
		menuScreen();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public JPanel panel;
	public static JMenuBar mb;

	//private boolean gameStarted = false;
	
	
	/**
	 * Setting up the menuScreen Frame
	 */
	public void menuScreen() {
		frame = new JFrame("Chip Vs Chap");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		mb = new JMenuBar();
		JMenuItem m1 = new JMenuItem("Game");
		JMenuItem m2 = new JMenuItem("Options");
		JMenuItem m3 = new JMenuItem("Level");
		mb.add(m1);
		mb.add(m2);
		mb.add(m3);
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

	public static void main(String[] args) {
		new GUI();
	}



}
