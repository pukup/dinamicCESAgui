package cesa.view;

import javafx.scene.control.Alert;

public class Message {

    private static Alert error = new Alert(Alert.AlertType.ERROR);
    private static Alert information = new Alert(Alert.AlertType.INFORMATION);

    public static void showExit(String contenido) {
        error.setHeaderText(null);
        error.setContentText(contenido);
        error.showAndWait();
        System.exit(1);
    }

    public static void show(String contenido) {
        information.setHeaderText(null);
        information.setContentText(contenido);
        information.showAndWait();
    }
}
