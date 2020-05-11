package cesa.controller;

import cesa.model.dao.HeliostatDAO;
import cesa.view.Message;
import javafx.application.Platform;

import java.io.IOException;

public class HeliostatController {

    private HeliostatDAO heliostatDAO = new HeliostatDAO();

    public String command(int comLineId, int heliostatId, String command) {
        try {
            return heliostatDAO.send("http://localhost:8080/command?comLineId=" + comLineId + "&heliostatId=" + heliostatId + "&command=" + command);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String focus(int comLineId, int heliostatId, int focus) {
        try {
            return heliostatDAO.send("http://localhost:8080/focus?comLineId=" + comLineId + "&heliostatId=" + heliostatId + "&focus=" + focus);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String newFocus(int comLineId, int heliostatId, int focus, int x, int y, int z) {
        try {
            return heliostatDAO.send("http://localhost:8080/newFocus?comLineId=" + comLineId + "&heliostatId=" + heliostatId + "&focus=" + focus + "&x=" + x + "&y=" + y + "&z=" + z);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String getOffsetAz(int comLineId, int heliostatId) {
        try {
            return heliostatDAO.send("http://localhost:8080/getOffsetAz?comLineId=" + comLineId + "&heliostatId=" + heliostatId);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String getOffsetEl(int comLineId, int heliostatId) {
        try {
            return heliostatDAO.send("http://localhost:8080/getOffsetEl?comLineId=" + comLineId + "&heliostatId=" + heliostatId);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String setAz(int comLineId, int heliostatId, int az) {
        try {
            return heliostatDAO.send("http://localhost:8080/setAzimuth?comLineId=" + comLineId + "&heliostatId=" + heliostatId + "&azimuth=" + az);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String setEl(int comLineId, int heliostatId, int el) {
        try {
            return heliostatDAO.send("http://localhost:8080/setElevation?comLineId=" + comLineId + "&heliostatId=" + heliostatId + "&elevation=" + el);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String getHour(int comLineId, int heliostatId) {
        try {
            return heliostatDAO.send("http://localhost:8080/getHour?comLineId=" + comLineId + "&heliostatId=" + heliostatId);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }

    public String setHour(int comLineId, int heliostatId) {
        try {
            return heliostatDAO.send("http://localhost:8080/setHour?comLineId=" + comLineId + "&heliostatId=" + heliostatId);
        } catch (IOException e) {
            Platform.runLater(() -> {
                Message.show(e.getMessage());
            });
            return "No response.";
        }
    }
}
