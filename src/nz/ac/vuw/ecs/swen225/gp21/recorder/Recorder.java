package nz.ac.vuw.ecs.swen225.gp21.recorder;


import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Stack;

/**
 * Recorder class to handle the recording of a game.
 */
public class Recorder {

    private File saveFile; // xml file to write the recorded game to
    private Tile[][] board; // Field storing the current state of the board when record button is clicked
    private Stack<Move> moveStack; // stack to store the current moves made in game

    public Recorder () {
        this.saveFile = new File("");
        this.moveStack = new Stack<>();
    }

    /**
     * Main method to write run through the stack and write the contents to the xml save file.
     */
    public void writeToFile() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        Document doc = createLevelDoc();
        Document doc2 = createMoveDoc();
        FileOutputStream file = createSaveFile();
        try {
            xmlOutput.output(doc, file);
            xmlOutput.output(doc2, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to test the writing to file feature.
     */
    public void testWriteToFile() {
        board = new Tile[20][20];
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                board[i][j] = new FreeTile();
            }
        }
        // used for testing purposes
        board[0][0] = new Chap();
        board[0][1] = new ExitLock();
        board[1][1] = new ExitTile();
        board[1][0] = new InfoField("test");
        board[2][0] = new Key("red");
        board[2][1] = new Treasure();
        board[2][2] = new WallTile();
        board[0][2] = new Door("red");

        // used for testing purposes
        moveStack.push(new Move(0, 0, 1, 1, "down"));
        moveStack.push(new Move(1, 1, 1, 2, "down"));
        moveStack.push(new Move(1, 2, 2, 2, "right"));

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        Document doc = createLevelDoc();
        Document doc2 = createMoveDoc();
        FileOutputStream file = createSaveFile();
        try {
            xmlOutput.output(doc, file);
            xmlOutput.output(doc2, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new save file and returns it.
     * @return  Game save File
     */
    public FileOutputStream createSaveFile(){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/recorder/gameSave.xml";
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     *  Creates a Cell Element with the tile information stored.
     * @param x x location
     * @param y y location
     * @param type optional change in a Tile
     * @return  Element
     */
    public Element createCellElement(int x, int y, String type, String state, String colour){
        Element cellElement = new Element("cell");

        Element elementX = new Element("x");
        elementX.setText(Integer.toString(x));
        Element elementY = new Element("y");
        elementY.setText(Integer.toString(y));

        Element elementType = new Element("type");
        elementType.setText(type);

        cellElement.addContent(elementX);
        cellElement.addContent(elementY);
        cellElement.addContent(elementType);
        if(!state.equals("")) {
            Element elementState = new Element("state");
            elementState.setText(state);
            cellElement.addContent(elementState);
        }else if(!colour.equals("")){
            Element elementColour = new Element("colour");
            elementColour.setText(colour);
            cellElement.addContent(elementColour);
        }
        return cellElement;
    }

    /**
     * Creates a move element to be added to the save file.
     * @param moveNumber the move number
     * @param x1 x value of first cell
     * @param y1 y value of first cell
     * @param x2 x value of second cell
     * @param y2 y value of second cell
     * @return Move Element
     */
    public Element createMoveElement(int moveNumber, int x1, int y1, int x2, int y2) {
        Element moveElement = new Element("move" + moveNumber);

        Element firstCell = new Element("cell");
        Element firstElementX = new Element("x");
        firstElementX.setText(String.valueOf(x1));
        Element firstElementY = new Element("y");
        firstElementY.setText(String.valueOf(y1));

        Element secondCell = new Element("cell");
        Element secondElementX = new Element("x");
        secondElementX.setText(String.valueOf(x2));
        Element secondElementY = new Element("y");
        secondElementY.setText(String.valueOf(y2));

        firstCell.addContent(firstElementX);
        firstCell.addContent(firstElementY);

        secondCell.addContent(secondElementX);
        secondCell.addContent(secondElementY);

        moveElement.addContent(firstCell);
        moveElement.addContent(secondCell);

        return moveElement;
    }

    /**
     * Creates the map portion of the save file by running through the board.
     * @return  Document
     */
    public Document createLevelDoc(){
        try{
            Element mapElement = new Element("map");
            mapElement.setAttribute(new Attribute("size",Integer.toString(20)));
            Document doc = new Document(mapElement);

            for(int i = 0; i < board.length; i++){
                for(int j = 0; j < board.length; j++){
                    Tile currentTile = board[i][j];
                    String type = currentTile.toString();
                    String state = "";
                    String colour = "";
                    if((currentTile instanceof Door && ((Door) currentTile).isLocked()) || (currentTile instanceof ExitLock && ((ExitLock) currentTile).isLocked())) {
                        state = "locked";
                    }else if((currentTile instanceof Door && !((Door) currentTile).isLocked()) || (currentTile instanceof ExitLock && !((ExitLock) currentTile).isLocked())){
                        state = "unlocked";
                    }
                    if(currentTile instanceof Door) {
                        colour = ((Door) currentTile).getLockedDoorColour();
                    }else if(currentTile instanceof InfoField) {
                        state = ((InfoField) currentTile).displayText();
                    }
                    Element cell = createCellElement(i, j, type, state, colour);
                    doc.getRootElement().addContent(cell);
                }
            }
            return doc;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates the move portion of the save file by running through the move stack.
     * @return Document
     */
    public Document createMoveDoc() {
        try{
            Element mapElement = new Element("moves");
            Document doc = new Document(mapElement);
            int moveCount = 1;
            for(Move move : moveStack) {
                Element cell = createMoveElement(moveCount, move.getPreMoveX(), move.getPreMoveY(), move.getPostMoveX(), move.getPostMoveY());
                doc.getRootElement().addContent(cell);
                moveCount++;
            }
            return doc;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to be called when game is finished and recording needs to be saved.
     */
    public void saveGame() {
        if(!moveStack.isEmpty()) {
            this.writeToFile();
        }
    }

    /**
     * Stores the board into the field.
     * @param board the board to be stored
     */
    public void setBoard(Tile[][] board) {
        this.board = board;
    }

    /**
     * Method to be called every time a move is made, pushes a move to the moveStack.
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
     * Returns the stack where Moves are being stored.
     * @return Stack of Moves
     */
    public Stack<Move> getMoveStack() {
        return moveStack;
    }

    /**
     * Returns the board stored in the field.
     * @return  2d Array of Tiles
     */
    public Tile[][] getBoard() {
        return board;
    }

    public static void main(String[] args) {
        Recorder main = new Recorder();
        main.writeToFile();
    }
}
