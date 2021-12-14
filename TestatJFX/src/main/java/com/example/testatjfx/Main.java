package com.example.testatjfx;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {

        NavigationBar navigationBar = new NavigationBar(primaryStage);
        MenuLeiste menu = new MenuLeiste(navigationBar);
        Anzeige toolBar = new Anzeige(navigationBar);


        VBox root = new VBox(menu, navigationBar, toolBar);

        root.setSpacing(4);

        primaryStage.setTitle("Browser");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}