package nz.ac.vuw.ecs.swen225.gp21.app;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Chap;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.Save;
import nz.ac.vuw.ecs.swen225.gp21.persistency.WriteLevel;
import nz.ac.vuw.ecs.swen225.gp21.persistency.levels.level2.Actor;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Move;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Recorder;
import nz.ac.vuw.ecs.swen225.gp21.recorder.Replay;
import nz.ac.vuw.ecs.swen225.gp21.renderer.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;


import javax.swing.*;

public class GUI extends JFrame implements ActionListener, PropertyChangeListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	public JFrame gameFrame;
	public JLabel time;
	public JPanel panel;
	public JFrame openingScreen;
	public JFrame pausedFrame;
	public JFrame rFrame;
	public static Board board;
	public Chap chap;
	public boolean set = true;
	public boolean boost = true;
	public boolean pause;
	public boolean restarted = true;
	public boolean replayStarted = false;
	JMenuItem stepbystep;
	long timePassed;


	public long secondsPassed;
	public static JDialog infoText, winText;

	public static JMenuBar mb;
	Replay replay;


	Recorder recorder = new Recorder();
	public DrawPanel draw;

	public GUI() throws InterruptedException {
		Music music = new Music();
		startScreen();
		begin();
		game();
		music.play();
		displayTime();
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

//	public void restart() {
//		while(true) {
//			System.out.println("\n");
//			if(restarted == false) {
//				break;
//			}
//		}
//	}

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
		//gameFrame.addPropertyChangeListener(chap.isLevelDone());
		setFocusable(true);
		time = new JLabel(String.valueOf(secondsPassed));
		JMenu game = new JMenu("Game");
		JMenu options = new JMenu("Options");
		JMenu level = new JMenu("Level");
		JMenu replayMen = new JMenu("Replay");
		JMenu record = new JMenu("Record");
		JMenuItem recordGame = new JMenuItem("Record Game");
		JMenuItem exitRecord = new JMenuItem("Exit Record");
		JMenuItem one = new JMenuItem("0.5");
		JMenuItem two = new JMenuItem("1");
		JMenuItem three = new JMenuItem("1.5");
		JMenuItem beginReplay = new JMenuItem("Begin Replay");
		stepbystep = new JMenuItem("Step by Step");
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		beginReplay.addActionListener(this);
		stepbystep.addActionListener(this);
		recordGame.addActionListener(this);
		exitRecord.addActionListener(this);
		record.add(recordGame);
		record.add(exitRecord);
		replayMen.add(one);
		replayMen.add(two);
		replayMen.add(three);
		replayMen.add(beginReplay);
		replayMen.add(stepbystep);
		draw = new DrawPanel();
		draw.setPreferredSize(new Dimension(DrawPanel.VIEW_WINDOW*DrawPanel.TILE_SIZE, DrawPanel.VIEW_WINDOW*DrawPanel.TILE_SIZE));
		mb = new JMenuBar();
		mb.add(game);
		mb.add(options);
		mb.add(level);
		mb.add(replayMen);
		mb.add(record);
		panel.add(time);
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
	}

	public void displayTime() throws InterruptedException {
		pause = true;
		//long displayMinutes = 0;
		long startTime = System.currentTimeMillis();
		System.out.println("Timer:");

		while(pause) {
			TimeUnit.SECONDS.sleep(1);
			long timePassed = System.currentTimeMillis() - startTime;
			secondsPassed = timePassed/1000; //Gets the seconds
			time.setText(String.valueOf(secondsPassed));
			if(secondsPassed == 10) {
				restartGame();
				break;
			}
			if(findChap().isLevelDone()){
				displayWin();
			}
			System.out.println(secondsPassed);
		}
	}

	public void restartGame() {
		rFrame = new JFrame();
		JDialog tDialog = new JDialog(rFrame, "Game is Over");
		JLabel l = new JLabel("Game is Over");
		tDialog.add(l);
		JButton restart = new JButton("Click Restart Game: Move to start again");
		restart.addActionListener(this);
		tDialog.add(restart);
		tDialog.setBounds(450, 450, 100, 100);
		tDialog.setSize(250, 250);
		tDialog.setVisible(true);
		chap.setStopMoving(true);

	}


	public long getSecondsPassed(){
		return secondsPassed;
	}

	public static void displayInfo(InfoField info) {
		JFrame infoB = new JFrame();
		infoText = new JDialog(infoB, "Info");
		JLabel label = new JLabel("Info", JLabel.CENTER);
		label.setText(info.displayText());
		infoText.add(label);
		infoText.setBounds(25, 80, 50, 50);
		infoText.setSize(750, 100);
		infoText.setVisible(true);
	}

	public static void disappearInfo() {
		infoText.setVisible(false);
	}

	public void displayWin() {
		JFrame winFrame = new JFrame();
		winText = new JDialog(winFrame, "You Win! Next Level");
		JButton button = new JButton("You Win! Next Level");
		button.addActionListener(this);
		winText.add(button);
		winText.setBounds(25, 80, 50, 50);
		winText.setSize(250, 100);
		winText.setVisible(true);
	}

	public static void disappearWin() {
		winText.setVisible(false);
	}

	public void paused() {
		pausedFrame = new JFrame("Paused");
		pause = false;
		JDialog pausedText = new JDialog(pausedFrame, "Paused");
		JButton resume = new JButton("Resume Game");
		resume.addActionListener(this);
		//pausedText.add(label);
		pausedText.add(resume);
		resume.setBounds(150, 150, 25, 25);
		pausedText.setBounds(500 , 350, 50, 50);
		pausedText.setSize(250, 100);
		pausedText.setVisible(true);
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
				break;
			case "Start Game":
				openingScreen.setVisible(false);
				set = false;
				if(chap.finishedLevel){
					board = new Board();
				}
				break;
			case "Click Restart Game: Move to start again":
				//restarted = false;
				rFrame.setVisible(false);
				Board.clearBoard(board.getBoard());
				chap.getTreasureInventory().clear();
				chap.getKeyInventory().clear();
				timePassed = 0;
				board = new Board();
				update(null);
				break;

			case "Resume Game":
				pause = true;

				pausedFrame.setVisible(false);

			case "Exit Game":
				openingScreen.setVisible(false);
				boost = false;
				break;
			case "0.5":
				replay.setReplaySpeed(0.5);
				System.out.println("bot");
				break;
			case "1":
				replay.setReplaySpeed(1);
				System.out.println("bastard");
				break;
			case "1.5":
				replay.setReplaySpeed(1.5);
				System.out.println("garteth");
				break;
			case "Begin Replay":
				replay = new Replay(board, this, draw);
				replayStarted = true;
				try {
					replay.beginAutoReplay();
				} catch (InterruptedException interruptedException) {
					interruptedException.printStackTrace();
				}
				break;
			case "Step by Step":
				if(!replayStarted) {
					replay = new Replay(board, this, draw);
					replayStarted = true;
				}

				replay.nextStepOfReplay();
				break;
			case "You Win! Next Level":
				System.out.println("fuckwit");
				disappearWin();
				board = new Board();
				break;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

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
				//System.exit(1);
				openingScreen.setVisible(true);
				//Exit game, start from last unfinished level
			} else if(e.isControlDown() && c == KeyEvent.VK_S) {
				//Exit game and game is saved
				Save save = new Save();
				String saveName = save.createSave(board.getBoard());
				save.populateSave(board.getBoard(), saveName);
			} else if(e.isControlDown() && c == KeyEvent.VK_R) {
				LoadLevel l = new LoadLevel();
				Tile[][] saveBoard = l.loadSave("saveTest");
				List<Key> keyList = findChap().getKeyInventory();
				for(Key k : keyList){
					String color = k.getKeyColour();
				}
				List<Treasure> treasureList = findChap().getTreasureInventory();
				Board board = new Board();
				board.setBoard(saveBoard);
				Chap newChap = findChap();
				for(Key k : keyList) {
					newChap.getKeyInventory().add(k);
					String color = k.getKeyColour();
				}
				for(Treasure t : treasureList) {
					newChap.getTreasureInventory().add(t);
				}

			} else if (e.isControlDown() && c == KeyEvent.VK_1) {
				//Start a new game from level 1
			} else if (e.isControlDown() && c == KeyEvent.VK_2) {
				//Start a new game from level 2
			}

			if (c == KeyEvent.VK_ESCAPE) {
				paused();
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

	public static void main(String[] args) throws InterruptedException {
		new GUI();
	}



}
