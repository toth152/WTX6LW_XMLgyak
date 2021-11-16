package xpathWTX6LW1110;

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

public class xPathWTX6LW {
	
	public static void main(String[] args) {
		
		try {
			//File xmlFile = new File("/src/xpathWTX6LW1110/studentWTX6LW.xml");
			
			//DocumentumBuilder létrehozása
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document doc = builder.parse("src/studentWTX6LW.xml");
			doc.getDocumentElement().normalize();
			
			//az XPath fa készítése
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			//Meg kell adni az elérési út kifejezést és a csomópont listát:
			
			//String expression = "class";
			
			//1) Válassza ki az összes student element, amely a class gyermekei.
			//String expression = "/class/student";
			
			//2) Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "01".
			//String expression = "/class/student[@id ='01']";
			
			//3) Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban.
			//String expression = "//student";
			
			//4) Válassza ki a második student element, amely a class elem gyermeke.
			//String expression = "/class/student[2]";
			
			//5) Válassza ki az utolsó student elemet, amely a class elem gyermeke.
			//String expression = "/class/student[last()]";
			
			//6) Válassza ki az utolsó elõtti student elemet, amely a class elem gyermeke.
			//String expression = "/class/student[last()-1]";
			
			//7) Válassza ki az elsõ két student elemet, amelyek a class elem gyermekei.
			//String expression = "/class/student[position()<3]";
			
			//8) Válassza ki class elem összes gyermek elemét.
			//String expression = "/class/*";
			
			//9) Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal.
			//String expression = "//student[@*]";
			
			//10) Válassza ki a dokumentum összes elemét.
			//String expression = "//*";
				
			//11) Válassza ki a class elem összes student elemét, amelynél a kor>20.
			String expression = "/class/student[kor > 20]";
			
			//Készítsünk egy listát, majd a Path kifejezést meg kell írni és ki kell értékelni
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			//For ciklus segítségével a NodeList csomóontjait végig kell iterálni
			for (int i=0; i<nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				
				System.out.println("\nAktuális elem: " + node.getNodeName());
				
				
				//Meg kell vizsgálni a csomópontot a subelement tesztelése megtörtént
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					//az id attribútumot ad vissza
					System.out.println("Hallgató ID: " 
							+element.getAttribute("id"));
					
					System.out.println("Keresztnév: " 
							+element.getElementsByTagName("keresztnev").item(0).getTextContent());
					
					System.out.println("Vezetéknév: " 
							+ element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					
					System.out.println("Becenév: " 
							+ element.getElementsByTagName("becenev").item(0).getTextContent());
					
					System.out.println("Kor: " 
							+ element.getElementsByTagName("kor").item(0).getTextContent());
					
					
				}
				
				
				
			}
		
		
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}
}