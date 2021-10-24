package SAXWTX6LW1020;

import java.io.File;              // 3 csomagtípust használunk fel
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
			
			/*Dok.olvasó létrehozása, a gyár objektumot a SAXParserFactory osztály 
			newInstance meródusa segítségével készítjük el */
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			
			/* példányosítja a SAX értelmezõt, a visszakapott gyár állítja elõ a SAX elemzõt. */
			SAXParser saxParser = saxParserFactory.newSAXParser();
			
			/* saját eseménykezelõ objektum létrehozása */
			SaxHandler handler = new SaxHandler();
			
			//dokumentum értelmezési folyamatának elindítása
			//A parse metódus és paramétere a beolvasandó XML fájl neve, a második paramétere a kezelõobjektum
			saxParser.parse(new File("src/SAXWTX6LW1020/macskakWTX6LW.xml"), handler);
		
		}catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}


//tartalomkezelõ keret létrehozása, valamint az esemény és hibakezelõ metódusok definiálása
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

	//elem kezdete és vége
public void startElement(String uri, String localname, String qName, Attributes attributes) {
	indent++;
	indent();
	System.out.println(qName + formatAttributes(attributes) + " start");
}

@Override //endelement metódust újraimplementáljuk
public void endElement(String uri, String localName, String qName) {
	indent();
	indent--;
	System.out.println(qName + " end");
}

//szövegelem feldolgozása, characters metódust újraimplementáljuk
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
