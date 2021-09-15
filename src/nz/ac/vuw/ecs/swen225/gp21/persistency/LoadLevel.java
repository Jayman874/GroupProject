package nz.ac.vuw.ecs.swen225.gp21.persistency;


import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LoadLevel {
    static final int mapSize = 5;

    public static void main(String[] args) {
        LoadLevel main = new LoadLevel();
        Map<Point, Character> points = main.makeMap("test.xml");
        //char[][] cells = main.makeBoard(points);
        Tile[][] tiles = main.makeTiles(points);
        printTiles(tiles);
        //printBoard(cells);
    }

    public Map<Point, Character> makeMap(String file){
        String fileName = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;
        File inputFile = new File(fileName);
        SAXBuilder saxBuilder = new SAXBuilder();

        Map<Point, Character> cells = new HashMap<>();

        try {
            Document document = saxBuilder.build(inputFile); //create the Document from the input file
            Element classElement = document.getRootElement();

            List<Element> cellList = classElement.getChildren(); //construct list of all cel

            for(int i = 0; i < cellList.size(); i++){
                Element cell = cellList.get(i);
                int x = Integer.parseInt((cell.getChildText("x")).trim());
                int y = Integer.parseInt((cell.getChildText("y")).trim());
                String type = cell.getChildText("type"); //get the cell from collection and info about cell
                char[] typeChar = type.toCharArray();


                cells.put(new Point(x, y), typeChar[0]);
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cells;
    }

    public char[][] makeBoard(Map<Point, Character> map){
        char[][] cells = new char[mapSize][mapSize];
        for(Map.Entry<Point, Character> entry : map.entrySet()){
            Point point = entry.getKey();
            char type = entry.getValue();
            int x = point.x;
            int y = point.y;

            cells[x][y] = type;
        }
        return cells;
    }

    public Tile[][] makeTiles(Map<Point, Character> map){
        Tile[][] cells = new Tile[mapSize][mapSize];
        for(Map.Entry<Point, Character> entry : map.entrySet()){
            Point point = entry.getKey();
            char type = entry.getValue();

            Tile tile = getTileFromChar(type);

            int x = point.x;
            int y = point.y;

            cells[x][y] = tile;
        }
        return cells;
    }

    public Tile getTileFromChar(Character c){
        Tile tile = new Chap();
        if(c.equals('w')){
            tile = new WallTile();
        }else if(c.equals('f')){
            tile = new FreeTile();
        }else if(c.equals('k')){
            tile = new Key("none");
        }else if(c.equals('l')){
            tile = new Door("none");
        }else if(c.equals('i')){
            tile = new InfoField("none");
        }else if(c.equals('t')){
            tile = new Treasure();
        }else if(c.equals('q')){
            tile = new ExitLock();
        }else if(c.equals('e')){
            tile = new ExitTile();
        }else if(c.equals('c')){ tile = new Chap();
        }
        return tile;
    }

    public static void printBoard(char[][] cells){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void printTiles(Tile[][] tiles){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                Tile tile = tiles[i][j];
                System.out.print(tile.toString() + " ");
            }
            System.out.println();
        }
    }


    public void getMapSize(){

    }
}
