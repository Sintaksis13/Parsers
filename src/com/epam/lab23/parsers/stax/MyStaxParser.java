package com.epam.lab23.parsers.stax;

import com.epam.lab23.parsers.flatwares.Flatware;
import com.epam.lab23.parsers.flatwares.Fork;
import com.epam.lab23.parsers.flatwares.Knife;
import com.epam.lab23.parsers.flatwares.Spoon;
import com.epam.lab23.parsers.types.Material;
import com.epam.lab23.parsers.types.TypeOfMetal;
import com.epam.lab23.parsers.types.TypeOfWood;

import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyStaxParser {

    private List<Flatware> arrayList = new ArrayList<>();
    private String type;
    private String origin;
    public boolean value;

    private static int index = 0;
    private String name;
    private StringBuilder accumulator;

    void parse(InputStream inputStream) {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = inputFactory.createXMLEventReader(inputStream);
            process(reader);
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }

    private void process(XMLEventReader reader) throws XMLStreamException{
        accumulator = new StringBuilder();
        StartElement startElement = null;
        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            switch (event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT: {
                    startElement = event.asStartElement();
                    break;
                }
                case XMLStreamConstants.END_ELEMENT: {
                    event.asEndElement();
                    startElement = null;
                    break;
                }
                case XMLStreamConstants.CHARACTERS: {
                    accumulator.setLength(0);
                    accumulator.append(event.asCharacters().toString().trim());
                    if (startElement == null) {
                        break;
                    }
                    name = startElement.getName().getLocalPart();
                    handleTag(startElement);
                    break;
                }
                case XMLStreamConstants.END_DOCUMENT: {
                    printElements();
                }
            }
        }
    }

    private void handleTag(StartElement startElement) {

        if (name.equals("flatwares")) {
            System.out.println("Starting document...");
        }
        if (name.equals("flatware")) {
            Iterator<Attribute> attributes = startElement.getAttributes();
            Attribute attribute;
            while (attributes.hasNext()) {
                attribute = attributes.next();
                handleAttribute(attribute.getName().getLocalPart(), attribute.getValue());
            }
            createObject();
            arrayList.get(index).setOrigin(origin);
            arrayList.get(index).setValue(value);
            index++;
        }
        if (name.equals("bladeLength")) {
            if (type.equals("knife")) {
                Knife kn = (Knife) arrayList.get(index - 1);
                kn.setBladeLength(Integer.parseInt(accumulator.toString()));
            }
        }
        if (name.equals("toothLength")) {
            if (type.equals("fork")) {
                Fork fk = (Fork) arrayList.get(index - 1);
                fk.setToothLength(Integer.parseInt(accumulator.toString()));
            }
        }
        if (name.equals("bladeWidth")) {
            if (type.equals("knife")) {
                Knife kn = (Knife) arrayList.get(index - 1);
                kn.setBladeWidth(Integer.parseInt(accumulator.toString()));
            }
        }
        if (name.equals("material")) {
            if (accumulator.toString().equals(TypeOfMetal.IRON.toString().toLowerCase())) {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.IRON);
            } else if (accumulator.toString().equals(TypeOfMetal.CRUDEIRON.toString().toLowerCase())) {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.CRUDEIRON);
            } else if (accumulator.toString().equals(TypeOfMetal.COPPER.toString().toLowerCase())) {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.COPPER);
            } else {
                arrayList.get(index - 1).setMaterial(TypeOfMetal.STEEL);
            }
        }
        if (name.equals("volume")) {
            if (type.equals("spoon")) {
                Spoon sp = (Spoon) arrayList.get(index-1);
                sp.setVolume(Integer.parseInt(accumulator.toString()));
            }
        }
        if (name.equals("gripMaterial")) {
            if (accumulator.toString().equals(Material.PLASTIC.toString().toLowerCase())) {
                arrayList.get(index-1).setGripMaterial(Material.PLASTIC);
            } else if (accumulator.toString().equals(Material.WOOD.toString().toLowerCase())) {
                arrayList.get(index-1).setGripMaterial(Material.WOOD);
            } else {
                arrayList.get(index-1).setGripMaterial(Material.METAL);
            }
        }
        if (name.equals("woodType")) {
            if (accumulator.toString().equals(TypeOfWood.BIRCH.toString().toLowerCase())) {
                arrayList.get(index-1).setTypeOfWood(TypeOfWood.BIRCH);
            } else if (accumulator.toString().equals(TypeOfWood.MAPLE.toString().toLowerCase())) {
                arrayList.get(index-1).setTypeOfWood(TypeOfWood.MAPLE);
            } else if (accumulator.toString().equals(TypeOfWood.POPLAR.toString().toLowerCase())) {
                arrayList.get(index-1).setTypeOfWood(TypeOfWood.POPLAR);
            } else {
                arrayList.get(index-1).setTypeOfWood(TypeOfWood.OAK);
            }
        }
    }

    private void handleAttribute(String nameOfAttribute, String valueOfAttribute) {
        if (nameOfAttribute.equals("type")) {
            type = valueOfAttribute;
        }
        if (nameOfAttribute.equals("origin")) {
            origin = valueOfAttribute;
        }
        if (nameOfAttribute.equals("value")) {
            value = Boolean.parseBoolean(valueOfAttribute);
        }
    }

    private void createObject() {
        if (type.equals("fork")) {
            arrayList.add(new Fork());
        } else if (type.equals("spoon")) {
            arrayList.add(new Spoon());
        } else if (type.equals("knife")) {
            arrayList.add(new Knife());
        }
    }

    private void printElements() {
        for (Flatware flatware: arrayList) {
            System.out.println(flatware.toString());
        }
    }
}
