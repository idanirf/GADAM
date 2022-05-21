package com.dam.gestionalmacendam.managers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.controllers.AcercaDeController;
import com.dam.gestionalmacendam.utils.Properties;
import com.dam.gestionalmacendam.utils.Resources;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import views.Views;

import java.io.IOException;

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

    public void initAcercaDe() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(Views.ACERCA_DE.get()));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Acerca de");
        stage.setResizable(false);
        fxmlLoader.<AcercaDeController>getController().setDialogStage(stage);
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void initAPPManager(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/MenuManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GADAM Gestión de Almacenes");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));

        stage.setScene(scene);
        stage.show();
    }

    public void initAPPEmployee(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("views/MenuEmployee.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("GADAM Gestión de Almacenes");
        stage.getIcons().add(new Image(Resources.get(HelloApplication.class, Properties.APP_ICON)));
        stage.setScene(scene);
        stage.show();
    }

}
