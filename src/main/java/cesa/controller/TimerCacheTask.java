package cesa.controller;

import cesa.model.ComLine;
import cesa.view.Primary;
import javafx.application.Platform;

import java.util.TimerTask;

public class TimerCacheTask extends TimerTask {

    private Primary primary;

    private ComLine comLine;

    public TimerCacheTask(Primary primary) {
        this.primary = primary;
    }

    @Override
    public void run() {
        refreshRows();
    }

    private void refreshRows() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                primary.refresRows();
            }
        });
    }
}