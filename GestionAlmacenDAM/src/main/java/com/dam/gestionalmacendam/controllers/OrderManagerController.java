package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;


public class OrderManagerController {
    OrderRepository repository = OrderRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    private TextField textAreaBuscarPorOic;

    @FXML
    private TableView<Order> tablaPedidos;

    @FXML
    private TableColumn<Order, String> columnaCliente;

    @FXML
    private TableColumn<Order, Pay> columnaMetodoDePago;

    @FXML
    private TableColumn<Order, String> columnaOIC;

    @FXML
    private TableColumn<Order, Double> columnaPrecio;




    //va bien
    private void initColumns() {
        columnaOIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOIC()));
        columnaCliente.setCellValueFactory(cellData -> cellData.getValue().getCustomer());
        columnaMetodoDePago.setCellValueFactory(cellData -> cellData.getValue().getMethodPay());
        columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().getPrice().asObject());
    }

    //va bien
    private void loadProd() throws SQLException {
        tablaPedidos.setItems(repository.findAll());
    }

    @FXML
    private void findByName() throws SQLException {
        String name = textAreaBuscarPorOic.getText().trim().toLowerCase();
        if (name.isEmpty()) {
            loadProd();
        } else {
            // todo lo que no va
            tablaPedidos.setItems(repository.findAll()
                    .filtered(x -> x.getOIC().toLowerCase().contains((name).toLowerCase()) ||
                            x.getCustomer().toString().contains((name).toLowerCase())));
        }
    }


    @FXML
    private void onClickAction() throws IOException, SQLException {
        // todo no va
        System.out.println("Mostrando Resumen.");
        Order order = tablaPedidos.getSelectionModel().getSelectedItem();
        SceneManager.get().initLineOrderView(order);

    }
    //va bien
    @FXML
    private void initialize() {
        try {
            loadProd();
            tablaPedidos.refresh();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        initColumns();

    }


}
