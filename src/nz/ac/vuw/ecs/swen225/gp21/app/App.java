package nz.ac.vuw.ecs.swen225.gp21.app;

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
    public int seconds = 60;
    GUI gui = new GUI();
    public static State state = State.START; //Leave at this for now

    public App() throws InterruptedException {
        Music music = new Music();
        music.play();
        gui.startScreen();
        begin();
        gui.game();
        exit();
        gui.displayTime();

    }

    public void begin() {
        while(true) {
            System.out.println("\n");
            if(gui.set == false) {
                break;
            }
        }
    }

    public void exit() {
        while(true) {
            System.out.println("\n");
            if(gui.boost == false) {
                break;
            }
        }
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

    public static void main(String[] args) throws InterruptedException {
        new App();


    }


}
