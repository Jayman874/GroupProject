package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.persistency.*;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Recorder;
import nz.ac.vuw.ecs.swen225.gp21.renderer.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;


import javax.swing.*;
import javax.swing.plaf.nimbus.State;

public class GUI extends JFrame implements ActionListener{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public JFrame gameFrame;

	public JPanel panel;
	public JFrame openingScreen;
	public static Board board;
	public Chap chap;
	public boolean set = true;
	public boolean boost = true;

	public long secondsPassed;
	public JDialog infoText;

	public static JMenuBar mb;


	Recorder recorder = new Recorder();
	DrawPanel draw;

	public GUI(){
		Music music = new Music();
		music.play();
		startScreen();
		begin();
		game();
		//gui.levelOne();
		//nextLevelCheck();
		//gui.levelTwo();
	}

	public void begin() {
		while(true) {
			System.out.println("\n");
			if(set == false) {
				break;
			}
			if(boost == false) {
				System.exit(1);
			}

		}
	}

	public void startScreen() {
		openingScreen = new JFrame("Start");
		JPanel openPanel = new JPanel();
		JButton startButton = new JButton("Start Game");
		JButton exitButton = new JButton("Exit Game");
		TitleDrawPanel tdp = new TitleDrawPanel();
		tdp.setPreferredSize(new Dimension(tdp.SIZE, tdp.SIZE));
		openPanel.add(tdp);
		startButton.addActionListener(this);
		exitButton.addActionListener(this);
		openPanel.add(startButton);
		openPanel.add(exitButton);
		openingScreen.add(openPanel);
		startButton.setBounds(0, 100, 20, 20);
		exitButton.setBounds(100, 100, 20, 20);
		openingScreen.setSize(900, 900);
		openingScreen.setVisible(true);
	}

	/**
	 * Setting up the menuScreen Frame
	 */
	public void game() {
		gameFrame = new JFrame("Little Big Game");
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
		gameFrame.add(panel);
		gameFrame.setJMenuBar(mb);
		gameFrame.setSize(1200, 900);
		gameFrame.setVisible(true);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);

	}

	public JFrame getGameFrame(){
		return gameFrame;

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

	public void displayTime() throws InterruptedException {
		boolean x = true;
		//long displayMinutes = 0;
		long startTime = System.currentTimeMillis();
		System.out.println("Timer:");
		while(x) {
			TimeUnit.SECONDS.sleep(1);
			long timePassed = System.currentTimeMillis() - startTime;
			 secondsPassed = timePassed/1000; //Gets the seconds
			if(secondsPassed == 60) {
				//System.out.println("Game Over!!!");
				JDialog tDialog = new JDialog(gameFrame, "dialog");
				tDialog.setBounds(450, 450, 100, 100);
				JLabel l = new JLabel("Game is Over");
				tDialog.add(l);
				tDialog.setSize(100, 100);
				tDialog.setVisible(true);
				break;
			}
			System.out.println(secondsPassed);
		}
	}
	public long getSecondsPassed(){
		return secondsPassed;
	}

	public void displayInfo(InfoField info) {
		infoText = new JDialog(gameFrame, "Info");
		JLabel label = new JLabel("Info", JLabel.CENTER);
		label.setText(info.displayText());
		infoText.add(label);
		infoText.setBounds(25, 80, 50, 50);
		infoText.setSize(250, 100);
		infoText.setVisible(true);
	}

	public void disappearInfo() {
		infoText.setVisible(false);
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
			case "Start Game":
				openingScreen.setVisible(false);
				set = false;
				if(chap.finishedLevel){
					board = new Board();
					System.out.println("hgijonsjod");
				}
				break;
			case "Exit Game":
				openingScreen.setVisible(false);
				boost = false;
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
			//char i = e.getKeyChar();
			int c = e.getKeyCode();
			//String str = Character.toString(i);
			if(e.isControlDown() && c == KeyEvent.VK_X) {
				System.out.println("Jewious");
				//Exit game, start from last unfinished level
			} else if(e.isControlDown() && c == KeyEvent.VK_S) {
				//Exit game and game is saved
			} else if(e.isControlDown() && c == KeyEvent.VK_R) {
				//Resume a saved game
			} else if (e.isControlDown() && c == KeyEvent.VK_1) {
				//Start a new game from level 1
			} else if (e.isControlDown() && c == KeyEvent.VK_2) {
				//Start a new game from level 2
			}

			if (c == KeyEvent.VK_UP){
				Location newLoc = new Location(x, y - 1);
				if(chap.isValid(newLoc)){
					Move up = new Move(x, y, x, y-1, "up");
					Board.updateBoard(chap, newLoc);
					Actor.updateActors(board.getActorList());
					update(up);
				}
			} else if(c == KeyEvent.VK_LEFT) {
				Location newLoc = new Location(x - 1, y);
				if(chap.isValid(newLoc)){
					Move left = new Move(x, y, x - 1, y, "left");
					Board.updateBoard(chap, newLoc);
					Actor.updateActors(board.getActorList());
					update(left);
				}
			} else if(c == KeyEvent.VK_RIGHT){
				Location newLoc = new Location(x + 1, y);
				if(chap.isValid(newLoc)){
					Move right = new Move(x, y, x + 1, y, "right");
					Board.updateBoard(chap, newLoc);
					Actor.updateActors(board.getActorList());
					update(right);
				}
			} else if(c == KeyEvent.VK_DOWN){
				Location newLoc = new Location(x, y + 1);
				if(chap.isValid(newLoc)){
					Move down = new Move(x, y, x, y+1, "down");
					Board.updateBoard(chap, newLoc);
					Actor.updateActors(board.getActorList());
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

	public enum State {
		START, RUNNING, PAUSE, GAME_OVER;
	}


	public static void main(String[] args) {
		new GUI();
	}



}
