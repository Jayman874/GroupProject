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
		JMenu record = new JMenu("Record");
		JMenuItem recordGame = new JMenuItem("Record Game");
		JMenuItem exitRecord = new JMenuItem("Exit Record");
		recordGame.addActionListener(this);
		exitRecord.addActionListener(this);
		record.add(recordGame);
		record.add(exitRecord);
		draw = new DrawPanel(this);
		draw.setPreferredSize(new Dimension(DrawPanel.VIEW_WINDOW*DrawPanel.TILE_SIZE, DrawPanel.VIEW_WINDOW*DrawPanel.TILE_SIZE));
		mb = new JMenuBar();
		mb.add(game);
		mb.add(options);
		mb.add(level);
		mb.add(help);
		mb.add(record);
		panel.add(draw);
		frame.add(panel);
		frame.setJMenuBar(mb);
		frame.setSize(1200, 900);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

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

	public void recordGame(){

		recorder.setBoard(board.getBoard());

		System.out.println("yoza");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch (action) {
			case "Record Game":
				recordGame();
				break;
			case "Exit Record":
				recorder.writeToFile();
				System.out.println("dopey harper");
				break;
		}
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
				if(chap.isValid(newLoc)){
					Move up = new Move(x, y, x, y-1, "up");
					Board.updateBoard(chap, newLoc);
					update(up);
				}
			} else if(c == KeyEvent.VK_LEFT) {
				Location newLoc = new Location(x - 1, y);
				if(chap.isValid(newLoc)){
					Move left = new Move(x, y, x - 1, y, "left");
					Board.updateBoard(chap, newLoc);
					update(left);
				}
			} else if(c == KeyEvent.VK_RIGHT){
				Location newLoc = new Location(x + 1, y);
				if(chap.isValid(newLoc)){
					Move right = new Move(x, y, x + 1, y, "right");
					Board.updateBoard(chap, newLoc);
					update(right);
				}
			} else if(c == KeyEvent.VK_DOWN){
				Location newLoc = new Location(x, y + 1);
				if(chap.isValid(newLoc)){
					Move down = new Move(x, y, x, y+1, "down");
					Board.updateBoard(chap, newLoc);
					update(down);
				}
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void update(Move move) {
			draw.update(move);
			recorder.addMove(move);
		}
	}






}
