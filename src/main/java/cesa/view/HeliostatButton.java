package cesa.view;

import cesa.App;
import cesa.controller.HeliostatController;
import cesa.model.Heliostat;
import javafx.beans.NamedArg;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * It manages the <code>StyleClass</code> of buttons to change them according to <code>Heliostat</code> attributes values.
 */
public class HeliostatButton extends VBox {

    @FXML
    TextField tfAddress, tfModbus, tfStatusCode, tfWarning, tfOperation, tfSecurity, tfCom, tfCL, tfAz, tfEl, tfMotAz, tfMotEl, tfStatusReachedAz, tfStatusReachedEl, tfSwingAz, tfSwingEl, tfNotAz, tfNotEl;
    @FXML
    ImageView iEl1, iAz;
    @FXML
    ComboBox<Integer> cbFocus;
    @FXML
    TextField tfFocusX, tfFocusY, tfFocusZ;
    @FXML
    TextField tfAzB, tfElB, tfOffAz, tfOffEl, tfDate, tfHour;
    @FXML
    TextArea textArea;
    @FXML
    private Button button;
    @FXML
    private Tooltip tooltip;

    private Heliostat heliostat;

    private HeliostatController heliostatController;

    private Scene valuesScene;

    public HeliostatButton(@NamedArg("comLineId") int comLineId, @NamedArg("toolTip") String toolTip) {
        try {
            loadButton();
            heliostat = new Heliostat(comLineId);
            tooltip = new Tooltip(toolTip);
            Tooltip.install(button, tooltip);
            heliostatController = new HeliostatController();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void loadButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("heliostatButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    @FXML
    private void openValues(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("values.fxml"));
        fxmlLoader.setController(this);
        valuesScene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(valuesScene);
        stage.show();
    }

    @FXML
    private void handleDrag(MouseEvent mouseEvent) {
        Dragboard dragboard = button.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.put(Heliostat.DataFormat, heliostat);
        dragboard.setContent(clipboardContent);
        mouseEvent.consume();
    }

    @FXML
    private void dejection(ActionEvent event) {
        textArea.appendText(command("a") + "\n");
    }

    @FXML
    private void aisle(ActionEvent event) {
        textArea.appendText(command("b") + "\n");
    }

    @FXML
    private void kilter(ActionEvent event) {
        textArea.appendText(command("d") + "\n");
    }

    @FXML
    private void boiler(ActionEvent event) {
        textArea.appendText(command("e") + "\n");
    }

    @FXML
    private void immobilize(ActionEvent event) {
        textArea.appendText(command("i") + "\n");
    }

    @FXML
    private void outService(ActionEvent event) {
        textArea.appendText(command("l") + "\n");
    }

    @FXML
    private void tracking(ActionEvent event) {
        textArea.appendText(command("n") + "\n");
    }

    @FXML
    private void emergency(ActionEvent event) {
        textArea.appendText(command("q") + "\n");
    }

    @FXML
    private void kilterAisle(ActionEvent event) {
        textArea.appendText(command("s") + "\n");
    }

    private String command(String s) {
        return heliostatController.command(heliostat.getComLineId(), heliostat.getId(), s);
    }

    @FXML
    private void setFocus() {
        try {
            textArea.appendText(heliostatController.focus(heliostat.getComLineId(), heliostat.getId(), cbFocus.getValue()) + "\n");
        } catch (Exception e) {
            Message.show("Seleccione el foco adecuado");
        }
    }

    @FXML
    private void newFocus() {
        try {
            int focus = cbFocus.getValue();
            int x = Integer.parseInt(tfFocusX.getText());
            int y = Integer.parseInt(tfFocusY.getText());
            int z = Integer.parseInt(tfFocusZ.getText());
            textArea.appendText(heliostatController.newFocus(heliostat.getComLineId(), heliostat.getId(), focus, x, y, z) + "\n");
        } catch (Exception e) {
            Message.show("Seleccione el foco y las coordenadas adecuades");
        }
    }

    @FXML
    private void setAzEl() {
        try {
            int az = Integer.valueOf(tfAzB.getText());
            int el = Integer.valueOf(tfElB.getText());
            heliostatController.setAz(heliostat.getComLineId(), heliostat.getId(), az);
            heliostatController.setEl(heliostat.getComLineId(), heliostat.getId(), el);
        } catch (Exception e) {
            Message.show("Introduzca valores adecuados");
        }
    }

    @FXML
    private void getOffset() {
        tfOffAz.setText(heliostatController.getOffsetAz(heliostat.getComLineId(), heliostat.getId()));
        tfOffEl.setText(heliostatController.getOffsetEl(heliostat.getComLineId(), heliostat.getId()));
    }

    @FXML
    private void setOffset() {

    }

    @FXML
    private void getHour() {
        //        String hour = heliostatController.getHour(heliostat.getComLineId(), heliostat.getId());
        //        byte[] hourBytes = hour.getBytes();
        //        tfHour.setText("" + hourBytes[3] / 256 + ":" + hourBytes[4] / 256 + ":" + hourBytes[5] / 256 + "" + hourBytes[6] / 256 + ":" + hourBytes[7]/256 + ":" + hourBytes[8]/256);
        //        tfDate.setText("" + hourBytes[3] + ":" + hourBytes[4] + ":" + hourBytes[5] + "" + hourBytes[6]  + ":" + hourBytes[7] + ":" + hourBytes[8]);
        //        String day = heliostatController.getDate(heliostat.getComLineId(), heliostat.getId());
        //        tfDate.setText(String.format(hour, ""));
        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        tfDate.setText(day.format(date));
        tfHour.setText(hour.format(date));
    }

    @FXML
    private void setHour() {
        SimpleDateFormat day = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        heliostatController.setHour(heliostat.getComLineId(), heliostat.getId());
    }

    public void refreshHeliostat(Heliostat heliostat) {
        this.heliostat.setAttributes(heliostat);
        setSkins();
        setValues();
    }

    private void setSkins() {
        setSkinState0();
        setSkinState1();
        setSkinEventCom();
    }

    public void setValues() {
        if (valuesScene != null && valuesScene.getWindow().isShowing()) {
            tfAddress.setText(tooltip.getText());
            tfModbus.setText(String.format("%d - %d", heliostat.getComLineId(), heliostat.getId()));
            tfWarning.setText(heliostat.state1ToString());
            tfStatusCode.setText(heliostat.state0ToString());
            tfOperation.setText(heliostat.eventOperationToString());
            tfSecurity.setText(heliostat.eventSecurityToString());
            tfCom.setText(heliostat.eventComToString());
            tfCL.setText(heliostat.eventCLToString());
            tfAz.setText(String.valueOf(heliostat.getPositionAZ()));
            tfEl.setText(String.valueOf(heliostat.getPositionEL()));
            tfMotAz.setText(heliostat.diagnosisAz0ToString());
            tfMotEl.setText(heliostat.diagnosisEl0ToString());
            tfStatusReachedAz.setText(heliostat.diagnosisAz1ToString());
            tfStatusReachedEl.setText(heliostat.diagnosisEl1ToString());
            tfSwingAz.setText(heliostat.diagnosisAz2ToString());
            tfSwingEl.setText(heliostat.diagnosisEl2ToString());
            tfNotAz.setText(heliostat.diagnosisAz3ToString());
            tfNotEl.setText(heliostat.diagnosisEl3ToString());
            iEl1.setRotate(heliostat.getPositionEL());
            iAz.setRotate(heliostat.getPositionAZ());
        }
    }

    /**
     * It changes the button GUI skin according to its first nibble state.
     */
    public void setSkinState0() {
        int nibble0 = 0x0f & heliostat.getState();
        switch (nibble0) {
            case 0x0:
                //                Operación local
                button.getStyleClass().set(1, "hand");
                break;
            case 0x1:
                //                Consiga fija
                button.getStyleClass().set(1, "pin");
                break;
            case 0x2:
                //                Busqueda de ceros
                button.getStyleClass().set(1, "zero");
                break;
            case 0x3:
                //                Fuera de servicio
                button.getStyleClass().set(1, "cross");
                break;
            case 0x4:
                //                Posición de defensa
                button.getStyleClass().set(1, "shield");
                break;
            case 0x5:
                //                Abatimiento
                button.getStyleClass().set(1, "pin");
                break;
            case 0x6:
                //                Blanco tierra
                button.getStyleClass().set(1, "ground");
                break;
            case 0x7:
                //                Blanco pasillo 1
                button.getStyleClass().set(1, "one");
                break;
            case 0x8:
                //                Blanco pasillo 2
                button.getStyleClass().set(1, "two");
                break;
            case 0x9:
                //                Blanco pasillo 3
                button.getStyleClass().set(1, "three");
                break;
            case 0xa:
                //                Blanco pasillo 4
                button.getStyleClass().set(1, "four");
                break;
            case 0xb:
                //                Seguimiento desfasado
                button.getStyleClass().set(1, "kilter");
                break;
            case 0xc:
                //                Blanco de emergencia
                button.getStyleClass().set(1, "triangle");
                break;
            case 0xd:
                //                Seguimiento normal a caldera
                button.getStyleClass().set(1, "boil");
                break;
            case 0xe:
                //                Foco
                button.getStyleClass().set(1, "focus");
                break;
            case 0xf:
                //                Seguimiento normal al sol
                button.getStyleClass().set(1, "solar");
                break;
            default:
                button.getStyleClass().set(1, "pin");
                break;
        }
    }

    /**
     * It changes the button GUI skin according to its first nibble state.
     */
    public void setSkinState1() {
        int nibble1 = 0xf0 & heliostat.getState();
        if ((nibble1 & 0x00) == 0x00) {
            button.getStyleClass().set(2, "radius-green");
        }
        if ((nibble1 & 0x10) == 0x10) {
            //            Consigna alcanzada AZ
            button.getStyleClass().set(2, "radius-green");
        }
        if ((nibble1 & 0x20) == 0x20) {
            //            Consigna alcanzada EL
            button.getStyleClass().set(2, "radius-green");
        }
        if ((nibble1 & 0x40) == 0x40) {
            //            Aviso evento
            button.getStyleClass().set(2, "radius-yellow");
        }
        if ((nibble1 & 0x80) == 0x80) {
            //            Aviso error
            button.getStyleClass().set(2, "radius-red");
        }
    }

    public void setSkinEventCom() {
        int coupleBits2 = 0x30 & heliostat.getEvent();
        switch (coupleBits2) {
            case 0x00:
                button.getStyleClass().set(3, "blue");
                break;
            case 0x10:
                //                Fallo de comunicaciones
                button.getStyleClass().set(3, "black");
                break;
            case 0x20:
                //                No acpeta el comando
                button.getStyleClass().set(3, "grey");
                break;
        }
    }
}
