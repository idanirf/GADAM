package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.HelloApplication;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Employee;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.employee.EmployeeRepository;

import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class NewSuplierController {
   SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private TextField areaD;

    @FXML
    private TextField areaSIC;

    @FXML
    private TextField areaTelefono;

    @FXML
    private TextField areaemail;

    @FXML
    private TextField areanombre;


    @FXML
    public void onAceptarAction(ActionEvent event) {
        boolean datosValidos = coprobarDatos();
        if (datosValidos) {
            saveSuplier();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmacion");
            alert.setContentText("El suplier se ha creado");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Datos no validos.");
            alert.showAndWait();
        }
    }



    @FXML
    private boolean coprobarDatos() {
        String errorMessage = "";

        if (areanombre.getText() == null || areanombre.getText().isBlank() || !Patterns.patternName(areanombre.getText())) {
            errorMessage += "Debes introducir nombre correcto. ";
        }
        if (areaD.getText() == null || areaD.getText().isBlank()) {
            errorMessage += "Debes introducir la direcion correcta. ";
        }
        if (areaTelefono.getText() == null || areaTelefono.getText().isBlank() || !Patterns.patterPhone(areaTelefono.getText())) {
            errorMessage += "Debes introducir un telefono correcto. ";

        }
        if (areaemail.getText() == null || areaemail.getText().isBlank()) {
            errorMessage += "Debes introducir email correcto. ";
        }

        if(errorMessage.length()!=0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error en los datos");
            alert.setHeaderText("Datos introducidos incorrectos");
            alert.setContentText("Hay datos incorrectos");
            alert.setContentText(errorMessage);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }

        }
        return errorMessage.length() == 0;

    }

    @FXML
    private void saveSuplier() {
        try {
            repository.save(
                    new Supplier(
                            areaSIC.getText(),
                            areanombre.getText(),
                            areaD.getText(),
                            areaTelefono.getText(),
                            areaemail.getText()));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
