package cesa.model;

import cesa.view.HeliostatButton;
import javafx.scene.layout.HBox;

import java.util.HashMap;

public class Row extends HBox {
    private ComLine comLine;
    private HashMap<Integer, HeliostatButton> integerHeliostatButtonHashMap;

    public Row(ComLine comLine) {
        this.comLine = comLine;
    }

    public ComLine getComLine() {
        return comLine;
    }

    public void setComLine(ComLine comLine) {
        this.comLine = comLine;
    }

    public HashMap<Integer, HeliostatButton> getIntegerHeliostatButtonHashMap() {
        return integerHeliostatButtonHashMap;
    }

    public void setIntegerHeliostatButtonHashMap(HashMap<Integer, HeliostatButton> integerHeliostatButtonHashMap) {
        this.integerHeliostatButtonHashMap = integerHeliostatButtonHashMap;
    }


}
