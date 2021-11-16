package domWTX6LW1110;

import java.io.File;
import javax.xml.parsers.*;

import org.w3c.dom.*;							//szükséges csomagok importálása

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class DOMModifyWTX6LW {
	
	public static void main(String argv[]) {

	      try {
	         File inputFile = new File("src/nyelvekWTX6LW.xml"); //szükséges fájl
	         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         Document doc = docBuilder.parse(inputFile);
	         Node nyelv = doc.getElementsByTagName("szerver_nyelvek").item(0);
	         
	         NamedNodeMap attr = nyelv.getAttributes();
	         Node nodeAttr = attr.getNamedItem("ceg");
	         nodeAttr.setTextContent("Oracle");

	        
	         NodeList list = nyelv.getChildNodes();
	         
	         //végigmegyünk a listán
	         for (int temp = 0; temp < list.getLength(); temp++) {
	            Node node = list.item(temp);
	            if (node.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) node;
	               if ("nyelv_neve".equals(eElement.getNodeName())) {
	            	   
	            	   // ha a nyelv neve megegyezik az Oracle 01 névvel, akkor csere OOP-re
	            	   if("Oracle 01".equals(eElement.getTextContent()))
		                     eElement.setTextContent("OOP");
	                  
	            	   // ha a nyelv neve megegyezik az Oracle 02 névvel, akkor csere Document-oriented DB-re
	                   if("Oracle 02".equals(eElement.getTextContent()))
	                     eElement.setTextContent("Document-oriented DB");
	                  
	               		}
	               	}
	            }
	         
	         TransformerFactory transformerFactory = TransformerFactory.newInstance();
	         Transformer transformer = transformerFactory.newTransformer();
	         
	         //Eredmények kiiratása
	         DOMSource dsource = new DOMSource(doc);
	         System.out.println("-----------Modositott fajl-----------");
	         StreamResult consoleResult = new StreamResult(System.out);
	         transformer.transform(dsource, consoleResult);
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   }
	}
