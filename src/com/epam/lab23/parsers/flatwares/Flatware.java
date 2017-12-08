package com.epam.lab23.parsers.flatwares;

import com.epam.lab23.parsers.types.Material;
import com.epam.lab23.parsers.types.TypeOfMetal;
import com.epam.lab23.parsers.types.TypeOfWood;

public abstract class Flatware {

    private String origin;
    private boolean value;
    private TypeOfMetal material;
    private Material gripMaterial;
    private TypeOfWood typeOfWood;

    @Override
    public String toString() {
        return getClass().getName() + " " + origin + " " + material + " " + value + " " + " " + gripMaterial +
                " " + typeOfWood;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public TypeOfMetal getMaterial() {
        return material;
    }

    public void setMaterial(TypeOfMetal material) {
        this.material = material;
    }

    public Material getGripMaterial() {
        return gripMaterial;
    }

    public void setGripMaterial(Material gripMaterial) {
        this.gripMaterial = gripMaterial;
    }

    public TypeOfWood getTypeOfWood() {
        return typeOfWood;
    }

    public void setTypeOfWood(TypeOfWood typeOfWood) {
        this.typeOfWood = typeOfWood;
    }
}
