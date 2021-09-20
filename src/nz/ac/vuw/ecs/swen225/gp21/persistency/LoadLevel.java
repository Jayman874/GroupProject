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

    public static void main(String[] args) {
        LoadLevel main = new LoadLevel();
        Map<Point, String> points = main.makeMap("test2.xml");
        //char[][] cells = main.makeBoard(points);
        Tile[][] tiles = main.makeTiles(points);
        printTiles(tiles);
        //printBoard(cells);
    }

    public Map<Point, String> makeMap(String file){
        Tile[][] tiles = new Tile[mapSize][mapSize];
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

                if(type.equals("l") || type.equals("d")){
                    String color = cell.getChildText("color");
                    char[] colorCharArray = color.toCharArray();
                    colorChar = colorCharArray[0];
                    type = type + colorChar;
                }else{
                    colorChar = ' ';
                }

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

            cells[x][y] = tile;
        }
        return cells;
    }

    //need to include the color of the things in the infofields
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
}
