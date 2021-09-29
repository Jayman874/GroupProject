package nz.ac.vuw.ecs.swen225.gp21.persistency;

import nz.ac.vuw.ecs.swen225.gp21.domain.*;
import org.jdom2.Attribute;
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
    static int mapSize;
    private final String LEVEL1INFO = "Grab all the correct keys to open the doors in order to get the treasures to open the exit lock!";
    private final String LEVEL2INFO = "Level 2!";

    public static void main(String[] args) {
        LoadLevel main = new LoadLevel();
        Tile[][] tiles = main.loadLevel("level1.xml");
        printTiles(tiles);
    }

    public Tile[][] loadLevel(String fileName){
        Map<Point, String> points = makeMap(fileName);
        Tile[][] tiles = makeTiles(points);
        return tiles;

    }

    public Tile[][] loadSave(String saveName){
        Map<Point, String> points = makeMap(saveName);
        Tile[][] tiles = makeTiles(points);
        return tiles;

    }

    public Map<Point, String> makeMap(String file){
        String fileName = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;
        File inputFile = new File(fileName);
        SAXBuilder saxBuilder = new SAXBuilder();

        Map<Point, String> cells = new HashMap<>();

        try {
            Document document = saxBuilder.build(inputFile); //create the Document from the input file
            Element classElement = document.getRootElement();
            Attribute attribute =  classElement.getAttribute("size");
            mapSize = attribute.getIntValue();

            List<Element> cellList = classElement.getChildren(); //construct list of all cel

            for(int i = 0; i < cellList.size(); i++){
                char colorChar;
                Element cell = cellList.get(i);
                int x = Integer.parseInt((cell.getChildText("x")).trim());
                int y = Integer.parseInt((cell.getChildText("y")).trim());
                String type = cell.getChildText("type"); //get the cell from collection and info about cell

                if(type.equals("l") || type.equals("k")){
                    String color = cell.getChildText("color");
                    System.out.println(color);
                    char[] colorCharArray = color.toCharArray();
                    colorChar = colorCharArray[0];
                }else{
                    colorChar = 'q';
                }
                type = type + colorChar;
                cells.put(new Point(x, y), type);
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cells;
    }

    public Tile[][] makeTiles(Map<Point, String> map){
        Tile[][] cells = new Tile[mapSize][mapSize];
        for(Map.Entry<Point, String> entry : map.entrySet()){
            Point point = entry.getKey();
            String string = entry.getValue();
            char[] charArr = string.toCharArray();
            char type = charArr[0];
            char col = charArr[1];

            Tile tile = getTileFromChar(type, col);

            int x = point.x;
            int y = point.y;
            tile.setLocation(new Location(x, y));

            //TODO
            //This is incorrect needs to be properly fixed in the future should be the other way around but inverting board when taht way
            cells[y][x] = tile;
        }
        return cells;
    }

    public Tile getTileFromChar(Character c, char col){
        String color = Character.toString(col);
        Tile tile = new Chap();
        if(c.equals('w')){
            tile = new WallTile();
        }else if(c.equals('f')){
            tile = new FreeTile();
        }else if(c.equals('k')){
            tile = new Key(color);
        }else if(c.equals('l')){
            tile = new Door(color);
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

    public static void printTiles(Tile[][] tiles){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                Tile tile = tiles[i][j];
                String tileString = tile.toString();
                char[] tileCharArr = tileString.toCharArray();
                System.out.print(tileCharArr[0] + " ");
            }
            System.out.println();
        }
    }
}
