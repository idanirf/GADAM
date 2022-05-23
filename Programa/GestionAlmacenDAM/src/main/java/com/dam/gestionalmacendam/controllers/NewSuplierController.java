package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;

import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class NewSuplierController {
    private final boolean aceptarClicked = false;
   SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    private PasswordField areaDirecion;

    @FXML
    private TextField areaSIC;

    @FXML
    private TextField areaTelefono;

    @FXML
    private TextField areaemail;

    @FXML
    private TextField areanombre;








    public void onAceptarAction(ActionEvent event) {
        boolean datosValidos = coprobarDatos();
        if (datosValidos) {
            saveSuplier();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Datos no validos.");
            alert.showAndWait();
        }
    }

    private boolean coprobarDatos() {
        String errorMessage = "";
        if (areaSIC.getText() == null || areaSIC.getText().isBlank()) {
            errorMessage += "Debes introducir el sic";
        }
        if (areanombre.getText() == null || areanombre.getText().isBlank()) {
            errorMessage += "Debes introducir nombre";
        }
        if (areaDirecion.getText() == null || areaDirecion.getText().isBlank()) {
            errorMessage += "Debes introducir la direcion";
        }
        if (areaTelefono.getText() == null || areaTelefono.getText().isBlank()) {
            errorMessage += "Debes introducir un telefono";
        }
        if (areaemail.getText() == null || areaemail.getText().isBlank()) {
            errorMessage += "Debes introducir email";
        }

        return errorMessage.length() == 0;
    }

    private void saveSuplier() {
        try {
            repository.save(
                    new Supplier(
                            areaSIC.getText(),
                            areanombre.getText(),
                            areaDirecion.getText(),
                            areaTelefono.getText(),
                            areaemail.getText()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
