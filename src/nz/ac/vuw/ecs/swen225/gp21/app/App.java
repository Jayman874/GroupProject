package nz.ac.vuw.ecs.swen225.gp21.app;

import nz.ac.vuw.ecs.swen225.gp21.domain.Board;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import nz.ac.vuw.ecs.swen225.gp21.renderer.Music;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class App implements ActionListener {
    InputMap inputs = new InputMap();
    public JLabel labelTime;
    public  Tile[][] board;

    GUI gui = new GUI();

    public App() throws InterruptedException {
        Music music = new Music();
        music.play();
        gui.startScreen();
        begin();
        //gui.levelOne();
        //nextLevelCheck();
        //gui.levelTwo();


    }

    public void begin() {
        while(true) {
            System.out.println("\n");
            if(gui.set == false) {
                break;
            }
            if(gui.boost == false) {
                System.exit(1);
            }
            if(gui.chap.finishedLevel) {
                gui.board = new Board();
            }
        }
    }

    public void nextLevelCheck() {
        while(true) {
            System.out.println("\n");
            if(gui.chap.finishedLevel) {
                break;
            }
        }
    }







    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action){
            //case
        }
    }

    public enum State {
        START, RUNNING, PAUSE, GAME_OVER;
    }

    public static void main(String[] args) throws InterruptedException {
        new App();


    }


}
