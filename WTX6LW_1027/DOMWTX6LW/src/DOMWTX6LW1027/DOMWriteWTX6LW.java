package DOMWTX6LW1027;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class DOMWriteWTX6LW {
	
	public static void main(String[] args) throws ParserConfigurationException,
	TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		//A dokumentumk�sz��t� gy�rb�l egy new document builder j�n l�tre
		
		Document doc = builder.newDocument();
		// A dokumentum k�sz�t�b�l �j dokumentumot hozunk l�tre a newDocument()
		
		Element root = doc.createElementNS("domWTX6LW", "users");
		doc.appendChild(root);
		//L�trehozunk egy gy�k�relemet, �s hozz�adjuk a dokumentumhoz appendChild()
		
		root.appendChild(createUser(doc, "1", "Pal", "Kiss", "programmer"));
		root.appendChild(createUser(doc, "2", "Piroska", "Zold", "writer"));
		root.appendChild(createUser(doc, "3", "Alma", "Gordon", "teacher"));
		//A gy�k�relemhez h�rom gyermekelemet f�z�nk
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transf = transformerFactory.newTransformer();
		//A Java DOM a Transformert haszn�lja az XML f�jl l�trehoz�s�hoz.
		//Ezt transzform�tornak h�vj�k, mert XSLT nyelven is k�pes �talak�tani a dokumentumot
		//Most az XML f�jlba �runk
		
		transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		transf.setOutputProperty(OutputKeys.INDENT, "yes");
		transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		//Be�ll�tjuk a dokumentum k�dol�s�t �s beh�z�s�t
		
		DOMSource source = new DOMSource(doc);
		//Ez a DOMSource tartalmazza a DOM f�t
		
		File myFile = new File("users1.xml");
		
		StreamResult console = new StreamResult(System.out);
		StreamResult file = new StreamResult(myFile);
		//�rni fogunk egy konzolba �s egy f�jlba
		//StreamResult transzform�ci�s eredm�ny birtokosa
		
		transf.transform(source, console);
		transf.transform(source, file);
		//Az XML-forr�sokat a stream eredm�nyekhez �rjuk
	}
	
	private static Node createUser(Document doc, String id, String firstName, 
			String lastName, String profession) {
		
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc, "firstname", firstName));
		user.appendChild(createUserElement(doc, "lastname", lastName));
		user.appendChild(createUserElement(doc, "profession", profession));
		
		return user;
	}
	//A createUser()met�dusban �j felhaszn�l�i elem j�n l�tre createElement()
	//Az elem attrib�tuma-val van be�ll�tva setAttribute()
	
	private static Node createUserElement(Document doc, String name,
			String value) {
		
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
		}
	//Egy elem hozz�ad�dik a sz�l�j�hez az appendChild() �s sz�veges csom�pont j�n l�tre ezzel createTectNode()

}
