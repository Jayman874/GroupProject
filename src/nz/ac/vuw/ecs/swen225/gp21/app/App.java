package nz.ac.vuw.ecs.swen225.gp21.app;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.recorder.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

public class App implements ActionListener, KeyListener {
    InputMap inputs = new InputMap();
    public JLabel labelTime;
    public  Tile[][] board;
    GUI gui = new GUI();

    public App(){
        gui.menuScreen();
        gui.drawBoard();
        gui.gameBoard();
        displayTime();

        //drawBoard();
    }

    public void displayTime(){
        TimerTask task = new TimerTask() {
            final int seconds = 60;
            int j = 0;
            @Override
            public void run(){
                j++;
                if (j % seconds == 0) {
                    updateTime("Out of time"); //Game is terminated and restarts from start of level 1
                    //need to implement the actual terminating of the game
                } else {
                    updateTime("Time left:" +(seconds - (j % seconds)));
                }
            }
        };
    }

    public void updateTime(String updateString) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                labelTime.setText(updateString);
            }
        });
    }

    public void drawBoard() {
        //Only printing in the terminal atm
        Tile[][] newBoard = Board.makeBoard();
        LoadLevel.printTiles(newBoard);
    }


    @Override
    public void keyTyped(KeyEvent e) {

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
        int c = e.getKeyCode();
        String str = Character.toString(i);
//		if(str.equals("CTRL-X")){
//			//exit game, don't save state
//		} else if(str.equals("CTRL-S")){
//			//exit game, save and start from this save next time app is opened
//		} else if(str.equals("CTRL-R")){
//			//resume a saved game by having a pop up a file selector to select a saved game to be loaded
//		} else if(str.equals("CTRL-1")){
//			//start level 1
//		} else if(str.equals("CTRL-2")){
//			//start level 2
//		} else if(str.equals("SPACE")){
//			//exit game
//		} else if(str.equals("ESC")){

        if (c == KeyEvent.VK_UP){
            Location newLoc = new Location(x, y - 1);
            Board.updateBoard(chap, newLoc);
            drawBoard();
        } else if(c == KeyEvent.VK_LEFT) {
            System.out.println("Hello");
            Location newLoc = new Location(x - 1, y);
            Board.updateBoard(chap, newLoc);
            System.out.println(chap.getLocation());
            drawBoard();
        } else if(c == KeyEvent.VK_RIGHT){

            Location newLoc = new Location(x + 1, y);
            drawBoard();
            Board.updateBoard(chap, newLoc);

        } else if(c == KeyEvent.VK_DOWN){
            Location newLoc = new Location(x, y + 1);
            Board.updateBoard(chap, newLoc);
            drawBoard();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new App();


    }



}
