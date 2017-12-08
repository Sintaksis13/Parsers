package com.epam.lab23.parsers.dom;

import com.epam.lab23.parsers.flatwares.Flatware;
import com.epam.lab23.parsers.flatwares.Fork;
import com.epam.lab23.parsers.flatwares.Knife;
import com.epam.lab23.parsers.flatwares.Spoon;
import com.epam.lab23.parsers.types.Material;
import com.epam.lab23.parsers.types.TypeOfMetal;
import com.epam.lab23.parsers.types.TypeOfWood;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class MyDomParser {

    private List<Flatware> list = new ArrayList<>();
    private int index = 0;

    public void handleDoc(Document doc) {
        Node rootTag = doc.getDocumentElement();
        handleTag(rootTag);
        NodeList nodeList = doc.getElementsByTagName("flatware");

        for (int i = 0; i < nodeList.getLength(); i++) {
            handleTag(nodeList.item(i));
        }

        for (Flatware f: list) {
            System.out.println(f.toString());
        }
    }

    private void handleTag(Node node) {
        String nodeName = node.getNodeName();
        if (nodeName.equals("flat:flatwares")) {
            System.out.println("Start parsing XML...");
        }
        if (nodeName.equals("flatware")) {
            handleAttribute(node);
            index++;
            Element el = (Element) node.getChildNodes();
            NodeList flatwareNodes = el.getElementsByTagName("visual");
            for (int i = 0; i < flatwareNodes.getLength(); i++) {
                handleTag(flatwareNodes.item(i));
            }
        }
        if (nodeName.equals("visual")) {
            Element el = (Element) node.getChildNodes();
            NodeList visualNodes = el.getChildNodes();
            for (int i = 0; i < visualNodes.getLength(); i++) {
                handleTag(visualNodes.item(i));
            }
        }
        if (nodeName.equals("bladeLength")) {
            if (list.get(index-1).getClass().equals(Knife.class)) {
                Knife kn = (Knife) list.get(index-1);
                kn.setBladeLength(new Integer(node.getTextContent()));
            }
        }
        if (nodeName.equals("bladeWidth")) {
            if (list.get(index-1).getClass().equals(Knife.class)) {
                Knife kn = (Knife) list.get(index-1);
                kn.setBladeWidth(new Integer(node.getTextContent()));
            }
        }
        if (nodeName.equals("volume")) {
            if (list.get(index-1).getClass().equals(Spoon.class)) {
                Spoon sp = (Spoon) list.get(index-1);
                sp.setVolume(new Integer(node.getTextContent()));
            }
        }
        if (nodeName.equals("toothLength")) {
            if (list.get(index-1).getClass().equals(Fork.class)) {
                Fork fk = (Fork) list.get(index-1);
                fk.setToothLength(new Integer(node.getTextContent()));
            }
        }
        if (nodeName.equals("material")) {
            setMaterial(node);
        }
        if (nodeName.equals("gripMaterial")) {
            setGripMaterial(node);
        }
        if (nodeName.equals("woodType")) {
            setWoodType(node);
        }
    }

    private void handleAttribute(Node node) {
        Node attribute;
        String type = null;
        String origin = null;
        boolean value = false;

        for (int i = 0; i <= 2; i++) {
            attribute = node.getAttributes().item(i);
            if (attribute.getNodeName().equals("type")) {
                type = attributeDef(attribute);
            } else if (attribute.getNodeName().equals("value")) {
                value = Boolean.parseBoolean(attributeDef(attribute));
            } else if (attribute.getNodeName().equals("origin")){
                origin = attributeDef(attribute);
            }
        }
        createObjects(type);

        if (value == true) {
            list.get(index).setValue(true);
        } else {
            list.get(index).setValue(false);
        }

        list.get(index).setOrigin(origin);
    }

    private void createObjects(String type) {
        if (type.equals("fork")) {
            list.add(new Fork());
        } else if (type.equals("spoon")) {
            list.add(new Spoon());
        } else if (type.equals("knife")) {
            list.add(new Knife());
        }
    }

    private String attributeDef(Node node) {
        String str = null;
        if (node.getNodeName().equals("type")) {
            if (node.getTextContent().equals("fork")) {
                str = "fork";
            } else if (node.getTextContent().equals("spoon")) {
                str = "spoon";
            } else if (node.getTextContent().equals("knife")) {
                str = "knife";
            }
        } else if (node.getNodeName().equals("value")) {
            if (node.getTextContent().equals("true")) {
                str = node.getTextContent();
            } else {
                str = node.getTextContent();
            }
        } else if (node.getNodeName().equals("origin")){
            str = node.getTextContent();
        }

        return str;
    }

    private void setMaterial(Node node) {
        if (node.getTextContent().equals(TypeOfMetal.COPPER.toString().toLowerCase())) {
            list.get(index-1).setMaterial(TypeOfMetal.COPPER);
        } else if (node.getTextContent().equals(TypeOfMetal.IRON.toString().toLowerCase())) {
            list.get(index-1).setMaterial(TypeOfMetal.IRON);
        } else if (node.getTextContent().equals(TypeOfMetal.STEEL.toString().toLowerCase())) {
            list.get(index-1).setMaterial(TypeOfMetal.STEEL);
        } else {
            list.get(index-1).setMaterial(TypeOfMetal.CRUDEIRON);
        }
    }

    private void setGripMaterial(Node node) {
        if (node.getTextContent().equals(Material.PLASTIC.toString().toLowerCase())) {
            list.get(index-1).setGripMaterial(Material.PLASTIC);
        } else if (node.getTextContent().equals(Material.WOOD.toString().toLowerCase())) {
            list.get(index-1).setGripMaterial(Material.WOOD);
        } else {
            list.get(index-1).setGripMaterial(Material.METAL);
        }
    }

    private void setWoodType(Node node) {
        if (node.getTextContent().equals(TypeOfWood.BIRCH)) {
            list.get(index-1).setTypeOfWood(TypeOfWood.BIRCH);
        } else if (node.getTextContent().equals(TypeOfWood.MAPLE)) {
            list.get(index-1).setTypeOfWood(TypeOfWood.MAPLE);
        } else if (node.getTextContent().equals(TypeOfWood.POPLAR)) {
            list.get(index-1).setTypeOfWood(TypeOfWood.POPLAR);
        } else {
            list.get(index-1).setTypeOfWood(TypeOfWood.OAK);
        }
    }
}
