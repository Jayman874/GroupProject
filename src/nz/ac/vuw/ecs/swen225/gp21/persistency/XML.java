import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;



public class XML {
    public static void main(String[] args) {
        try{

            String fileName = System.getProperty("user.dir") + "/src/test.xml";
            System.out.println(fileName);
            File inputFile = new File(fileName);
            SAXBuilder saxBuilder = new SAXBuilder();
            Document document = saxBuilder.build(inputFile);
            System.out.println("Root element :" + document.getRootElement().getName());
            Element classElement = document.getRootElement();

            List<Element> cellList = classElement.getChildren();
            System.out.println("----------------------------");

            for (int temp = 0; temp < cellList.size(); temp++) {
                Element cell = cellList.get(temp);
                System.out.println("\nCurrent Element :" + cell.getName());
                Attribute attribute = cell.getAttribute("id");
                String x = cell.getChild("x").getText();
                String y = cell.getChildText("y");
                String type = cell.getChildText("type");
                System.out.println("Cell type: " + type);
                System.out.println("X/Y: " + x + "/" + y);
            }

        }catch (JDOMException e){
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}
