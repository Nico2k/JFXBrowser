package com.example.testatjfx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.text.Text;




public class MenuLeiste extends MenuBar {
    private Menu data = new Menu("Datei");
    private Menu extra = new Menu("Extras");
    private Menu help = new Menu("Hilfe");

    private MenuItem newTab = new MenuItem("Neuer Tab");
    private MenuItem newWindow = new MenuItem("Neues Fenster");
    private MenuItem settings = new MenuItem("Einstellungen");
    private MenuItem about = new MenuItem("Über");

    private Main main = new Main();


    public MenuLeiste(NavigationBar navigationBar){
        this.getMenus().addAll(data, extra, help);
        data.getItems().addAll(newTab, newWindow);
        extra.getItems().addAll(settings);
        help.getItems().add(about);

        newWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    main.start(new Stage());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        newTab.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                VBox vBox = new VBox();
                vBox.setSpacing(10);
                vBox.setPadding(new Insets(20));

                Label lbl = new Label("Startseite definieren: ");
                TextField txt = new TextField();
                Label lbl1 = new Label("aktuelle Startseite: " + navigationBar.getHomePage());
                txt.setPrefWidth(100);
                Button ok = new Button("Ok");

                lbl.setAlignment(Pos.CENTER);
                lbl1.setAlignment(Pos.CENTER);
                vBox.setAlignment(Pos.CENTER);
                ok.setAlignment(Pos.CENTER);
                vBox.getChildren().addAll(lbl, lbl1, txt, ok);

                Scene scene = new Scene(vBox);
                Stage set = new Stage();
                set.setScene(scene);
                set.initModality(Modality.APPLICATION_MODAL);
                set.show();

                ok.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        navigationBar.setHomePage("https://" + txt.getText());
                        set.close();
                    }
                });
            }
        });

        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                HBox hBox = new HBox();

                VBox vBoxLeft = new VBox();
                VBox vBoxRight = new VBox();

                Stage stage = new Stage();
                Scene scene = new Scene(hBox);
                stage.setScene(scene);

                stage.setTitle("Produktinformationen");
                Text browserName = new Text("ShieldFX");
                Text name = new Text("Name: ");
                Text number = new Text("Matrikelnummer: ");

                Text certificate = new Text("Testataufgabe zur Vorlesung Objektorientierte Programmierung 2");
                Text degreeProgram = new Text("Studiengang: " );
                Text semester = new Text("Semester: " );

                Text studName = new Text("Nicolas Richter");
                Text studNumber = new Text( "00388220");
                Text studDegreeProgram = new Text("Informatik");
                Text studSemester = new Text("WS2021");


                vBoxLeft.getChildren().addAll(certificate, new Label(""), browserName, new Label(""), name, number, degreeProgram, semester);
                vBoxRight.getChildren().addAll(new Label(""), new Label(""), new Label(""), new Label(""), studName, studNumber, studDegreeProgram, studSemester);
                hBox.setPadding(new Insets(20));
                hBox.setSpacing(5);
                hBox.getChildren().addAll(vBoxLeft, vBoxRight);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
        });
    }
}
