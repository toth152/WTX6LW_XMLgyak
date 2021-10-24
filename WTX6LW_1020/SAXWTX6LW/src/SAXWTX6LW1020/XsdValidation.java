package SAXWTX6LW1020;

import java.io.File;
import java.io.IOException;
  
import javax.xml.validation.Schema;	             //Csomagtípusok használata
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.SAXException;

public class XsdValidation {
	
	public static boolean XSDValidation(String xsdUtv, String xmlUtv){  //Validáló függvény megírása
	      try {
	    	 SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	    	 Schema schema = factory.newSchema(new File(xsdUtv));
	         Validator validator = schema.newValidator();
	         validator.validate(new StreamSource(new File(xmlUtv)));
	      } catch (IOException e){
	         System.out.println("Exception: "+e.getMessage());
	         return false;
	      }catch(SAXException e1){
	         System.out.println("SAX Exception: "+e1.getMessage());
	         return false;
	      }
			
	      return true;
	   }
	
	public static void main(String[] args) {
		
		boolean isValid = XSDValidation("src/SaxWTX6LW1020/macskakWTX6LW.xsd",  //2 útvonal megadása
				                            "src/SAXWTX6LW1020/macskakWTX6LW.xml");
        
        if(isValid){
           System.out.println("Successful validation!");   //Az XSD fájl alapján történik a validálás
        } else {
           System.out.println("Failed validation");
        }

	}
	
}


