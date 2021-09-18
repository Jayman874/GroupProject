package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
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
	 * Drawing board in terminal
	 */
	public void drawBoard() {
		JFrame board = new JFrame();
		Tile[][] newBoard = Board.makeBoard();
		LoadLevel.printTiles(newBoard);
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
	public void keyPressed(KeyEvent e){
		Chap chap = new Chap();
		Location loc = chap.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		char i = e.getKeyChar();
		String str = Character.toString(i);
		if(str.equals("CTRL-X")){
			//exit game, don't save state
		} else if(str.equals("CTRL-S")){
			//exit game, save and start from this save next time app is opened
		} else if(str.equals("CTRL-R")){
			//resume a saved game by having a pop up a file selector to select a saved game to be loaded
		} else if(str.equals("CTRL-1")){
			//start level 1
		} else if(str.equals("CTRL-2")){
			//start level 2
		} else if(str.equals("SPACE")){
			//exit game
		} else if(str.equals("ESC")){
			//exit game
		} else if(str.equals("UP")){
			Location newLoc = new Location(x, y - 1);
			Board.updateBoard(chap, newLoc);
			drawBoard();
		} else if(str.equals("LEFT")){
			Location newLoc = new Location(x - 1, y);
			Board.updateBoard(chap, newLoc);
			drawBoard();
		} else if(str.equals("RIGHT")){
			Location newLoc = new Location(x + 1, y);
			Board.updateBoard(chap, newLoc);
			drawBoard();
		} else if(str.equals("DOWN")){
			Location newLoc = new Location(x, y + 1);
			Board.updateBoard(chap, newLoc);
			drawBoard();
		}
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
