package com.dam.gestionalmacendam.controllers;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Customer;
import com.dam.gestionalmacendam.repositories.customer.CustomerRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class CustomerViewController {
    private CustomerRepository customerRepository= CustomerRepository.getInstance(DataBaseManager.getInstance());
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer,String> tbCic;
    @FXML
    private TableColumn<Customer,String> tbName;
    @FXML
    private TableColumn<Customer,String> tbSurname;
    @FXML
    private TableColumn<Customer,String> tbCif;
    @FXML
    private TableColumn<Customer,String> tbDirection;
    @FXML
    private TableColumn<Customer,String> tbNick;
    @FXML
    private TableColumn<Customer,String> tbPhone;
    @FXML
    private TableColumn<Customer,String> tbEmail;
    @FXML
    private TableColumn<Customer, LocalDateTime> tbCreateAt;
    @FXML
    private TableColumn<Customer,Customer> tbActive;
    public void findByCIC(ActionEvent actionEvent) {
    }
    @FXML
    private void initialize(){
        try {
            loadData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tbName.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getName()));
        tbSurname.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getSurname()));
        tbCic.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getCIC()));
        tbCif.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getCif()));
        tbDirection.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getDirection()));
        tbNick.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getNickName()));
        tbPhone.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getTelephoneNumber()));
        tbEmail.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getEmail()));
        tbCreateAt.setCellValueFactory(data-> new SimpleObjectProperty<LocalDateTime>(data.getValue().getCreatedAt()));
        tbActive.setCellValueFactory(data-> new SimpleObjectProperty<>(data.getValue()));
        setCustomerPrueba();


//        customerTable.getSelectionModel().selectedItemProperty().addListener(
//                (observable, oldValue, newValue) -> onCustomerSelected(newValue)
//        );

    }

    private void setCustomerPrueba(){
        tbActive.setCellFactory(param -> new TableCell<>(){
            @Override
            public void updateItem(Customer item, boolean empty){
                if (item!=null){
                    CheckBox check= new CheckBox();
                    check.setDisable(true);
                    if (item.isActive()){
                        check.setSelected(true);
                    }else{
                        check.setSelected(false);
                    }
                    setGraphic(check);
                }

            }
        });
    }

    private void loadData() throws SQLException {
        customerTable.setItems(customerRepository.findAll());
    }
}
