package nz.ac.vuw.ecs.swen225.gp21.app;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;

import javax.swing.*;
import java.util.TimerTask;

public class App {
    InputMap inputs = new InputMap();
    public JLabel labelTime;
    Tile[][] boar;

    public void displayTime(){
        TimerTask task = new TimerTask() {
            final int seconds = 60;
            int j = 0;
            @Override
            public void run(){
                j++;
                if (j % seconds == 0) {
                    updateTime("Reset");
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


}
