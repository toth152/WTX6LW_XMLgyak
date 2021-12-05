package hu.domparse.WTX6LW;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;     //szükséges csomagok importálása
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifyWTX6LW {
	
	public static void main(String[] args){
	
	try {
		File xmlFile = new File("src/XMLWTX6LW.xml");  //felhasznált XML fájl

		//DocumentumBuilder létrehozása
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
				
		//A parse() metódus elemzi az XML fájlt
		Document doc = dBuilder.parse(xmlFile);

		doc.getDocumentElement().normalize();
		
		
		//Végig iterálunk a konyv elemeken, a 240-es oldalszámúakat 300-assá módosítjuk,
		//majd a ,,A rút kiskacsa" címűt ,,A rút kiscsibe"-re módosítjuk
		
		//A getElementByTagName() metódus segítségével megkapjuk a könyv elem NodeListjét a dokumentumban.
		NodeList nList = doc.getElementsByTagName("Konyv");
		
		//A listán for ciklussal megyünk végig
		for(int i = 0; i < nList.getLength(); i++) {
			Node konyv = doc.getElementsByTagName("Konyv").item(i);
	        
	        NodeList list = konyv.getChildNodes();
	        
	        for (int temp = 0; temp < list.getLength(); temp++) {
	           Node node = list.item(temp);
	           if (node.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) node;
	              if ("Oldalszam".equals(eElement.getNodeName())) {
	                 if("240".equals(eElement.getTextContent())) {
	                    eElement.setTextContent("300");
	                 }
	              }
	              if ("Cim".equals(eElement.getNodeName())) {
		                 if("A rút kiskacsa".equals(eElement.getTextContent())) {
		                    eElement.setTextContent("A rút kiscsibe");
		                 }
		          }
	           }
	        }
		}
		
		//A kölcsönzők közül Haragos Hugó nevét Haragos Hedvig-re változtatjuk meg
		NodeList nList2 = doc.getElementsByTagName("Kolcsonzo");
		
		for(int i = 0; i < nList2.getLength(); i++) {
			Node kolcsonzo = doc.getElementsByTagName("Kolcsonzo").item(i);
	        
	        NodeList list2 = kolcsonzo.getChildNodes();
	        
	        for (int temp = 0; temp < list2.getLength(); temp++) {
	           Node node = list2.item(temp);
	           if (node.getNodeType() == Node.ELEMENT_NODE) {
	              Element eElement = (Element) node;
	              if ("Nev".equals(eElement.getNodeName())) {
	                 if("Haragos Hugó".equals(eElement.getTextContent())) {
	                	 eElement.setTextContent("Haragos Hedvig");
	                 }
	              }
	           }
	        }
		}
		//Könyvtárosoknak fizetés létrehozása egy alap 220k értékkel 
		NodeList nList3 = doc.getElementsByTagName("Konyvtaros");
		Element Konyvtaros = null;
		
		for(int temp = 0; temp < nList3.getLength(); temp++) {
			Konyvtaros = (Element) nList3.item(temp);
			Element salaryElement = doc.createElement("Fizetes");
			salaryElement.appendChild(doc.createTextNode("220000"));
			Konyvtaros.appendChild(salaryElement);
		}
		
		//A Termékeknél az ár mező törlése
		NodeList nList4 = doc.getElementsByTagName("Termek");
		Element Termek = null;
		
		for(int temp = 0; temp < nList4.getLength(); temp++) {
			Termek = (Element) nList4.item(temp);
			Node priceNode = Termek.getElementsByTagName("Ar").item(0);
			Termek.removeChild(priceNode);
		}
		
		
		System.out.println("Az adott kategóriákban módosított adatok:");
		System.out.println("\n1. módosítás: [Oldalszám: 240 -> Oldalszám: 300]");
		System.out.println("2. módosítás: [Cím: Rút kiskacsa -> Cím: Rút kiscsibe]");
		
		for (int i2 = 0; i2 < nList.getLength(); i2++) {
            Node nNode = nList.item(i2);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement2 = (Element) nNode;

                System.out.println("Könyv ID-ja: " + eElement2.getAttribute("ID"));

                System.out.println("Cím: " + eElement2.getElementsByTagName("Cim").item(0).getTextContent());
                
                System.out.println("Ár: " + eElement2.getElementsByTagName("Ar").item(0).getTextContent());
                
                System.out.println("Szerző: " + eElement2.getElementsByTagName("Szerzo").item(0).getTextContent());
                
                System.out.println("Oldalszám: " + eElement2.getElementsByTagName("Oldalszam").item(0).getTextContent());

            }
        }
		System.out.println("\n3. módosítás: [Név: Haragos Hugó -> Név: Haragos Hedvig]");
		
		for (int i = 0; i < nList2.getLength(); i++) {
            Node nNode = nList2.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                System.out.println("Kölcsönző ID-ja: " + eElement.getAttribute("ID"));

                System.out.println("Név: " + eElement.getElementsByTagName("Nev").item(0).getTextContent());

                System.out.println("Cím: "+"\n\tVáros: " + eElement.getElementsByTagName("Varos").item(0).getTextContent()+"\n\tUtca: "
                										+ eElement.getElementsByTagName("Utca").item(0).getTextContent()+"\n\tHázszám: "
                										+ eElement.getElementsByTagName("Hazszam").item(0).getTextContent());

                System.out.println("Elérhetõség: "+"\n\tTel: " + eElement.getElementsByTagName("Tel").item(0).getTextContent()+"\n\tEmail: "
						+ eElement.getElementsByTagName("Email").item(0).getTextContent());
            }
        }
		
		System.out.println("\n4. módosítás: [Alap fizetés beállítása a könyvtárosoknak]");
		
		for (int i = 0; i < nList3.getLength(); i++) {
            Node nNode = nList3.item(i);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement3 = (Element) nNode;

                System.out.println("Könyvtáros ID-ja: " + eElement3.getAttribute("ID"));

                System.out.println("Név: " + eElement3.getElementsByTagName("Nev").item(0).getTextContent());

                System.out.println("Elérhetõség: "+"\n\tTel: " + eElement3.getElementsByTagName("Tel").item(0).getTextContent()+"\n\tEmail: "
						+ eElement3.getElementsByTagName("Email").item(0).getTextContent());
                
                System.out.println("Kituntetes: "+"\n\tElső: " + eElement3.getElementsByTagName("Elso").item(0).getTextContent()+"\n\tMásodik: "
						+ eElement3.getElementsByTagName("Masodik").item(0).getTextContent());
                
                System.out.println("Fizetés: " + eElement3.getElementsByTagName("Fizetes").item(0).getTextContent());
            }
        }
		
		System.out.println("\n5. módosítás: [Termék gyerekelemből Ár törlése]");
		
		for (int i2 = 0; i2 < nList4.getLength(); i2++) {
            Node nNode = nList4.item(i2);

            System.out.println("\nAktuális elem: " + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement4 = (Element) nNode;

                System.out.println("Termék ID-ja: " + eElement4.getAttribute("ID"));

                System.out.println("Név: " + eElement4.getElementsByTagName("Nev").item(0).getTextContent());
                
            }
        }
        
        
        //Módosított XML kiírása a konzolra
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        System.out.println("\n-----------A teljes módosított XML dokumentum-----------");
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
		
	    }catch (Exception e) {
			e.printStackTrace();
	    }
	}

}
