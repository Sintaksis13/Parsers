package com.epam.lab23.parsers.sax;

import com.epam.lab23.parsers.flatwares.Flatware;
import com.epam.lab23.parsers.flatwares.Fork;
import com.epam.lab23.parsers.flatwares.Knife;
import com.epam.lab23.parsers.flatwares.Spoon;
import com.epam.lab23.parsers.types.Material;
import com.epam.lab23.parsers.types.TypeOfMetal;
import com.epam.lab23.parsers.types.TypeOfWood;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MySaxParser extends DefaultHandler {

    private StringBuffer accumulator;
    private String type;
    private String origin;
    private boolean value;
    private int index = 0;

    private ArrayList<Flatware> arrayList;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Parsing XML-file...");
        accumulator = new StringBuffer();
        arrayList = new ArrayList<>();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        accumulator.setLength(0);
        if (qName.equals("flat:flatwares")) {
            System.out.println("Start of document");
        }
        if (qName.equals("flatware")) {
            type = attributes.getValue("type");
            origin = attributes.getValue("origin");
            value = Boolean.parseBoolean(attributes.getValue("value"));
            if (type.equals("fork")){
                arrayList.add(index, new Fork());
                arrayList.get(index).setOrigin(origin);
                arrayList.get(index).setValue(value);
            } else if (type.equals("knife")) {
                arrayList.add(index, new Knife());
                arrayList.get(index).setOrigin(origin);
                arrayList.get(index).setValue(value);
            } else if (type.equals("spoon")) {
                arrayList.add(index, new Spoon());
                arrayList.get(index).setOrigin(origin);
                arrayList.get(index).setValue(value);
            }
            index++;
        }
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        if (qName.equals("bladeLength")) {
            if (type.equals("knife")) {
                Knife kn = (Knife) arrayList.get(index - 1);
                kn.setBladeLength(Integer.parseInt(accumulator.toString()));
            }
        }
        if (qName.equals("bladeWidth")) {
            if (type.equals("knife")) {
                Knife kn = (Knife) arrayList.get(index - 1);
                kn.setBladeWidth(Integer.parseInt(accumulator.toString()));
            }
        }
        if (qName.equals("toothLength")) {
            if (type.equals("fork")) {
                Fork fk = (Fork) arrayList.get(index - 1);
                fk.setToothLength(Integer.parseInt(accumulator.toString()));
            }
        }
        if (qName.equals("material")) {
            if (accumulator.toString().equals(TypeOfMetal.COPPER.toString().toLowerCase())) {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.COPPER);
            } else if (accumulator.toString().equals(TypeOfMetal.IRON.toString().toLowerCase())) {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.IRON);
            } else if (accumulator.toString().equals(TypeOfMetal.CRUDEIRON.toString().toLowerCase())) {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.CRUDEIRON);
            } else {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.STEEL);
            }
        }
        if (qName.equals("gripMaterial")) {
                if (accumulator.toString().equals(Material.METAL.toString().toLowerCase())) {
                    arrayList.get(index - 1).setGripMaterial(Material.METAL);
                } else if (accumulator.toString().equals(Material.PLASTIC.toString().toLowerCase())) {
                    arrayList.get(index - 1).setGripMaterial(Material.PLASTIC);
                } else {
                    arrayList.get(index - 1).setGripMaterial(Material.WOOD);
                }
        }
        if (qName.equals("woodType")) {
                if (arrayList.get(index - 1).getGripMaterial().equals(Material.WOOD)) {
                    if (accumulator.toString().equals(TypeOfWood.BIRCH.toString().toLowerCase())) {
                        arrayList.get(index - 1).setTypeOfWood(TypeOfWood.BIRCH);
                    } else if (accumulator.toString().equals(TypeOfWood.MAPLE.toString().toLowerCase())) {
                        arrayList.get(index - 1).setTypeOfWood(TypeOfWood.MAPLE);
                    } else if (accumulator.toString().equals(TypeOfWood.POPLAR.toString().toLowerCase())) {
                        arrayList.get(index - 1).setTypeOfWood(TypeOfWood.POPLAR);
                    } else {
                        arrayList.get(index - 1).setTypeOfWood(TypeOfWood.OAK);
                    }
                }
        }
        if (qName.equals("volume")) {
            if (type.equals("spoon")) {
                Spoon sp = (Spoon) arrayList.get(index - 1);
                sp.setVolume(Integer.parseInt(accumulator.toString()));
            }
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        accumulator.append(ch, start, length);
    }

    @Override
    public void endDocument() {
        for (Flatware flat: arrayList) {
            System.out.println(flat.toString());
        }
        System.out.println("Stop parse XML.");
    }
}
