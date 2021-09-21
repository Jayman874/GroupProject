package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.persistency.*;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Recorder;
import nz.ac.vuw.ecs.swen225.gp21.renderer.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.*;

public class GUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public JPanel panel;
	public Board board;
	public Chap chap;

	public static JMenuBar mb;


	Recorder recorder = new Recorder();
	DrawPanel draw;

	InputMap inputs = new InputMap();
	ActionMap actions = new ActionMap();

	public void startScreen() {
		JFrame openingScreen = new JFrame("Start");
		JButton startButton = new JButton("Start Game");
		JButton exitButton = new JButton("Exit Game");

		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		openingScreen.add(startButton);
		openingScreen.add(exitButton);
		startButton.setBounds(0, 100, 20, 20);
		exitButton.setBounds(100, 100, 20, 20);
		openingScreen.setSize(900, 900);
		openingScreen.setVisible(true);


	}


	
	/**
	 * Setting up the menuScreen Frame
	 */
	public void game() {
		frame = new JFrame("Little Big Game");
		panel = new JPanel();
		board = new Board();
		panel.setFocusable(true);
		KeyListener listener = new MyKeyListener();
		panel.addKeyListener(listener);
		setFocusable(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	public class MyKeyListener implements InputUpdate, KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		/**
		 * Checks for key presses
		 * @param e
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("Hello");
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
				//System.out.println("Hello");
				Move upMove = new Move(x, y, x, y-1, "up");
				update(upMove);
				Location newLoc = new Location(x, y - 1);
				if(chap.isValid(newLoc)){

					Move up = new Move(x, y, x, y-1, "up");
					//update(down);
					//Board.
					Board.updateBoard(chap, newLoc);
					draw.update(up);
				}
			} else if(c == KeyEvent.VK_LEFT) {
				//System.out.println("Hello");
				Move leftMove = new Move(x, y, x - 1, y, "left");
				update(leftMove);
				Location newLoc = new Location(x - 1, y);
				if(chap.isValid(newLoc)){

					Move left = new Move(x, y, x - 1, y, "left");
					//update(down);
					//Board.
					Board.updateBoard(chap, newLoc);
					draw.update(left);
				}
			} else if(c == KeyEvent.VK_RIGHT){
				//System.out.println("Hello");
				Move rightMove = new Move(x, y, x + 1, y, "right");
				update(rightMove);
				Location newLoc = new Location(x + 1, y);
				if(chap.isValid(newLoc)){

					Move right = new Move(x, y, x + 1, y, "right");
					//update(down);
					//Board.
					Board.updateBoard(chap, newLoc);
					draw.update(right);
				}
			} else if(c == KeyEvent.VK_DOWN){
				//System.out.println("Hello");
				Move downMove = new Move(x, y, x, y+1, "down");
				update(downMove);
				Location newLoc = new Location(x, y + 1);
				if(chap.isValid(newLoc)){

					Move down = new Move(x, y, x, y+1, "down");
					//update(down);
					//Board.
					Board.updateBoard(chap, newLoc);
					draw.update(down);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
		}

		@Override
		public void update(Move move) {
			draw.update(move);
			recorder.addMove(move);
		}
	}






}
