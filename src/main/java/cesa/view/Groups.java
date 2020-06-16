package cesa.view;

import cesa.controller.HeliostatController;
import cesa.model.Heliostat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.net.URL;
import java.util.ResourceBundle;

public class Groups implements Initializable {


    @FXML
    ComboBox<Integer> cbFocus;
    @FXML
    TextField tfAzB, tfElB;
    @FXML
    TextArea textArea;
    @FXML
    private ListView listView;
    private HeliostatController heliostatController;
    private ObservableList<Heliostat> heliostats = FXCollections.observableArrayList();
    private ObservableList<Heliostat> trackingList = FXCollections.observableArrayList();
    private ObservableList<Heliostat> kilterList = FXCollections.observableArrayList();
    private ObservableList<Heliostat> emergencyList = FXCollections.observableArrayList();
    private ObservableList<Heliostat> focusList = FXCollections.observableArrayList();
    private ObservableList<Heliostat> dejectionList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void handleDrag(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasContent(Heliostat.DataFormat)) {
            Heliostat heliostat = (Heliostat) db.getContent(Heliostat.DataFormat);
            heliostats.add(heliostat);
            listView.setItems(heliostats);
            dragEvent.setDropCompleted(true);
        }
    }

    @FXML
    private void dejection(ActionEvent event) {
        command("a");
    }

    @FXML
    private void aisle(ActionEvent event) {
        command("b");
    }

    @FXML
    private void kilter(ActionEvent event) {
        command("d");
    }

    @FXML
    private void boiler(ActionEvent event) {
        command("e");
    }

    @FXML
    private void immobilize(ActionEvent event) {
        command("i");
    }

    @FXML
    private void outService(ActionEvent event) {
        command("l");
    }

    @FXML
    private void tracking(ActionEvent event) {
        command("n");
    }

    @FXML
    private void emergency(ActionEvent event) {
        command("q");
    }

    @FXML
    private void kilterAisle(ActionEvent event) {
        command("s");
    }

    private void command(String s) {
        try {
            if (!heliostats.isEmpty())
                for (Heliostat heliostat : heliostats) {
                    textArea.appendText(heliostatController.command(heliostat.getComLineId(), heliostat.getId(), s) + "\n");
                }
            else {
                Message.show("La lista de heliostatos esta vacia.");
            }
        } catch (Exception e) {
            Message.show(e.toString());
        }
    }

    @FXML
    private void setFocus() {
    }

    @FXML
    private void newFocus() {
    }

    @FXML
    private void setAzEl() {
    }

    @FXML
    private void trackingList() {

    }

    @FXML
    private void kilterList() {

    }

    @FXML
    private void emergencyList() {

    }

    @FXML
    private void focusList() {

    }

    @FXML
    private void dejectionList() {

    }

}
