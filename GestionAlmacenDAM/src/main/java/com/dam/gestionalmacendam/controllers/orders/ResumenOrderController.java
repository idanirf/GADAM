package com.dam.gestionalmacendam.controllers.orders;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.managers.SceneManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class ResumenOrderController {
    private Order o;
    LineOrderRepository repo =  LineOrderRepository.getInstance(DataBaseManager.getInstance());

    @FXML
    TableColumn<LineOrder, String> articleCol;
    @FXML
    TableColumn<LineOrder, Integer> cantCol;
    @FXML
    TableColumn<LineOrder, Double> unitCol;
    @FXML
    TableColumn<LineOrder, Double> totalCol;
    @FXML
    TableView<LineOrder> OrderView;
    @FXML
    private TextField order;
    @FXML
    TextField cost;

    private void initColumns() {
        articleCol.setCellValueFactory(data -> data.getValue().getArticle());
        cantCol.setCellValueFactory(data -> data.getValue().getLoad().asObject());
        unitCol.setCellValueFactory(data -> data.getValue().getUnitPrice().asObject());
        totalCol.setCellValueFactory(data -> data.getValue().getTotalPrice().asObject());
    }

    private void loadLineOrder() throws SQLException {
        OrderView.setItems(repo.findAll().filtered(z -> z.getBelongsOrder().get().contains(o.getOIC().toString())));
    }

    private void setDataInfo() {

        order.setText(o.getOIC().toString());
        cost.setText(o.getPrice().getValue().toString());

    }

    public void setOrder(Order order) throws SQLException {
        System.out.println("entra en resumen");
        this.o = order;
        setDataInfo();
        loadLineOrder();
        initColumns();
    }



}
