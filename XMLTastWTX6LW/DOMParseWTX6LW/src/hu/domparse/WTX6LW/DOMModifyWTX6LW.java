package hu.domparse.WTX6LW;

import org.w3c.dom.*;
import org.xml.sax.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.*;

public class DOMModifyWTX6LW {
	
	public static void main(String[] args) {
		//Az esetleges keletkezõ hbák lekezelése try-catch blokkban
				try {
		// A kezelendõ fájl meghatározása
					  File xmlFile = new File("src/XMLWTX6LW.xml");

		//Az XML fájl átalakítása DOM objektumokká
				        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				        DocumentBuilder builder = factory.newDocumentBuilder();
				        Document doc = builder.parse(xmlFile);
				        doc.getDocumentElement().normalize();
		//XPath segítségével határozzuk meg a módosítandó elemet, vagy annak gyermek elemét és az új értéket
				        XPath xPath = XPathFactory.newInstance().newXPath();
		//Megadjuk a kifejezést amit módosítani szeretnénk
				        String expression1 = "/konyvtar/Kolcsonzo[@ID = 'K101']/Nev/text()";
				        NodeList nodeList1 = (NodeList) xPath.compile(expression1).evaluate(doc, XPathConstants.NODESET);
		//Megadjuk a módosítás értékét
				        nodeList1.item(0).setNodeValue("Tóth Sándor");
		//Majd megadjuk újra az elérést ezuttal csak a szülõ elemet a gyermekelemet nem szükséges, ezután lekérdezhetjük a megtörtént módosítást 
				        String expression2 = "/konyvtar/Kolcsonzo[@ID = 'K101']";
				        nodeList1 = (NodeList) xPath.compile(expression2).evaluate(doc, XPathConstants.NODESET);
				     
				        String expression3 = "/konyvtar/Konyvtaros[@ID = 'K301']/Kituntetes/Elso/text()";
				        NodeList nodeList2 = (NodeList) xPath.compile(expression3).evaluate(doc, XPathConstants.NODESET);

				        nodeList2.item(0).setNodeValue("Legsegítőkészebb könyvtáros");

				        String expression4 = "/konyvtar/Konyvtaros[@ID = 'K301']";
				        nodeList2 = (NodeList) xPath.compile(expression4).evaluate(doc, XPathConstants.NODESET);
				        
				        String expression5 = "/konyvtar/Konyv[@ID = 'K200']/Cim/text()";
				        NodeList nodeList3 = (NodeList) xPath.compile(expression5).evaluate(doc, XPathConstants.NODESET);

				        nodeList3.item(0).setNodeValue("A kis csónak");

				        String expression6 = "/konyvtar/Konyv[@ID = 'K200']";
				        nodeList3 = (NodeList) xPath.compile(expression6).evaluate(doc, XPathConstants.NODESET);
		//A ciklussal végig járjuk az adott elem gyermekelemeit és kiíratjuk az értékeit		        
				        for (int i = 0; i < nodeList1.getLength(); i++) {
				            Node nNode = nodeList1.item(i);

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
				        
				        for (int i = 0; i < nodeList2.getLength(); i++) {
				            Node nNode = nodeList2.item(i);

				            System.out.println("\nAktuális elem: " + nNode.getNodeName());

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element eElement = (Element) nNode;

				                System.out.println("Könyvtáros ID-ja: " + eElement.getAttribute("ID"));

				                System.out.println("Név: " + eElement.getElementsByTagName("Nev").item(0).getTextContent());

				                System.out.println("Elérhetõség: "+"\n\tTel: " + eElement.getElementsByTagName("Tel").item(0).getTextContent()+"\n\tEmail: "
										+ eElement.getElementsByTagName("Email").item(0).getTextContent());
				                System.out.println("Kitüntetés: "+"\n\tElsõ: " + eElement.getElementsByTagName("Elso").item(0).getTextContent()+"\n\tMásodik: "
										+ eElement.getElementsByTagName("Masodik").item(0).getTextContent());
				            }
				        }
				        
				        for (int i = 0; i < nodeList3.getLength(); i++) {
				            Node nNode = nodeList3.item(i);

				            System.out.println("\nAktuális elem: " + nNode.getNodeName());

				            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				                Element eElement = (Element) nNode;

				                System.out.println("Könyv ID-ja: " + eElement.getAttribute("ID"));

				                System.out.println("Cím: " + eElement.getElementsByTagName("Cim").item(0).getTextContent());
				                
				                System.out.println("Ár: " + eElement.getElementsByTagName("Ar").item(0).getTextContent());
				                
				                System.out.println("Szerző: " + eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
				                
				                System.out.println("Oldalszám: " + eElement.getElementsByTagName("Oldalszam").item(0).getTextContent());

				            }
				        }
				}catch(SAXException sxe) {
					sxe.printStackTrace();
				}catch(ParserConfigurationException pe) {
					pe.printStackTrace();
				}catch(IOException ioe) {
					ioe.printStackTrace();
				}catch(XPathExpressionException xe) {
					xe.printStackTrace();
				}

			}

		}
