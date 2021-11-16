package DOMWTX6LW1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;                       //sz�ks�ges csomagok import�l�sa
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class DomModifyWTX6LW {
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException  {
		File sourceFile = new File("src/carsWTX6LW.xml"); //haszn�lt f�jl
		
		//DocumentumBuilder l�trehoz�sa
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        Document document = dBuilder.parse(sourceFile);
        document.getDocumentElement().normalize();
        Modify(document);

	}
	
	//met�dus a konzolon val� megjelen�t�sre
    public static void Modify_File(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult console = new StreamResult(System.out);
        transformer.transform(source, console);
    }
    
    //M�dos�t� met�dus 
    public static void Modify(Document document) throws TransformerException {
    	NodeList nodeList = document.getElementsByTagName("supercars");
    	
    	//v�gigiter�l�s a list�n
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Node nNode = nodeList.item(i);
    		Element element = (Element) nNode;

    		//Ferrari gyerekelemek m�dos�t�sa
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
