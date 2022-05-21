package com.dam.gestionalmacendam.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;

import java.util.Optional;


public class OrderManagerController {
    //mi repositorio de order



    //todo por que no va el logger
    //Logger logger = LogManager.getLogger(pedidoManagerViewController.class);


    @FXML
    private TableView<?> tablaPedidos;

    @FXML
    private TableColumn<?, ?> columnaCliente;

    @FXML
    private TableColumn<?, ?> columnaMetodoDePago;

    @FXML
    private TableColumn<?, ?> columnaOIC;

    @FXML
    private TableColumn<?, ?> columnaPrecio;

    @FXML
    private TextField textAreaBuscarPorOic;


    @FXML
    private Button butonVerDetalle;

    @FXML
    void onButonVerDetalle(MouseEvent event) {


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


}
