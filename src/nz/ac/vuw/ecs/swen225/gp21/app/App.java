package nz.ac.vuw.ecs.swen225.gp21.app;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.recorder.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.renderer.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.awt.*;

public class App implements ActionListener {
    InputMap inputs = new InputMap();
    public JLabel labelTime;
    public  Tile[][] board;
    //Graphics g;
    //App app = new App();
    GUI gui = new GUI();

    //DrawPanel draw = new DrawPanel(app);

    public App(){
        gui.menuScreen();
        Music music = new Music();
        music.play();
       // gui.drawBoard();
        //gui.gameBoard();
        //displayTime();
       //draw.paint(g);
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

    public static void drawBoard() {
        //Only printing in the terminal atm
        Tile[][] newBoard = Board.makeBoard();
        LoadLevel.printTiles(newBoard);
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new App();


    }


}
