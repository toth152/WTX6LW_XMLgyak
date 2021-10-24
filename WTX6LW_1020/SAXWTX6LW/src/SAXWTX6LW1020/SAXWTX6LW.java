package SAXWTX6LW1020;

import java.io.File;              // 3 csomagt�pust haszn�lunk fel
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXWTX6LW {
	public static void main(String[] args) {
		try {
			
			/*Dok.olvas� l�trehoz�sa, a gy�r objektumot a SAXParserFactory oszt�ly 
			newInstance mer�dusa seg�ts�g�vel k�sz�tj�k el */
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			
			/* p�ld�nyos�tja a SAX �rtelmez�t, a visszakapott gy�r �ll�tja el� a SAX elemz�t. */
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			/* saj�t esem�nykezel� objektum l�trehoz�sa */
			SaxHandler handler = new SaxHandler();
			
			//dokumentum �rtelmez�si folyamat�nak elind�t�sa
			//A parse met�dus �s param�tere a beolvasand� XML f�jl neve, a m�sodik param�tere a kezel�objektum
			saxParser.parse(new File("src/SAXWTX6LW1020/macskakWTX6LW.xml"), handler);
		
		}catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}


//tartalomkezel� keret l�trehoz�sa, valamint az esem�ny �s hibakezel� met�dusok defini�l�sa
class SaxHandler extends DefaultHandler {
	
	private int indent = 0;
	
	private String formatAttributes(Attributes attributes) {
		int attrLength = attributes.getLength();
		if (attrLength == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder(", {");
		for (int i = 0; i < attrLength; i++) {
			sb.append(attributes.getLocalName(i) + ":" + attributes.getValue(i));
			if (i < attrLength - 1) {
			sb.append(", ");
		}
	}
	sb.append("}");
	return sb.toString();
	}
	
	private void indent() {
		for (int i = 0; i< indent; i++) {
			System.out.println(" ");
		}
	}

	//elem kezdete �s v�ge
public void startElement(String uri, String localname, String qName, Attributes attributes) {
	indent++;
	indent();
	System.out.println(qName + formatAttributes(attributes) + " start");
}

@Override //endelement met�dust �jraimplement�ljuk
public void endElement(String uri, String localName, String qName) {
	indent();
	indent--;
	System.out.println(qName + " end");
}

//sz�vegelem feldolgoz�sa, characters met�dust �jraimplement�ljuk
@Override
public void characters(char ch[], int start, int length) {
	String chars = new String(ch, start, length).trim();
	if (!chars.isEmpty()) {
		indent++;
		indent();
		indent--;
		System.out.println(chars);
	}
}



}
