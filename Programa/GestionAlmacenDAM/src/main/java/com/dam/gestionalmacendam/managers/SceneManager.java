package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.utils.Properties;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import views.Views;

import java.io.IOException;
import java.util.Objects;

public class SceneManager {
    private static SceneManager instance;
    private final Class<?> appClass;

    private Stage mainStage;

    private SceneManager(Class<?> appClass) {
        this.appClass = appClass;
    }

    public static SceneManager getInstance(Class<?> appClass) {
        if (instance == null) {
            instance = new SceneManager(appClass);
        }
        return instance;
    }

    public static SceneManager get() {
        return instance;
    }

    public void changeScene(Node node, Views view) throws IOException {
        Stage stage = (Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(appClass.getResource(view.get())));
        Scene newScene = new Scene(root, Properties.APP_WIDTH, Properties.APP_HEIGHT);
        stage.setScene(newScene);
        stage.show();
    }


}
