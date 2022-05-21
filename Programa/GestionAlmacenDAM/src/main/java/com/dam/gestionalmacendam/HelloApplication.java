package com.dam.gestionalmacendam;

import com.dam.gestionalmacendam.managers.SceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance(HelloApplication.class);
        sceneManager.initAPPManager(stage);
        sceneManager.initAPPEmployee(stage);
    }

}