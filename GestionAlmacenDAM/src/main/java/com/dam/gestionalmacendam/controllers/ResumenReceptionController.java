package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.Printers.HtmlPrinterOrder;
import com.dam.gestionalmacendam.Printers.HtmlPrinterReception;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Optional;

public class ResumenReceptionController {


    LineReceptionRepository repo = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    TableColumn<LineReception, String> articleCol;
    @FXML
    TableColumn<LineReception, Integer> cantCol;
    @FXML
    TableColumn<LineReception, Double> unitCol;
    @FXML
    TableColumn<LineReception, Double> totalCol;
    @FXML
    TableColumn<LineReception, String> recepCol;
    @FXML
    TableColumn<LineReception, String> rlic;
    @FXML
    TableView<LineReception> recepcionView;
    @FXML
    TextField suplierName;
    @FXML
    TextField carrier;
    @FXML
    TextField cost;
    @FXML
    private Button butoncreartiket;

    private Reception reception;

    public void setRecepcion(Reception recepcion) throws SQLException {
        this.reception = recepcion;
        setDataInfo();
        loadLineReception();
        initColumns();
    }

    private void initColumns() {
        articleCol.setCellValueFactory(data -> data.getValue().getArticlePIC());
        cantCol.setCellValueFactory(data -> data.getValue().getLoad().asObject());
        unitCol.setCellValueFactory(data -> data.getValue().getUnitPrice().asObject());
        totalCol.setCellValueFactory(data -> data.getValue().getTotalPrice().asObject());
        recepCol.setCellValueFactory(data -> data.getValue().getBelongsRecepcion());
        rlic.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRLIC()));

    }

    private void loadLineReception() throws SQLException {
        recepcionView.setItems(repo.findAll().filtered(z -> z.getBelongsRecepcion().get().contains(reception.getRIC())));
    }


    private void setDataInfo() {
        suplierName.setText(reception.getSupplierName().get());
        carrier.setText(reception.getCarrier().get());
        cost.setText(reception.getCost().asObject().get().toString());


    }
    @FXML
    void onactioncreartiket(ActionEvent event) {

        HtmlPrinterReception printer = new HtmlPrinterReception(reception);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tiket Realizado");
        alert.setContentText("Puede encontrar su tiket en :\n" +
                " GestionAlmacenDAM\\reception\\ \nRecepcion.uuid_de_la_Recepción.html");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        } else {
            alert.close();
        }
    }

}
