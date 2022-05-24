package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.Optional;


public class EditarSuplierController {
    private static Supplier s;
    private static EditarSuplierController instance;
    SupplierRepository repository = SupplierRepository.getInstance(DataBaseManager.getInstance());


    public static void createInstance(Supplier supplier) {
        instance = new EditarSuplierController();
        s=supplier;
    }
    public static EditarSuplierController getInstance(){
        return instance;
    }

    @FXML
    private TextField areaD;


    @FXML
    private TextField areaTelefono;

    @FXML
    private TextField areaemail;

    @FXML
    private TextField areanombre;


    @FXML
    void initialize(){
        areaD.setText(s.getDirection());
        areaTelefono.setText(s.getTelephoneNumber());
        areanombre.setText(s.getNameSupplier());
        areaemail.setText(s.getEmail());
    }

    @FXML
    void onAceptarAction(ActionEvent event) {
        boolean datosOk = comprobarDatos();
        if(datosOk){
            try{


                guardarSuplier();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmacion");
                alert.setContentText("El suplier se ha modificado");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    alert.close();
                } else {
                    alert.close();
                }
            }catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmacion");
                alert.setContentText("El suplier NO se ha modificado");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    alert.close();
                } else {
                    alert.close();
                }


                System.out.println("no se ha podido aptualizar");
            }

        }
    }

    private void guardarSuplier() throws SQLException {
        s.setTelephoneNumber(areaTelefono.getText());
        s.setEmail(areaemail.getText());
        s.setNameSupplier(areanombre.getText());
        s.setDirection(areaD.getText());
        repository.update(s.getSIC(),s);
    }

    private boolean comprobarDatos() {
        String errorMessage = "";

        if (areanombre.getText() == null || areanombre.getText().isBlank()) {
            errorMessage += "Debes introducir nombre";
        }
        if (areaD.getText() == null || areaD.getText().isBlank()) {
            errorMessage += "Debes introducir la direcion";
        }
        if (areaTelefono.getText() == null || areaTelefono.getText().isBlank()) {
            errorMessage += "Debes introducir un telefono";

        }else{

            //ver Jeremy
           // errorMessage += "Debes introducir un telefono valido";
        }
        if (areaemail.getText() == null || areaemail.getText().isBlank()) {
            errorMessage += "Debes introducir email";
        }else{
           // errorMessage += "Debes introducir email valido";
        }

        return errorMessage.length() == 0;
    }
}
