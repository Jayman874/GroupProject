package nz.ac.vuw.ecs.swen225.gp21.app;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.recorder.*;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;

import javax.swing.*;
import java.util.TimerTask;

public class App {
    InputMap inputs = new InputMap();
    public JLabel labelTime;
    public  Tile[][] board;
    GUI gui = new GUI();

    public App(){
        gui.menuScreen();
        gui.drawBoard();

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

    public static void main(String[] args) {
        new App();


    }


}
