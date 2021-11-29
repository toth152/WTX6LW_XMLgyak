package hu.domparse.WTX6LW;

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
	
	public static void main(String[] args) {
		//Az esetleges keletkezõ hibák lekezelése try-catch blokkban
				try {
		//Az olvasandó fájl megadása
					 File xmlFile = new File("src/XMLWTX6LW.xml");
					 
		//Az XML fájl átalakítása DOM objektumokká
				        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				        DocumentBuilder dBuilder = factory.newDocumentBuilder();
				        Document doc = dBuilder.parse(xmlFile);
		// Node-ok normalizálása
				        doc.getDocumentElement().normalize();
		//A gyökér elem nevének kiolvasása és kiíratása konzolra		        
				        System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
		//A gyökér elem alatt fellelhetõ gyermek/testvér elemek listába illesztése
				        NodeList nList1 = doc.getElementsByTagName("Kolcsonzo");
				        NodeList nList2 = doc.getElementsByTagName("Kolcsonzes");
				        NodeList nList3 = doc.getElementsByTagName("Konyv");
				        NodeList nList4 = doc.getElementsByTagName("Kezeles");
				        NodeList nList5 = doc.getElementsByTagName("Konyvtaros");
				        NodeList nList6 = doc.getElementsByTagName("Beszerzes");
				        NodeList nList7 = doc.getElementsByTagName("Termek");
				        NodeList nList8 = doc.getElementsByTagName("Gyartas");
				        NodeList nList9 = doc.getElementsByTagName("Gyarto");
				        System.out.println("----------------------------------------");

		//Ciklus segítségével végignézzük az aktuális node lista elemeit
				        for (int i = 0; i < nList1.getLength(); i++) {
		//Az egyes lista elemeket meghatározzuk node ként
				            Node nNode = nList1.item(i);
		//Ha egyezést talál akkor Kiírjuk az adott elemhez tartozó nevet és adatokat is
				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				   //A node-ot elemként definiáljuk
				            	Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());
				        // Az aktuális elemhez tartozó aazonosító letárolása egy String adattípusban
				                String id = elem.getAttribute("ID");
				        // Az adott elem gyermekelem értékének eltárolása String adattíõusban
				                Node node1 = elem.getElementsByTagName("Nev").item(0);
				                String nev = node1.getTextContent();
				                
				                Node node2_1 = elem.getElementsByTagName("Varos").item(0);
				                Node node2_2 = elem.getElementsByTagName("Utca").item(0);
				                Node node2_3 = elem.getElementsByTagName("Hazszam").item(0);
				                String varos = node2_1.getTextContent();
				                String utca = node2_2.getTextContent();
				                String hazszam = node2_3.getTextContent();
				                
				                Node node3_1 = elem.getElementsByTagName("Tel").item(0);
				                Node node3_2 = elem.getElementsByTagName("Email").item(0);
				                String tel = node3_1.getTextContent();
				                String email = node3_2.getTextContent();

				                // Az eltárolt értékek kiíratása
				                System.out.println("Kölcsönző ID-ja: " + id);
				                System.out.println("Név: " + nev);
				                System.out.println("Lakhely: ");
				                System.out.println("\tVáros: "+varos);
				                System.out.println("\tUtca: "+utca);
				                System.out.println("\tHázszám: "+hazszam);
				                System.out.println("Elérhetõség: ");
				                System.out.println("\tTel: "+tel);
				                System.out.println("\tEmail: "+email);

				            }
				        }
		// A továbbiakban minden listához ezt a megoldást alkalmazzuk		        
				        for (int i = 0; i < nList2.getLength(); i++) {
				            Node nNode = nList2.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id1 = elem.getAttribute("K1ID");
				                String id2 = elem.getAttribute("K2ID");
				                
				                Node node1 = elem.getElementsByTagName("Datum").item(0);
				                String datum = node1.getTextContent();
				                
				                Node node2 = elem.getElementsByTagName("Helyszin").item(0);
				                String helyszin = node2.getTextContent();

				                System.out.println("Kölcsönző ID-ja: " + id1);
				                System.out.println("Könyv azonosítója: " + id2);
				                System.out.println("Dátum: "+datum);
				                System.out.println("Helyszín: "+helyszin);

				            }
				        }
				        
				        for (int i = 0; i < nList3.getLength(); i++) {
				            Node nNode = nList3.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id = elem.getAttribute("ID");

				                Node node1 = elem.getElementsByTagName("Cim").item(0);
				                String cim = node1.getTextContent();
				                
				                Node node2 = elem.getElementsByTagName("Ar").item(0);
				                String ar = node2.getTextContent();
				                
				                Node node3 = elem.getElementsByTagName("Szerzo").item(0);
				                String szerzo = node3.getTextContent();
				                
				                Node node4 = elem.getElementsByTagName("Oldalszam").item(0);
				                String oldalszam = node4.getTextContent();


				                System.out.println("Könyv ID-ja: " + id);
				                System.out.println("Cím: " + cim);
				                System.out.println("Ár: "+ar);
				                System.out.println("Szerző: "+szerzo);
				                System.out.println("Oldalszám: "+oldalszam);
					            }
				        }
				        
				        for (int i = 0; i < nList4.getLength(); i++) {
				            Node nNode = nList4.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id2 = elem.getAttribute("K2ID");
				                String id3 = elem.getAttribute("K3ID");

				                System.out.println("Könyv ID-ja: " + id2);
				                System.out.println("Könyvtáros ID-ja: " + id3);

				            }
				        }
				        
				        for (int i = 0; i < nList5.getLength(); i++) {
				            Node nNode = nList5.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id = elem.getAttribute("ID");

				                Node node1 = elem.getElementsByTagName("Nev").item(0);
				                String nev = node1.getTextContent();
				                
				                Node node2_1 = elem.getElementsByTagName("Tel").item(0);
				                Node node2_2 = elem.getElementsByTagName("Email").item(0);
				                String tel = node2_1.getTextContent();
				                String email = node2_2.getTextContent();
				                
				                Node node3_1 = elem.getElementsByTagName("Elso").item(0);
				                Node node3_2 = elem.getElementsByTagName("Masodik").item(0);
				                String elso = node3_1.getTextContent();
				                String masodik = node3_2.getTextContent();
				                
				                System.out.println("Könyvtáros ID-ja: " + id);
				                System.out.println("Név: " + nev);
				                System.out.println("Elérhetõség: ");
				                System.out.println("\tTel: "+tel);
				                System.out.println("\tEmail: "+email);
				                System.out.println("Kitüntetések:");
				                System.out.println("\tElsõ: "+elso);
				                System.out.println("\tMásodik: "+masodik);

				            }
				        }
				        
				        for (int i = 0; i < nList6.getLength(); i++) {
				            Node nNode = nList6.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id1 = elem.getAttribute("K3ID");
				                String id2 = elem.getAttribute("TID");

				                System.out.println("Könyvtáros ID-ja: " + id1);
				                System.out.println("Termék ID-ja: " + id2);

				            }
				        }
				        
				        for (int i = 0; i < nList7.getLength(); i++) {
				            Node nNode = nList7.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id = elem.getAttribute("ID");
				                
				                Node node1 = elem.getElementsByTagName("Nev").item(0);
				                String nev = node1.getTextContent();
				                
				                Node node2 = elem.getElementsByTagName("Ar").item(0);
				                String ar = node2.getTextContent();

				                System.out.println("Termék ID-ja: " + id);
				                System.out.println("Név: "+nev);
				                System.out.println("Ár: "+ar);

				            }
				        }
				        
				        for (int i = 0; i < nList8.getLength(); i++) {
				            Node nNode = nList8.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id1 = elem.getAttribute("TID");
				                String id2 = elem.getAttribute("GyID");

				                System.out.println("Termék ID-ja: " + id1);
				                System.out.println("Gyártó ID-ja: " + id2);

				            }
				        }
				        
				        for (int i = 0; i < nList9.getLength(); i++) {
				            Node nNode = nList9.item(i);

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element elem = (Element) nNode;

				                System.out.println("\nAktuális elem: " + nNode.getNodeName());

				                String id = elem.getAttribute("ID");

				                Node node1 = elem.getElementsByTagName("Nev").item(0);
				                String nev = node1.getTextContent();
				                
				                Node node2_1 = elem.getElementsByTagName("Tel").item(0);
				                Node node2_2 = elem.getElementsByTagName("Email").item(0);
				                String tel = node2_1.getTextContent();
				                String email = node2_2.getTextContent();

				                System.out.println("Gyártó azonosítója: " + id);
				                System.out.println("Név: " + nev);
				                System.out.println("Elérhetõség: ");
				                System.out.println("\tTel: "+tel);
				                System.out.println("\tEmail: "+email);
				            }
				        }
				        
					
				}catch(SAXException sxe) {
					sxe.printStackTrace();
				}catch(ParserConfigurationException pe) {
					pe.printStackTrace();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}

			}



}
