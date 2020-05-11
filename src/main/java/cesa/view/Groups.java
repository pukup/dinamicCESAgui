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
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;

import java.net.URL;
import java.util.ResourceBundle;

public class Groups implements Initializable {

    @FXML
    ImageView iEl1, iAz;
    @FXML
    ComboBox<Integer> cbFocus;
    @FXML
    TextField tfAzB, tfElB, tfDate, tfHour;
    @FXML
    TextArea textArea;
    @FXML
    private ListView listView;
    private HeliostatController heliostatController;
    private ObservableList<Heliostat> heliostats = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void handleDrag(DragEvent dragEvent) {
        Dragboard db = dragEvent.getDragboard();
        if (db.hasContent(Heliostat.DataFormat) && !heliostats.contains((Heliostat) db.getContent(Heliostat.DataFormat))) {
            heliostats.add((Heliostat) db.getContent(Heliostat.DataFormat));
            listView.setItems(heliostats);
            dragEvent.setDropCompleted(true);
        }
        dragEvent.consume();
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

    private void command(String b) {
        for (Heliostat heliostat : heliostats) {
            textArea.appendText(heliostatController.command(heliostat.getComLineId(), heliostat.getId(), b) + "\n");
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
    private void getOffset() {
    }

    @FXML
    private void setOffset() {

    }

    @FXML
    private void getHour() {
    }

    @FXML
    private void setHour() {
    }
}
