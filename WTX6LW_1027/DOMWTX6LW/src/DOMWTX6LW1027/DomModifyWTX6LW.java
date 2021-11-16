package DOMWTX6LW1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;                       //szükséges csomagok importálása
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomModifyWTX6LW {
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException  {
		File sourceFile = new File("src/carsWTX6LW.xml"); //használt fájl
		
		//DocumentumBuilder létrehozása
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document document = dBuilder.parse(sourceFile);
        document.getDocumentElement().normalize();
        Modify(document);

	}
	
	//metódus a konzolon való megjelenítésre
    public static void Modify_File(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }
    
    //Módosító metódus 
    public static void Modify(Document document) throws TransformerException {
    	NodeList nodeList = document.getElementsByTagName("supercars");
    	
    	//végigiterálás a listán
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Node nNode = nodeList.item(i);
    		Element element = (Element) nNode;

    		//Ferrari gyerekelemek módosítása
    		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
    			if(element.getAttribute("company").equals("Ferrari")) {
    				
    				Node x = element.getElementsByTagName("carname").item(i);
    				Node y = element.getElementsByTagName("carname").item(1);
    				x.setTextContent("Lamborghini 001");
    				y.setTextContent("Lamborghini 002");
    				
    				element.setAttribute("company", "Lamborghini");

    			}
    		}
			
		}
    				Modify_File(document);
    }
}
