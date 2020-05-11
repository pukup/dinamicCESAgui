package cesa.controller;

import cesa.model.ComLine;
import cesa.model.dao.ComLineDAO;
import cesa.view.Message;
import javafx.application.Platform;

import java.io.IOException;

public class ComLineController {

    private static ComLineDAO comLineDAO = new ComLineDAO();

    public static ComLine getAPICache(int comLineId) {
        try {
            return comLineDAO.getAPICache("http://localhost:8080/getCache?comLineId=" + comLineId);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.showExit(e.getMessage());
            });
            return null;
        }
    }

    public static int getNumber() {
        try {
            return comLineDAO.getAPINumber("http://localhost:8080/getNumber");
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.showExit(e.getMessage());
            });
            return 0;
        }
    }


}
