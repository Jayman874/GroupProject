package nz.ac.vuw.ecs.swen225.gp21.recorder;


import java.io.File;
import java.util.Stack;

/**
 * Recorder class to handle the recording of a game
 */
public class Recorder {

    private File saveFile; // xml file to write the recorded game to
    private Stack<Move> moveStack; // stack to store the current moves made in game

    public Recorder () {
        this.saveFile = new File("");
        this.moveStack = new Stack<>();
    }

    /**
     * Main method to write run through the stack and write the contents to the xml save file
     */
    public void recordCurrentTurn() {

        for(Move move : moveStack) {

        }

    }

    /**
     * Pushes a move to the moveStack
     * @param move  Move to be added to stack
     */
    public void addMove(Move move) {
        this.moveStack.push(move);
    }

    /**
     * Returns the file the data is being written to.
     * @return  save File
     */
    public File getSaveFile() {
        return saveFile;
    }

    /**
     * Returns the stack where Moves are being stored
     * @return Stack of Moves
     */
    public Stack<Move> getMoveStack() {
        return moveStack;
    }

}
