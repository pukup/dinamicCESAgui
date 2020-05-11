module cesa {
    requires javafx.controls;
    requires javafx.fxml;
    requires gson;
    requires java.sql;

    opens cesa.view to javafx.fxml;
    opens cesa.model;
    exports cesa;


}