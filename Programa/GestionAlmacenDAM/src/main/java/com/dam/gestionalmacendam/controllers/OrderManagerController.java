package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
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

    @FXML
    private Button butonVerDetalle;

    @FXML
    void onButonVerDetalle(MouseEvent event) throws SQLException {

        String s = textAreaBuscarPorOic.getText();
        var lista = repository.findAll().filtered(x -> x.getOIC().contains(s));

        if(lista.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Pedido no selecionado");
            alert.setContentText("No ha selecionado ningun Pedido para ver su detalle");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                alert.close();
            } else {
                alert.close();
            }
        }else{
            try{
                SceneManager.get().initLineOrderView(lista);
            }catch(Exception e){
                System.out.println("no se ha podido cargar el line order view");
            }
        }





    }
    


    @FXML
    void errorDeBudqueda(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Pedido selecionado no existe o incorrecto");
        alert.setContentText("No ha selecionado ningun pedido o  su pedido selecionado no existe o incorrecto");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        } else {
            alert.close();
        }
    }

    @FXML
    private void initialize() {

         try {
             loadData();
         } catch (SQLException e) {
             System.out.println("No se ha podido cargar la lista de personas");
         }

        columnaOIC.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getOIC()));
        columnaCliente.setCellValueFactory(cellData -> cellData.getValue().getCustomer());
        columnaMetodoDePago.setCellValueFactory(cellData -> cellData.getValue().getMethodPay());
        columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        tablaPedidos.getSelectionModel().selectFirst();

    }
    @FXML
    private void loadData() throws SQLException {
        System.out.println("Cargando datos...");
        if(repository.findAll().size()==0){
            System.out.println("Repositorio vacio a 0");

        }else{
            System.out.println("Repositorio NO vacio");
            System.out.println(repository.findAll());
        }
        tablaPedidos.setItems(repository.findAll());

    }

    public void selecionarAcion(MouseEvent mouseEvent) {
        Order o = tablaPedidos.getSelectionModel().getSelectedItem();
        textAreaBuscarPorOic.setText(o.getOIC());
    }



    public void vertodos(ActionEvent actionEvent) throws SQLException {
        tablaPedidos.setItems(repository.findAll());
    }

    public void findByName(ActionEvent actionEvent) throws SQLException {
        String name = textAreaBuscarPorOic.getText();
        if(name.isEmpty()){
            loadData();
        }else{
            tablaPedidos.setItems(repository.findAll().filtered(x -> x.getCustomer().get()
                    .toLowerCase().contains(name)|| x.getOIC().toUpperCase().contains(name)));
        }
        tablaPedidos.refresh();
    }

}
