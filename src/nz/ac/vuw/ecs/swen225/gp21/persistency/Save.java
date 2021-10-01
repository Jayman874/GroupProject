package nz.ac.vuw.ecs.swen225.gp21.persistency;

import nz.ac.vuw.ecs.swen225.gp21.domain.Door;
import nz.ac.vuw.ecs.swen225.gp21.domain.InfoField;
import nz.ac.vuw.ecs.swen225.gp21.domain.Key;
import nz.ac.vuw.ecs.swen225.gp21.domain.Tile;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Save {

    /**
     *
     * @param board the board to be saved
     * @return the name of the save file
     */
    public String createSave(Tile[][] board){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String saveName = "savetest";
        FileOutputStream fo = createSaveFile(saveName);
        Document doc = createSaveDoc(board.length, "f");

        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        try {
            xmlOutput.output(doc, fo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return(saveName);
    }

    /**
     *
     * @param board board to be populated
     * @param saveName name of save file
     */
    public void populateSave(Tile[][] board, String saveName){
        for(int x = 0; x < board.length; x++){
            for(int y = 0; y < board.length; y++){
                Tile tile = board[y][x];
                String type = tile.toString();
                String color = "null";
                String info = "null";
                if(tile instanceof Key){
                    Key key = (Key) tile;
                    color = key.getKeyColour();
                }

                if(tile instanceof Door){
                    Door door = (Door) tile;
                    color = door.getLockedDoorColour();
                }

                if(tile instanceof InfoField){
                    InfoField i = (InfoField) tile;
                    info =  i.displayText();
                }

                editCell(saveName, x, y, type, color, info);

            }
        }
    }

    /**
     *
     * @param file containing cell to be edited
     * @param pointX
     * @param pointY
     * @param newType
     */
    public static void editCell(String file, int pointX, int pointY, String newType, String newColor, String newInfo){
        String path = System.getProperty("user.dir") + "/src//nz/ac/vuw/ecs/swen225/gp21/persistency/Saves/" + file;
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
                    cellElement.getChild("color").setText(newColor);
                    cellElement.getChild("info").setText(newInfo);
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

    public Document createSaveDoc(int mapSize, String tileType){
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
