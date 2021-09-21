package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.persistency.*;
import nz.ac.vuw.ecs.swen225.gp21.renderer.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.*;

public class GUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public JPanel panel;
	public Board board;
	public Chap chap;

	public static JMenuBar mb;
	private State state = State.START;


	DrawPanel draw;



//	private boolean gameStarted = false;
//	public GUI(){
//		switch(state){
//			case START:
//				startScreen();
//				break;
//			case RUNNING:
//				menuScreen();
//				break;
//				//actual game board
//
//		}
//
//	}
//
//	public static void main(String[] args) {
//		new GUI();
//	}



//	public void startScreen() {
//		JFrame openingScreen = new JFrame("Start");
//		JButton startButton = new JButton("Start Game");
//		JButton exitButton = new JButton("Exit Game");
//
//		startButton.addActionListener(this);
//		exitButton.addActionListener(this);
//		openingScreen.add(startButton);
//		openingScreen.add(exitButton);
//		startButton.setBounds(0, 100, 20, 20);
//		//exitButton.setBounds(100, 100, 20, 20);
//		openingScreen.setSize(900, 900);
//		openingScreen.setVisible(true);
//
//
//	}


	
	/**
	 * Setting up the menuScreen Frame
	 */
	public void menuScreen() {
		frame = new JFrame("Menu Screen");
		panel = new JPanel();
		board = new Board();

		JMenu game = new JMenu("Game");
		JMenu options = new JMenu("Options");
		JMenu level = new JMenu("Level");
		JMenu help = new JMenu("Help");

		draw = new DrawPanel(this);
		draw.setPreferredSize(new Dimension(DrawPanel.VIEW_WINDOW*DrawPanel.TILE_SIZE, DrawPanel.VIEW_WINDOW*DrawPanel.TILE_SIZE));

		mb = new JMenuBar();
		mb.add(game);
		mb.add(options);
		mb.add(level);
		mb.add(help);
		panel.add(draw);
		frame.add(panel);

		frame.setJMenuBar(mb);
		frame.setSize(1200, 900);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}



	/**
	 * Main Game Board Frame GUI
	 */
	public void gameBoard() {


	}

	public Chap findChap() {
		for(int i = 0; i < board.getBoard().length; i++) {
			for(int j = 0; j < board.getBoard().length; j++) {
				Tile[][] tiles = board.getBoard();
				Tile board = tiles[i][j];
				if(board instanceof Chap) {
					chap = (Chap) Board.getBoard()[i][j];
				}
			}
		}
		return chap;
	}

	public enum State {
		START, RUNNING, PAUSE, GAME_OVER;
	}




}
