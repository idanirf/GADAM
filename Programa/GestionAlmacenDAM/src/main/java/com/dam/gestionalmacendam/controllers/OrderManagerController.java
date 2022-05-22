package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

import java.sql.SQLException;
import java.util.Optional;


public class OrderManagerController {
    //mi repositorio de order
    OrderRepository repository = OrderRepository.getInstance(DataBaseManager.getInstance());



    //todo por que no va el logger
    //Logger logger = LogManager.getLogger(pedidoManagerViewController.class);


    @FXML
    private TableView<Order> tablaPedidos;

    @FXML
    private TableColumn<Order, String> columnaCliente;

    @FXML
    private TableColumn<?, ?> columnaMetodoDePago;

    @FXML
    private TableColumn<Order, String> columnaOIC;

    @FXML
    private TableColumn<Order, Double> columnaPrecio;

    @FXML
    private TextField textAreaBuscarPorOic;


    @FXML
    private Button butonVerDetalle;

    @FXML
    void onButonVerDetalle(MouseEvent event) {
        if (textAreaBuscarPorOic == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pedido no selecionado");
            alert.setContentText("No ha selecionado ningun Pedido para ver su detalle");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
        }


    }

    @FXML
    void buscaPorOIC(InputMethodEvent event) {

    }


    @FXML
    public void salirAction(MouseEvent mouseEvent) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setContentText("¿Está seguro que desea salir?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            } else {
                alert.close();
            }


    }

    @FXML
    private void initialize() {
        // Cargo la lista de personas en base al observable
        try {
            loadData();
        } catch (SQLException e) {
            System.out.println("No se ha podido cargar la lista de personas");
        }

        // Asigno las columnas de la tabla
        columnaOIC.setCellValueFactory(cellData -> cellData.getValue().getOIC());
        columnaCliente.setCellValueFactory(cellData -> cellData.getValue().getCustomer());
        // columnaMetodoDePago.setCellValueFactory(cellData -> cellData.getValue().getClass().);
        //columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().getPrice());

        tablaPedidos.getSelectionModel().selectFirst();

    }
    @FXML
    private void loadData() throws SQLException {
        System.out.println("Cargando datos...");
       tablaPedidos.setItems(repository.findAll());
    }

}
