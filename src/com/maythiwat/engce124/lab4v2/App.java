package com.maythiwat.engce124.lab4v2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Player.fxml"));
        // fxmlLoader.setController(new PlayerScene());
        Scene scene = new Scene(fxmlLoader.load(), 360, 600);

        stage.setTitle("RMUTLSoft Javamp Media Player");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        // center screen hack
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((visualBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((visualBounds.getHeight() - stage.getHeight()) / 2);
    }
}
