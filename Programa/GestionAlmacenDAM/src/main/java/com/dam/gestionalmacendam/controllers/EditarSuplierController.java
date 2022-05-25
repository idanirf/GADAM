package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import com.dam.gestionalmacendam.repositories.supplier.SupplierRepository;
import com.dam.gestionalmacendam.utils.Patterns;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

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
}
