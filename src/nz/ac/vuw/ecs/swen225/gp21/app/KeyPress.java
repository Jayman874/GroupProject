package nz.ac.vuw.ecs.swen225.gp21.app;


import java.awt.*;
import java.awt.event.*;

public class KeyPress extends Frame{
    Label label;
    TextField txtField;
    public static void main(String[] args) {
        KeyPress k = new KeyPress();
    }

    public KeyPress(){
        super("Key Press Event Frame");
        Panel panel = new Panel();
        label = new Label();
        txtField = new TextField(20);
        txtField.addKeyListener(new MyKeyListener());
        add(label, BorderLayout.NORTH);
        panel.add(txtField, BorderLayout.CENTER);
        add(panel, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });
        setSize(400,400);
        setVisible(true);
    }

    public class MyKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent ke){
            char i = ke.getKeyChar();
            String str = Character.toString(i);
            if(str.equals("CTRL-X")){
                //exit game, don't save state
            } else if(str.equals("CTRL-S")){
                //exit game, save and start from this save next time app is opened
            } else if(str.equals("CTRL-R")){
                //resume a saved game by having a pop up a file selector to select a saved game to be loaded
            } else if(str.equals("CTRL-1")){
                //start level 1
            } else if(str.equals("CTRL-2")){
                //start level 2
            } else if(str.equals("SPACE")){
                //exit game
            } else if(str.equals("ESC")){
                //exit game
            } else if(str.equals("UP")){
                //exit game
            } else if(str.equals("LEFT")){
                //exit game
            } else if(str.equals("RIGHT")){
                //exit game
            } else if(str.equals("DOWN")){
                //exit game
            }
        }
    }
}