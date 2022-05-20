package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;


import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class pedidoManagerViewController implements Initializable {

    //todo por que no va el logger
    //Logger logger = LogManager.getLogger(pedidoManagerViewController.class);
    OrderRepository orderRepository = OrderRepository.getInstance(DataBaseManager.getInstance());




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

        @FXML
        private TextField textAreaBuscarPorOic;

        @FXML
        void buscaPorOIC(InputMethodEvent event) {

        }
        @FXML
        private Button butonVerDetalle;

        @FXML
        void onButonVerDetalle(MouseEvent event) {

            Order  order = tablaPedidos.getSelectionModel().getSelectedItem();
            try {
                SceneManager.get().initConsultarUno(order);

            }catch (Exception e){
                System.out.println("no se ha podido consultar uno");
            }

        }

    @FXML
    private void loadData() throws SQLException {

        //logger.info("Cargando datos de Pedidos...");
        System.out.println("cargando datos de personas");
        tablaPedidos.setItems(orderRepository.findAll());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Cargo la lista de personas en base al observable
        try {
            loadData();
        } catch (SQLException e) {
            System.out.println("No se ha podido cargar la lista de personas");
        }

        // Asigno las columnas de la tabla
        columnaOIC.setCellValueFactory(cellData -> cellData.getValue().OICProperty());
        columnaCliente.setCellValueFactory(cellData -> cellData.getValue().customerProperty());
        //columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        columnaMetodoDePago.setCellValueFactory(cellData -> cellData.getValue().methodPayProperty());

        tablaPedidos.getSelectionModel().selectFirst();


    }

    @FXML
    public void onSalirAction() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setContentText("¿Salir de Agenda?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Hacemos el backup!!
           // backup();
            Platform.exit();
        } else {
            alert.close();
        }
    }
}
