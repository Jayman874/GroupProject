package nz.ac.vuw.ecs.swen225.gp21.persistency;

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
import java.util.List;

public class WriteLevel {

    /**
     * Creates a level with the help of helper methods
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

    /**
     * Edit cell in an existing file
     * @param file containing the cell to be edited
     * @param pointX of cell to be edited
     * @param pointY of cell to be edited
     * @param newType new type of cell being edited
     * @param newColor newColor of cell to be edited
     * @param newInfo new information of cell to be edited
     */
    public static void editCell(String file, int pointX, int pointY, String newType, String newColor, String newInfo){
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

    /**
     * Create a FileOutputStream stream for level creation purposes
     * @param fileName name of file to be created
     * @return fileOutputStream for containment methods
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
     * Create a document for level creation purposes
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

    /**
     * Create a cell element (XML) containing passed information
     * @param x position of cell
     * @param y position of cell
     * @param type of the cell to be created
     * @return an XMLElement containing the correct information
     * @throws Exception
     */
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
