package domWTX6LW1110;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.OutputKeys;                   //szükséges csomagok importálása
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


public class DomWriteWTX6LW {
	
	private static Node createUserElement(Document doc, String name,String value) {
		Element node = doc.createElement(name);
		
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}
	
	//Metódus, amely beállítja a gyerekelemeket
	private static Node create_User(Document doc, String id, String firstname, String lastname,String profession) {
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstname));
		user.appendChild(createUserElement(doc, "lastname", lastname));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	}
	
public static void main(String[] args)throws ParserConfigurationException, TransformerException {
		
		//DocumentumBuilder létrehozása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		
		Element root = doc.createElementNS("domwtx6lw", "users");
		
		doc.appendChild(root);
		
		//elemek feltöltése adatokkal
		root.appendChild(create_User(doc,"1","Pal","Kiss","programmer"));
		root.appendChild(create_User(doc,"2","Zold","Piroska","writer"));
		root.appendChild(create_User(doc,"3","Gordon","Alma","teacher"));
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		
		//szükséges átalaítások
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
		
		DOMSource source = new DOMSource(doc);
		
		//új file neve és kiterjesztése
		File myFile = new File("users1WTX6LW.xml");
		
		//kiirítás konzolra és fájlba
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		
		transf.transform(source, console);
		transf.transform(source, file);
	}
}
