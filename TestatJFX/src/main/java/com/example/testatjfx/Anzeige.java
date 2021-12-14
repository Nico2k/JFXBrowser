package com.example.testatjfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Anzeige extends VBox {

    private ProgressBar progressBar = new ProgressBar();
    private Text message = new Text();

    public Anzeige(NavigationBar navigationBar){
        progressBar.setPrefWidth(1000);

        navigationBar.getWebView().getEngine().getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State state, Worker.State t1) {
                progressBar.progressProperty().bind(navigationBar.getWebView().getEngine().getLoadWorker().progressProperty());

                message.textProperty().bind(navigationBar.getWebView().getEngine().getLoadWorker().messageProperty());

                if (navigationBar.getWebView().getEngine().getLoadWorker().progressProperty().doubleValue() == 1.0){
                    progressBar.setVisible(false);
                }else{
                    progressBar.setVisible(true);
                }
            }
        });

        this.getChildren().addAll(progressBar, message);
        this.setSpacing(5);
    }

}
