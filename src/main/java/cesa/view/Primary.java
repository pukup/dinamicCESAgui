package cesa.view;


import cesa.App;
import cesa.controller.ComLineController;
import cesa.controller.TimerCacheTask;
import cesa.model.ComLine;
import cesa.model.Heliostat;
import cesa.model.Row;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Timer;

/**
 *
 */
public class Primary implements Initializable {

    private TimerCacheTask timerCacheTask;
    private HashMap<Integer, Row> rows;

    @FXML
    private VBox rows_vbox;

    @FXML
    private Slider zoom_slider;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zoom_slider.valueProperty().addListener((o, oldVal, newVal) -> zoom());
        rows = new HashMap<Integer, Row>();
        loadComLines();
        loadTimer();
    }

    private void loadTimer() {
        Timer timer = new Timer("TimerHeliostatsRefresh");
        timerCacheTask = new TimerCacheTask(this);
        timer.schedule(timerCacheTask, 0, 1000);
    }

    @FXML
    private void zoom() {
        rows_vbox.styleProperty().bind(Bindings.format("-fx-font-size: %.2fpt;", zoom_slider.getValue()));
    }

    @FXML
    private void zoomIn(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal += 1);
    }

    @FXML
    private void zoomOut(ActionEvent event) {
        double sliderVal = zoom_slider.getValue();
        zoom_slider.setValue(sliderVal -= 1);
    }

    @FXML
    private void openGrouping(ActionEvent event) throws IOException {
        Scene scene = new Scene(loadFXML("groups"));
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    @FXML
    private void emergency() {
        // The ones in focus must go to kilter focus throw aisle.
    }


    /**
     * It sets the GUI elements to represent the <code>Row</code> objects which are obtained from the server
     */
    private void loadComLines() {
        int comLineNo = ComLineController.getNumber();
        for (int i = 1; i < comLineNo + 1; i++) {
            ComLine comLine = ComLineController.getAPICache(i);
            rows_vbox.getChildren().add(createGUIHbox(comLine));
        }
    }

    private Row createGUIHbox(ComLine comLine) {
        Row row= new Row(comLine);
        row.setSpacing(30.0);
        row.setAlignment(Pos.CENTER);
        createRegion(row);
        createLabel(row);
        createButtons(row);
        createLabel(row);
        createRegion(row);
        rows.put(comLine.getId(), row);
        return row;
    }

    private void createRegion(Row row) {
        Region region = new Region();
        row.setHgrow(region, Priority.ALWAYS);
        row.getChildren().add(region);
    }

    private void createLabel(Row row) {
        Label label = new Label(String.valueOf(row.getComLine().getId()));
        label.setFont(new Font(24));
        label.setTextFill(Color.WHITE);
        row.getChildren().add(label);
    }

    private void createButtons(Row row) {
        HashMap<Integer, HeliostatButton> heliostatButtons = new HashMap<>();
        for (Heliostat heliostat : row.getComLine().getHeliostats().values()) {
            HeliostatButton button = new HeliostatButton(row.getComLine().getId(), "" + row.getComLine().getId() + "-" + heliostat.getId());
            heliostatButtons.put(heliostat.getId(), button);
            row.setIntegerHeliostatButtonHashMap(heliostatButtons);
            row.getChildren().add(button);
        }
    }

    public void refresRows() {
        for (Map.Entry<Integer, Row> integerRowEntry : rows.entrySet()) {
            integerRowEntry.getValue().getComLine().setAttributes(ComLineController.getAPICache(integerRowEntry.getKey()));
            HashMap<Integer, HeliostatButton> heliostatButtonHashMap = integerRowEntry.getValue().getIntegerHeliostatButtonHashMap();
            for (Map.Entry<Integer, HeliostatButton> integerHeliostatButtontEntry : heliostatButtonHashMap.entrySet()) {
                integerHeliostatButtontEntry.getValue().refreshHeliostat(integerRowEntry.getValue().getComLine().getHeliostats().get(integerHeliostatButtontEntry.getKey()));
            }
        }
    }
}
