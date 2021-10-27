package DOMWTX6LW1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadWTX6LW {
	public static void main(String argv[]) throws SAXException,
	IOException, ParserConfigurationException {

		File xmlFile = new File("src/DOMWTX6LW1027/usersWTX6LW.xml");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		//A DocumentBuilderFactory-ból megkaptjuk a DocumentBuildert.
		//A DocumentBuilder tartalmazza az API-t a DOM dokumentum példányok XML dokumentumból való beszerzésébõl

		Document doc = dBuilder.parse(xmlFile);
		//A parse() metódus elemzi az XML fájl a Document

		doc.getDocumentElement().normalize();

		System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
		//Megkapjuk a dokumentum gyökérelemét.

		NodeList nList = doc.getElementsByTagName("user");
		//A getElementByTagName() metódus segítségével megkapjuk a user eleme NodeListjét a dokumentumban.
		
		for (int i = 0; i < nList.getLength(); i++) {
			//A listán for ciklussal megyünk végig
			
			Node nNode = nList.item(i);
			
			System.out.println("\nCurrent Element: " + nNode.getNodeName());
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				Element elem = (Element) nNode;
				
				String uid = elem.getAttribute("id");
				// Az elem attribútumot a getAttribute() segítségével kapjuk meg
				
				Node nodel = elem.getElementsByTagName("firstname").item(0);
				String fname = nodel.getTextContent();
				
				Node node2 = elem.getElementsByTagName("lastname").item(0);
				String lname = node2.getTextContent();
				
				Node node3 = elem.getElementsByTagName("profession").item(0);
				String pname = node3.getTextContent();
				//Megkapjuk a user elem három alelemének szöveges tartalmát
				
				System.out.printf("User id: %s%n", uid);
				System.out.printf("First name: %s%n", fname);
				System.out.printf("Last name: %s%n", lname);
				System.out.printf("Profession: %s%n", pname);
			}
			
		}

	}
}