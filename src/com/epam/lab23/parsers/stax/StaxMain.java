package com.epam.lab23.parsers.stax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StaxMain {

    final static String fileName = "src\\com\\epam\\lab23\\parsers\\xml\\Flatware.xml";

    public static void main(String[] args) {
        MyStaxParser parser;
        try (InputStream input = new FileInputStream(fileName)){
            parser = new MyStaxParser();
            parser.parse(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}
