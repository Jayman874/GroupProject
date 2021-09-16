package nz.ac.vuw.ecs.swen225.gp21.persistency;
import org.jdom2.*;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.print.Doc;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteLevel {
    public static void main(String[] args) {
        WriteLevel main = new WriteLevel();
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());

        Document doc = main.createLevelDoc(3, "w");
        FileOutputStream file = main.createLevelFile("test1");
        try {
            xmlOutput.output(doc, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


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

    public Document createDocFromRegions(List<List<Element>> regions){
        Element mapElement = new Element("map");
        Document doc = new Document(mapElement);

        for(int i = 0; i < regions.size(); i++){
            List<Element> region = regions.get(i);
            for(int j = 0; j < region.size(); j++){
                Element cell = region.get(j);
                doc.getRootElement().addContent(cell);
            }
        }
        return doc;
    }

    public List<Element> createRegion(int xFrom, int xTo, int yFrom, int yTo, String tileType){
        int xDiff = Math.abs(xTo - xFrom);
        int yDiff = Math.abs(yTo - yFrom);
        try{
            List<Element> region = new ArrayList<>();

            for(int x = 0; x < xDiff; x++){
                for(int y = 0; y < yDiff; y++){
                    Element cell = createCellElement(x, y, tileType);
                    region.add(cell);
                }
            }
            return region;
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

            cellElement.addContent(elementX);
            cellElement.addContent(elementY);
            cellElement.addContent(elementType);

            return cellElement;
        }
    }
}
