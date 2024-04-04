package com.example.otobussirketiotomasyonu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("giris-view.fxml"));
        stage.setTitle("Giriş Ekranı");
        stage.setScene(new Scene(root));
        stage.show();


    }

    public static void main(String[] args) {
        launch(args);
}
}