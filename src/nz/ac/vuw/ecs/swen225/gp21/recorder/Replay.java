package nz.ac.vuw.ecs.swen225.gp21.recorder;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle reading a save file and replaying it on the game panel
 */
public class Replay {

    private int currentMoveNumber;
    private Tile[][] gameBoard;
    private List<Move> moves;
    public Board board;

    public Replay(Board board) {
        this.currentMoveNumber = 0;
        this.moves = new ArrayList<>();
        this.board = board;
    }

    /**
     * Method to handle the main scanning of file and calls helper methods to store information
     * inspiration taken from https://www.geeksforgeeks.org/java-program-to-extract-content-from-a-xml-document/
     */
    public void readSaveFile() {
        try {

            File file = new File(System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/recorder/gameSave.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            gameBoard = readBoardFromFile(doc);
            moves = readMovesFromFile(doc);

        }catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method to use the scanner to read the board from the save file
     * @param doc xml doc
     * @return 2d Tile array
     */
    public Tile[][] readBoardFromFile(Document doc) {
        NodeList cellNodeList = doc.getElementsByTagName("cell");
        Tile[][] tiles = new Tile[20][20];

        for(int i = 0; i < cellNodeList.getLength(); i++) {
            Node node = cellNodeList.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String xValue = element.getElementsByTagName("x").item(0).getTextContent();
                String yValue = element.getElementsByTagName("y").item(0).getTextContent();
                String tileType = element.getElementsByTagName("type").item(0).getTextContent();

                int x = Integer.parseInt(xValue);
                int y = Integer.parseInt(yValue);
                Tile tile = new FreeTile();
                if(tileType.equals("f")) {
                    tile = new FreeTile();

                }else if(tileType.equals("c")) {
                    tile = new Chap();

                }else if(tileType.equals("l")) {
                    String colour = element.getElementsByTagName("colour").item(0).getTextContent();
                    //String state = element.getElementsByTagName("state").item(0).getTextContent();
                    tile = new Door(colour);

                }else if(tileType.equals("q")) {
                    tile = new ExitLock();

                }else if(tileType.equals("e")) {
                    tile = new ExitTile();

                }else if(tileType.equals("i")) {
                    String info = element.getElementsByTagName("state").item(0).getTextContent();

                    tile = new InfoField(info);

                }else if(tileType.equals("k")) {
                    String colour = element.getElementsByTagName("colour").item(0).getTextContent();
                    tile = new Key(colour);

                }else if(tileType.equals("t")) {
                    tile = new Treasure();

                }else if(tileType.equals("w")) {
                    tile = new WallTile();

                }
                tiles[x][y] = tile;

            }
        }
        return tiles;
    }

    /**
     * Method to use the scanner to read the moves from the save file
     *
     * @return List of Moves
     */
    public List<Move> readMovesFromFile(Document doc) {
        NodeList cellNodeList = doc.getElementsByTagName("tile");
        List<Move> moves = new ArrayList<>();

        for(int i = 0; i < cellNodeList.getLength(); i += 2) {
            Node node1 = cellNodeList.item(i);
            Node node2 = cellNodeList.item(i+1);
            if (node1.getNodeType() == Node.ELEMENT_NODE) {
                Element element1 = (Element) node1;
                Element element2 = (Element) node2;

                String firstXValue = element1.getElementsByTagName("x").item(0).getTextContent();
                String firstYValue = element1.getElementsByTagName("y").item(0).getTextContent();

                String secondXValue = element2.getElementsByTagName("x").item(0).getTextContent();
                String secondYValue = element2.getElementsByTagName("y").item(0).getTextContent();

                Move move = new Move(Integer.parseInt(firstXValue), Integer.parseInt(firstYValue),
                        Integer.parseInt(secondXValue), Integer.parseInt(secondYValue), "");

                moves.add(move);
            }
        }

        return moves;
    }

    /**
     * Method to begin the replay  of a save file
     */
    public void beginReplay() {
        board.setBoard(gameBoard);
    }

    public static void main(String[] args) {
//        Replay r = new Replay();
//        r.readSaveFile();
    }
}
