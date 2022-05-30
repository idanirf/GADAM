package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LineaPedidoControler {
    private Order order ;
    LineOrderRepository repository = LineOrderRepository.getInstance(DataBaseManager.getInstance());



    @FXML
    private TextField precioTotalPedido;

    @FXML
    private TableView<LineOrder> tablaPedidos;
    @FXML
    private TableColumn<LineOrder, Integer> tablaCantidadArticulo;

    @FXML
    private TableColumn<LineOrder, String> tablaNombreArticulo;


    @FXML
    private TableColumn<LineOrder, String> tablaPicArticulo;

    @FXML
    private TableColumn<LineOrder, Double> tablaPrecioArticulo;

    @FXML
    private TableColumn<LineOrder, Double> tablaTotalLinea;
    public void setOrder(Order order){
        this.order=order;
        initData();
    }

    private void initData(){
        try {
            loadData();
        } catch (SQLException e) {
            System.out.println("No se ha podido cargar la lista de personas");
        }

        tablaCantidadArticulo.setCellValueFactory(cellData -> cellData.getValue().getLoad().asObject());
        tablaTotalLinea.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());
        tablaNombreArticulo.setCellValueFactory(cellData -> cellData.getValue().getArticle());
        tablaPicArticulo.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getOLIC()));
        tablaPrecioArticulo.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asObject());


        tablaPedidos.getSelectionModel().selectFirst();

    }

    private void loadData() throws SQLException {
        tablaPedidos.setItems(repository.searchByUuidOrder(order.getOIC()));
    }



}
