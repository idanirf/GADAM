package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ResumenReceptionController {


    LineReceptionRepository repo = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    TableColumn<LineReception,String> articleCol;
    @FXML
    TableColumn<LineReception, Integer> cantCol;
    @FXML
    TableColumn<LineReception, Double> unitCol;
    @FXML
    TableColumn<LineReception, Double> totalCol;
    @FXML
    TableColumn<LineReception, String> recepCol;
    @FXML
    TableColumn<LineReception,String> rlic;
    @FXML
    TableView<LineReception> recepcionView;
    @FXML
    TextField suplierName;
    @FXML
    TextField carrier;
    @FXML
    TextField cost;

    private Reception reception;
    public void setRecepcion(Reception recepcion) throws SQLException {
        this.reception= recepcion;
        setDataInfo();
        loadLineReception();
        initColumns();
    }

    private void initColumns() {
        articleCol.setCellValueFactory(data -> data.getValue().articlePICProperty());
        cantCol.setCellValueFactory(data -> data.getValue().loadProperty().asObject());
        unitCol.setCellValueFactory(data -> data.getValue().unitPriceProperty().asObject());
        totalCol.setCellValueFactory(data -> data.getValue().totalPriceProperty().asObject());
        recepCol.setCellValueFactory(data -> data.getValue().belongsRecepcionProperty());
        rlic.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRLIC()));

    }

    private void loadLineReception() throws SQLException {
        recepcionView.setItems(repo.findAll().filtered(z -> z.belongsRecepcionProperty().get().contains(reception.getRIC())));
    }


    private void setDataInfo() {
        suplierName.setText(reception.getSupplierName().get());
        carrier.setText(reception.getCarrier().get());
        cost.setText(reception.getCost().asObject().get().toString());







    }


}
