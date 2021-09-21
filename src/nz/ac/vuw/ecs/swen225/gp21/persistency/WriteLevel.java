package nz.ac.vuw.ecs.swen225.gp21.persistency;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteLevel {
    public static void main(String[] args) {
        WriteLevel main = new WriteLevel();

        main.createLevel("test6.xml", "w", 10);

    }

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

    /**
     *
     * @param file to be edited
     * @param pointX x coordinate of the tile to be edited
     * @param pointY y coordinate of the tile to be edited
     * @param info the information string to populate the infofield tile
     */
    public static void setCellInfo(String file, int pointX, int pointY, String info){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/levels/" + file;
        editCellType(file, pointX, pointY, "i");

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
                    Element colorElement = new Element("color");
                    colorElement.setText(color);
                    cellElement.addContent(colorElement);
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
                    Element colorElement = new Element("color");
                    colorElement.setText(color);
                    cellElement.addContent(colorElement);
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
