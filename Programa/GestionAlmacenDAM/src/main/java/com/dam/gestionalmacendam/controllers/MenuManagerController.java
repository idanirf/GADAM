package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MenuManagerController {
    private Stage dialogStage;
    @FXML
    private Label user;

    @FXML
    public void onSalirAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Está seguro que desea salir?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
        } else {
            alert.close();
        }
    }

    @FXML
    public void onAcercaDe() {
        try {
            SceneManager.get().initAcercaDe();
        } catch (Exception e) {
            System.out.println("Operación No Disponible");
        }
    }


    @FXML
    public void openOperationEmployee() {
        try {
            SceneManager.get().initEmployee();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
