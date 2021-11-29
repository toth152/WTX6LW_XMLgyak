package hu.domparse.WTX6LW;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryWTX6LW {
	
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
				        String expression1 = "/konyvtar/Kolcsonzo[@ID ='K100']";
				        NodeList nodeList1 = (NodeList) xPath.compile(expression1).evaluate(doc, XPathConstants.NODESET);
				        
				        String expression2 = "/konyvtar/Konyv[Ar > 3000]";
				        NodeList nodeList2 = (NodeList) xPath.compile(expression2).evaluate(doc, XPathConstants.NODESET);
				        
				        String expression3 = "/konyvtar/Konyv[Ar > 3000 and Oldalszam > 700]";
				        NodeList nodeList3 = (NodeList) xPath.compile(expression3).evaluate(doc, XPathConstants.NODESET); 
		
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

				                System.out.println("Könyv ID-ja: " + eElement.getAttribute("ID"));

				                System.out.println("Cím: " + eElement.getElementsByTagName("Cim").item(0).getTextContent());
				                
				                System.out.println("Ár: " + eElement.getElementsByTagName("Ar").item(0).getTextContent());
				                
				                System.out.println("Szerző: " + eElement.getElementsByTagName("Szerzo").item(0).getTextContent());
				                
				                System.out.println("Oldalszám: " + eElement.getElementsByTagName("Oldalszam").item(0).getTextContent());

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
