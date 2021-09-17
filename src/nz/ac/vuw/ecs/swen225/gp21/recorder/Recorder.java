package nz.ac.vuw.ecs.swen225.gp21.recorder;


import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import nz.ac.vuw.ecs.swen225.gp21.persistency.WriteLevel;
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
 * Recorder class to handle the recording of a game
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
        board = new Tile[20][20];
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                board[i][j] = new FreeTile();
            }
        }
        board[0][0] = new Chap();
        board[0][1] = new ExitLock();
        board[1][1] = new ExitTile();
        board[1][0] = new InfoField("test");
        board[2][0] = new Key("red");
        board[2][1] = new Treasure();
        board[2][2] = new WallTile();
        board[0][2] = new Door("red");

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        Document doc = createLevelDoc();
        FileOutputStream file = createSaveFile();
        try {
            xmlOutput.output(doc, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for(Move move : moveStack) {
//
//        }

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
     *  Creates a Cell Element with the tile information stored
     * @param x x location
     * @param y y location
     * @param type optional change in a Tile
     * @return  Element
     * @throws Exception Exception to be thrown if change if not one string
     */
    public Element createCellElement(int x, int y, String type, String state, String colour) throws Exception {
        if(type.toCharArray().length != 1){
            throw new Exception("type must be string of length 1");
        }else{
            Element cellElement = new Element("cell");

            //supercars element
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
    }

    /**
     * Creates the map portion of the save file by running through the board
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
     * Pushes a move to the moveStack.
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

    /**
     * Returns the board stored in the field
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
