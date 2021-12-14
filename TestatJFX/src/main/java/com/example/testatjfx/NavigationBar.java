package com.example.testatjfx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class NavigationBar extends VBox {
    private TabPane tabPane = new TabPane();
    private WebView webView = new WebView();
    private TextField url = new TextField();
    private String homePage = "https://www.google.com";
    private Stage primaryStage;

    public NavigationBar(Stage primaryStage){
        this.primaryStage = primaryStage;
        HBox navbar = new HBox();
        navbar.setSpacing(4);
        navbar.setPadding(new Insets(5));

        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observableValue, Tab tab, Tab t1) {
                int activeTab = tabPane.getSelectionModel().getSelectedIndex();
                try{
                    webView = (WebView) tabPane.getTabs().get(activeTab).getContent();
                }catch(IndexOutOfBoundsException e){

                }
                url.textProperty().bind(webView.getEngine().locationProperty());
                /**System.out.println("Ausgabe");**/

            }
        });


        addTab();

        Label label = new Label("Suche...");
        TextField url = new TextField();


        url.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                webView.getEngine().load("https://" + url.getText());

            }
        });

        Button search = new Button("suchen");
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                webView.getEngine().load("https://" + url.getText());
            }
        });

        Button home = new Button("Startseite");
        home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                webView.getEngine().load(homePage);
            }
        });

        Button newTab = new Button("+");
        newTab.setOnAction(e -> addTab());

        Button refresh = new Button("refresh");
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                webView.getEngine().reload();
            }
        });

        navbar.getChildren().addAll(refresh, label, url, search, home, newTab);
        this.getChildren().addAll(navbar, tabPane);

    }

    public Tab addTab(){
        WebView webView1 = new WebView();
        Tab t = new Tab();

        t.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if(tabPane.getTabs().size() == 0){
                    primaryStage.close();
                }
            }
        });

        t.textProperty().bind(webView1.getEngine().titleProperty());
        webView1.getEngine().load(homePage);
        tabPane.getTabs().add(t);
        return t;
    }

    public void setHomePage(String home){
        this.homePage = homePage;
    }
    public String getHomePage(){
        return homePage;
    }
    public WebView getWebView(){
        return webView;
    }
}
