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

    GUI gui = new GUI();
    public static State state = State.START; //Leave at this for now

    public App() throws InterruptedException {
        Music music = new Music();
        music.play();
        gui.startScreen();
        begin();
        gui.game();
        gui.displayTime();

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
        }
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
