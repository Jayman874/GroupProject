package src.nz.ac.vuw.ecs.swen225.gp21.persistency;

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
        Map<Point, Character> points = main.makeString("test.xml");
        char[][] cells = main.makeBoard(points);
        printBoard(cells);

    }

    public Map<Point, Character> makeString(String file){
        String fileName = System.getProperty("user.dir") + "/src/" + file;
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

    public static void printBoard(char[][] cells){
        for(int i = 0; i < mapSize; i++){
            for(int j = 0; j < mapSize; j++){
                System.out.print(cells[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void getMapSize(){

    }
}
