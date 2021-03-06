package com.demopoy;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.w3c.dom.Document;
import org.w3c.dom.DOMException;
import org.json.*;

import javax.xml.transform.Source;
// For write operation
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class demotransformcionxslt {
	// Global value so it can be ref'd by the tree-adapter
    static Document document;

    public static void main(String[] argv) {
//        if (argv.length != 2) {
//            System.err.println("Usage: java Stylizer stylesheet xmlfile");
//            System.exit(1);
//        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            File stylesheet = new File("src/main/java/com/demopoy/transform1.xsl");
            File datafile = new File("src/main/java/com/demopoy/data.xml");

           // DocumentBuilder builder = factory.newDocumentBuilder();
            //document = builder.parse(datafile);

            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource stylesource = new StreamSource(stylesheet);
            Transformer transformer = tFactory.newTransformer(stylesource);

            //DOMSource source = new DOMSource(document);

            Source source = new StreamSource(datafile);
            
            // valida que source sea xml
//			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.RELAXNG_NS_URI);
//			Schema schema = schemaFactory.newSchema();
//			Validator validator = schema.newValidator();
//			validator.validate(source);
            
            //transformer.setParameter("entrada", source);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source,result);
            
            String strResult = writer.toString();
            System.out.println(strResult);
            ;
        } catch (TransformerConfigurationException tce) {
            // Error generated by the parser
            System.out.println("\n** Transformer Factory error");
            System.out.println("   " + tce.getMessage());

            // Use the contained exception, if any
            Throwable x = tce;

            if (tce.getException() != null) {
                x = tce.getException();
            }

            x.printStackTrace();
        } catch (TransformerException te) {
            // Error generated by the parser
            System.out.println("\n** Transformation error");
            System.out.println("   " + te.getMessage());

            // Use the contained exception, if any
            Throwable x = te;

            if (te.getException() != null) {
                x = te.getException();
            }

            x.printStackTrace();
        } 
//            catch (SAXException sxe) {
//            // Error generated by this application
//            // (or a parser-initialization error)
//            Exception x = sxe;
//
//            if (sxe.getException() != null) {
//                x = sxe.getException();
//            }
//
//            x.printStackTrace();
//        } 
//        catch (ParserConfigurationException pce) {
//            // Parser with specified options can't be built
//            pce.printStackTrace();
//        } 
//        catch (IOException ioe) {
//            // I/O error
//            ioe.printStackTrace();
//        }
    } // main
}

