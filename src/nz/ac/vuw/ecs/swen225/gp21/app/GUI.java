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

public class GUI extends JFrame implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public JPanel panel;
	public Board board;
	public Chap chap;

	public static JMenuBar mb;



	DrawPanel draw;



	//private boolean gameStarted = false;
	public GUI(){
		menuScreen();
		findChap();
		//draw.paint(g);
		//drawBoard();

	}

	public static void main(String[] args) {
		new GUI();
	}
	
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
		draw.setPreferredSize(new Dimension(500, 500));

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
	}

	/**
	 * Drawing board in terminal
	 */
	public void drawBoard() {
		//Only printing in the terminal atm
		Tile[][] newBoard = Board.makeBoard();
		LoadLevel.printTiles(newBoard);
	}

	/**
	 * Main Game Board Frame GUI
	 */
	public void gameBoard() {
//		JButton upButton = new JButton("W");
//		JButton leftButton = new JButton("A");
//		JButton downButton = new JButton("S");
//		JButton rightButton = new JButton("D");
//		upButton.addKeyListener(this);
//		leftButton.addKeyListener(this);
//		downButton.addKeyListener(this);
//		rightButton.addKeyListener(this);
//		upButton.setBounds(250, 545, 50, 50);
//		downButton.setBounds(250, 595, 50, 50);
//		leftButton.setBounds(200, 570, 50, 50);
//		rightButton.setBounds(300, 570, 50, 50);
//		frame.add(upButton);
//		frame.add(downButton);
//		frame.add(leftButton);
//		frame.add(rightButton);

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
		Chap chap = findChap();
		Location loc = chap.getLocation();
		int x = loc.getX();
		int y = loc.getY();
		char i = e.getKeyChar();
		int c = e.getKeyCode();
		//String str = Character.toString(i);
		if(c == KeyEvent.VK_CONTROL) {

		}
		if (c == KeyEvent.VK_UP){
			Location newLoc = new Location(x, y - 1);
			if(chap.isValid(newLoc)) {
				Board.updateBoard(chap, newLoc);
				drawBoard();
			}
		} else if(c == KeyEvent.VK_LEFT) {
			Location newLoc = new Location(x - 1, y);
			if(chap.isValid(newLoc)){
				Board.updateBoard(chap, newLoc);
				drawBoard();
			}
		} else if(c == KeyEvent.VK_RIGHT){
			Location newLoc = new Location(x + 1, y);
			if(chap.isValid(newLoc)) {
				Board.updateBoard(chap, newLoc);
				drawBoard();
			}
		} else if(c == KeyEvent.VK_DOWN){
			Location newLoc = new Location(x, y + 1);
			if(chap.isValid(newLoc)){
				Board.updateBoard(chap, newLoc);
				drawBoard();
			}
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
