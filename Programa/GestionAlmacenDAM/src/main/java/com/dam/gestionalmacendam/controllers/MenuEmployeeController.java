package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Employee;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.Optional;

public class MenuEmployeeController {
    private Employee employee;
    @FXML
    private Label nickname;
    public void setEmployee(Employee employee) {
        this.employee = employee;
        nickname.setText(employee.getNickName());
    }

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
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }

    public void openOperationPedidos() {
        try {
        } catch (Exception e) {
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }


    public void openOperationArticles() {
        try {
            SceneManager.get().initArticleView();
        } catch (Exception e) {
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }

    public void openOperationClientes() {
        try {
            SceneManager.get().initViewCustomer();
        } catch (Exception e) {
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }

    public void openOperationRecepciones() {
        try {
        } catch (Exception e) {
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }

    public void onImportarAction() {
        try {
        } catch (Exception e) {
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }

    public void onExportarAction() {
        try {
        } catch (Exception e) {
            System.out.println("no consigue cargar pantalla acerca de");
        }
    }


}
