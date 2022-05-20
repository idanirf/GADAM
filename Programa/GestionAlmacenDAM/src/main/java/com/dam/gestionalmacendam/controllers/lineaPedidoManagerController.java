package com.dam.gestionalmacendam.controllers;
import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class lineaPedidoManagerController {
    LineOrderRepository lineOrderRepository = LineOrderRepository.getInstance(DataBaseManager.getInstance());


    @FXML
    private TableView<LineOrder> tablaPedidos;

    @FXML
    private TextField oicConsultado;

    @FXML
    private MenuBar optionSalir;

    @FXML
    private TextField precioTotalPedido;

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


    public void setOederData(Order order) {
        //logger.info("Cargando datos de Linea Orer...");
        System.out.println("cargando datos de LineOrder");
        try{
            tablaPedidos.setItems(lineOrderRepository.searchByUuidOrder(order.getOIC().get()));
            tablaCantidadArticulo.setCellValueFactory(lineOrder -> lineOrder.getValue().loadProperty() );
        }catch(Exception e){
            System.out.println("no ha sido posible cargar datos");
        }

        columnaMetodoDePago.setCellValueFactory(cellData -> cellData.getValue().methodPayProperty());

    }
}
