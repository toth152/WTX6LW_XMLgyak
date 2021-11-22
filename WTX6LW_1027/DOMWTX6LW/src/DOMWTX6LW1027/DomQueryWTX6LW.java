package DOMWTX6LW1027;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;		//szükséges csomagok importálása
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomQueryWTX6LW {
	
	//A kért feltételt megkereső metódus
	public static void QueryLoad(Document doc) throws TransformerException {
		NodeList nodeList = doc.getElementsByTagName("supercars");

		//A listán for ciklussal megyünk végig
    	for (int i = 0; i < nodeList.getLength(); i++) {
    		Node nNode = nodeList.item(i);
    		Element element = (Element) nNode;
    		int db = element.getElementsByTagName("carname").getLength();
    		if(nNode.getNodeType() == Node.ELEMENT_NODE) {
    			
    			//Ha a Company megegyezik a Ferrari vagy Lamborgini kulcsszavakkal, akkor meghívja a szükséges függvényt
    			if(element.getAttribute("company").equals("Ferrari")) {
    				QueryWrite(db, doc, element);

    			}
    			if(element.getAttribute("company").equals("Lamborgini")) {
    				QueryWrite(db, doc, element);

    			}
    		}
    	}
		
	}
	
	//Kiíró (formázó) metódus a megfelelő megjelenítésért
	public static void QueryWrite(int db, Document doc, Element element) {
		String company = element.getAttribute("company");
		
		System.out.println("\nCurrent element:");
		System.out.println("supercarscompany: " + company);
		
		//végig iterálás a listán
		for (int i = 0; i < db; i++) {
			NodeList nodeList = element.getElementsByTagName("carname");
			Node nNode = nodeList.item(i);
			Element carElement = (Element) nNode;
	
			//A mintán látható módon fogjuk kiiratni az eredményt
			String name =  element.getElementsByTagName("carname").item(i).getTextContent();
			String type = carElement.getAttribute("type");
			System.out.println("car name: "+name+"\ncar type: "+type);
		}
	}
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException  {
		File xmlFile = new File("src/carsWTX6LW.xml"); //használandó fájl
        
		//A DocumentBuilderFactory-ból megkaptuk a DocumentBuildert.
		//A DocumentBuilder tartalmazza az API-t a DOM dokumentum példányok XML dokumentumból való beszerzéséből.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
        DocumentBuilder dBuilder = factory.newDocumentBuilder();
        
        //A parse() metódus elemzi az XML fájl
        Document doc = dBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();

        //Megkapjuk a dokumentum gyökérelemét.
        System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
        System.out.println("------------------------------");
        QueryLoad(doc);

	}

}
