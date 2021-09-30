package nz.ac.vuw.ecs.swen225.gp21.persistency;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class WriteLevel {
    public static void main(String[] args) {
        WriteLevel main = new WriteLevel();
        editCellType("level1.xml", 0, 3, "a");

    }

    /**
     *
     * @param board board to be written to a file
     * @param saveName name of save file
     */
    public void createSave(Tile[][] board, String saveName){
        createSave(saveName, "f", board.length);
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board.length; y++){
                Tile tile = board[y][x];
                String type = tile.toString();

                editCellType(saveName, x, y, type);

            }
        }
    }

    /**
     *
     * @param levelName Name of level to be created
     * @param tileType Type of tile to populate level XML file as initiatlized
     * @param mapSize size of the map (squared)
     */
    public void createLevel(String levelName, String tileType, int mapSize){
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        Document doc = createLevelDoc(mapSize, tileType);
        FileOutputStream fo = createLevelFile(levelName);
        try {
            xmlOutput.output(doc, fo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Created " + levelName + " of size " + mapSize + " populated with " + tileType);
    }

    public void createSave(String levelName, String tileType, int mapSize){
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        Document doc = createLevelDoc(mapSize, tileType);
        FileOutputStream fo = createSaveFile(levelName);
        try {
            xmlOutput.output(doc, fo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Saved " + levelName);
    }

    /**
     *
     * @param file to be edited
     * @param pointX x coordinate of the tile to be edited
     * @param pointY y coordinate of the tile to be edited
     * @param info the information string to populate the infofield tile
     */
    public static void makeCellInfo(String file, int pointX, int pointY, String info){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;
        editCellType(file, pointX, pointY, "i");
        System.out.print(", info: " + info);

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path);
            Document jdomDoc = (Document) builder.build(xmlFile);

            Element rootElement = jdomDoc.getRootElement();
            List<Element> cellElements = rootElement.getChildren();
            for (int i = 0; i < cellElements.size(); i++) {
                Element cellElement = cellElements.get(i);

                int x = Integer.parseInt((cellElement.getChildText("x")).trim());
                int y = Integer.parseInt((cellElement.getChildText("y")).trim());;
                if(x == pointX && y == pointY){
                    Element infoElement = new Element("color");
                    infoElement.setText(info);
                    cellElement.addContent(info);
                }
            }
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(jdomDoc, new FileOutputStream(path));

        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param file the file to be edited
     * @param pointX x coordinate of the tile to be edited
     * @param pointY y coordinate of the tile to be edited
     * @param color Color of the key
     */
    public static void makeCellKey(String file, int pointX, int pointY, String color){
        editCellType(file, pointX, pointY, "k");
        System.out.print(", color: " + color);
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path);
            Document jdomDoc = (Document) builder.build(xmlFile);

            Element rootElement = jdomDoc.getRootElement();
            List<Element> cellElements = rootElement.getChildren();
            for (int i = 0; i < cellElements.size(); i++) {
                Element cellElement = cellElements.get(i);

                int x = Integer.parseInt((cellElement.getChildText("x")).trim());
                int y = Integer.parseInt((cellElement.getChildText("y")).trim());;
                if(x == pointX && y == pointY){
                    Element colorElement = cellElement.getChild("color");
                    colorElement.setText(color);
                }
            }
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(jdomDoc, new FileOutputStream(path));

        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param file the file to be edited
     * @param pointX x coordinate of the tile to be edited
     * @param pointY y coordinate of the tile to be edited
     * @param color Color of the door
     */
    public static void makeCellDoor(String file, int pointX, int pointY, String color){
        editCellType(file, pointX, pointY, "l");
        System.out.print(", color: " + color);
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;

        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path);
            Document jdomDoc = (Document) builder.build(xmlFile);

            Element rootElement = jdomDoc.getRootElement();
            List<Element> cellElements = rootElement.getChildren();
            for (int i = 0; i < cellElements.size(); i++) {
                Element cellElement = cellElements.get(i);

                int x = Integer.parseInt((cellElement.getChildText("x")).trim());
                int y = Integer.parseInt((cellElement.getChildText("y")).trim());;
                if(x == pointX && y == pointY){
                    Element colorElement = cellElement.getChild("color");
                    colorElement.setText(color);
                }
            }
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(jdomDoc, new FileOutputStream(path));

        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param file to be edited
     * @param pointX x coordinate of tile to be edited
     * @param pointY y coordinate of tile to be edited
     * @param newType The new type of the cell at (x, y)
     */
    public static void editCellType(String file, int pointX, int pointY, String newType){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;
        try {
            SAXBuilder builder = new SAXBuilder();
            File xmlFile = new File(path);
            Document jdomDoc = (Document) builder.build(xmlFile);

            Element rootElement = jdomDoc.getRootElement();
            List<Element> cellElements = rootElement.getChildren();
            for (int i = 0; i < cellElements.size(); i++) {
                Element cellElement = cellElements.get(i);

                int x = Integer.parseInt((cellElement.getChildText("x")).trim());
                int y = Integer.parseInt((cellElement.getChildText("y")).trim());;
                if(x == pointX && y == pointY){
                    cellElement.getChild("type").setText(newType);
                }
            }
            XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
            xmlOutputter.output(jdomDoc, new FileOutputStream(path));

        } catch(JDOMException e) {
            e.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        System.out.println("Editing cell at " + pointX + ", " + pointY + " to be of type: " + newType);
    }

    /**
     *
     * @param fileName name of file to be created
     * @return fileOutputStream
     */
    public FileOutputStream createLevelFile(String fileName){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + fileName;
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    public FileOutputStream createSaveFile(String saveName){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/Saves/" + saveName;
        FileOutputStream file = null;
        try {
            file = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     *
     * @param mapSize size of map
     * @param tileType type of tile to populate the map
     * @return Document (XML) of tile elements of type tileType
     */
    public Document createLevelDoc(int mapSize, String tileType){
        try{
            Element mapElement = new Element("map");
            mapElement.setAttribute(new Attribute("size",Integer.toString(mapSize)));
            Document doc = new Document(mapElement);

            for(int i = 0; i < mapSize; i++){
                for(int j = 0; j < mapSize; j++){
                    Element cell = createCellElement(i, j, tileType);
                    doc.getRootElement().addContent(cell);
                }
            }
            return doc;
        } catch(IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Element createCellElement(int x, int y, String type) throws Exception {
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

            Element elementColor = new Element("color");
            elementColor.setText("null");

            Element elementInfo = new Element("info");
            elementInfo.setText("null");

            cellElement.addContent(elementX);
            cellElement.addContent(elementY);
            cellElement.addContent(elementType);
            cellElement.addContent(elementColor);
            cellElement.addContent(elementInfo);

            return cellElement;
        }
    }
}
