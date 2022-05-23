package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;

import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class aEditarSuplierController {
    private static aEditarSuplierController instance;
    private static Supplier suplier;
    SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());

    public aEditarSuplierController(DataBaseManager instance) {
    }

    public static aEditarSuplierController createInstance(Supplier s) {

        instance = new aEditarSuplierController(DataBaseManager.getInstance());
        suplier = s;
        return instance;
    }

    public static aEditarSuplierController get() {
        return instance;
    }



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
        if (isDataValid()) {
            saveEmployee();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("");
            alert.setHeaderText("Datos no validos.");
            alert.setContentText("Seleccione Aceptar los Términos.");
            alert.showAndWait();
        }
    }

    @FXML
    private boolean isDataValid() {
        String errorMessage = "";
        if (areaSIC.getText() == null || areaSIC.getText().isBlank()) {
            errorMessage += "Debes introducir el sic";
        }
        if (areanombre.getText() == null || areanombre.getText().isBlank()) {
            errorMessage += "Debes introducir nombre";
        }
        if (areaD.getText() == null || areaD.getText().isBlank()) {
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

    @FXML
    private void saveEmployee() {
        suplier.setDirection(areaD.getText());
        suplier.setEmail(areaemail.getText());
        suplier.setNameSupplier(areanombre.getText());
        suplier.setTelephoneNumber(areaTelefono.getText());
        try{
            repository.update(suplier.getSIC().get(), suplier);
        }catch (Exception e){
            System.out.println("no guarda los datos");
        }

    }


}


