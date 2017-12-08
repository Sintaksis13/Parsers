/*
package com.epam.lab23.parsers.dom;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DomMain {
    private final static String fileName = "src\\com\\epam\\lab23\\parsers\\xml\\Flatware.xml";

    public static void main(String[] args) {
        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);

            MyDomParser myDomParser = new MyDomParser();
            myDomParser.handleDoc(document);

        } catch (ParserConfigurationException ex) {
            System.out.println(ex.getMessage());
        } catch (SAXException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
*/
