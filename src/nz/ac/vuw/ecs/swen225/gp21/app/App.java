package nz.ac.vuw.ecs.swen225.gp21.app;

import nz.ac.vuw.ecs.swen225.gp21.persistency.LoadLevel;
import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.renderer.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class App implements ActionListener {
    InputMap inputs = new InputMap();
    public JLabel labelTime;
    public  Tile[][] board;
    public int seconds = 60;
    GUI gui = new GUI();
    private State state = State.START; //Leave at this for now

    public App(){
        Music music = new Music();
        music.play();
        switch(state){
            case START:
                gui.startScreen();
                break;
            case RUNNING:
                gui.game();
        }
    }

    public void displayTime(){
        TimerTask task = new TimerTask() {

            int j = 0;
            @Override
            public void run(){
                j++;
                if (j % seconds == 0) {
                    updateTime("Out of time"); //Game is terminated and restarts from start of level 1
                    //need to implement the actual terminating of the game
                } else {
                    updateTime("Time left:" + (seconds - (j % seconds)));
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

    public int getSeconds(){
        return seconds;
    }




    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public enum State {
        START, RUNNING, PAUSE, GAME_OVER;
    }

    public static void main(String[] args) {
        new App();


    }


}
