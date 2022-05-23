package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RecepcionController {

    LineReceptionRepository repo = LineReceptionRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    TextField unitPrice;
    @FXML
    TextField load;
    @FXML
    TextField article;
    @FXML
    TextField cost;
    @FXML
    TextField carrier;
    @FXML
    TextField suplierName;

    private Stage dialogStage;
    private boolean aceptarClicked = false;
    private Reception reception;

    private LineReception lineReception;
    public void setReception(Reception recepcion, LineReception lineReception) {
        this.reception=recepcion;
        this.lineReception=lineReception;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    private void initialize(){
    }
    public boolean isAceptarClicked() {
        return aceptarClicked;
    }

    public void onAcept() {
        reception.setSupplierName(new SimpleStringProperty(suplierName.getText()));
        reception.setCarrier(new SimpleStringProperty(carrier.getText()));
        reception.setCost(new SimpleDoubleProperty(Double.parseDouble(cost.getText())));
        lineReception = new LineReception(article.getText(),Integer.parseInt(load.getText()),Double.parseDouble(unitPrice.getText()),reception.getRIC());
        try {
            repo.save(lineReception);
        }catch(SQLException e){
            e.printStackTrace();
        }
        aceptarClicked = true;
        dialogStage.close();
    }

    public void onCancel() {
        System.out.println(("Has pulsado Cancelar"));
        dialogStage.close();
    }


}
