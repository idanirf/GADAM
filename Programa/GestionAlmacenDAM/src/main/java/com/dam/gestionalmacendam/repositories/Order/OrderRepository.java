package com.dam.gestionalmacendam.repositories.Order;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderRepository implements OrderInterface {
    private static OrderRepository instance;
    private final DataBaseManager dataBaseManager;


    private OrderRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static OrderRepository getInstance(){
        if(instance==null){
            instance = new OrderRepository (DataBaseManager.getInstance());
        }
        return instance;
    }

    @Override
    public ObservableList findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from Order";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        ObservableList listOrder = null;
        if (result.next()){
            StringProperty OIC = new SimpleStringProperty(result.getString("OIC"));
            StringProperty customerCIC =  new SimpleStringProperty(result.getString("Customer "));
            DoubleProperty price = new SimpleDoubleProperty(result.getDouble("Price"));;
            StringProperty methodPayTemporal = new SimpleStringProperty(result.getString("Pay"));

            ObjectProperty<Pay> methodPay = transformMetodetails(methodPayTemporal);

            Order order = new Order(OIC, customerCIC, price, methodPay);
            listOrder.add(order);
        }
        dataBaseManager.close();
        return listOrder;
    }

    private ObjectProperty<Pay> transformMetodetails(StringProperty methodPayTemporal) {
        ObjectProperty<Pay> pay ;
        if(methodPayTemporal.equals("CARD")){
            pay =new SimpleObjectProperty(Pay.CARD);
        }else{
            pay = new SimpleObjectProperty( Pay.PAYPAL);
        }
        return pay;
    }

    @Override
    public Optional save(Object entity) throws SQLException {
        Order Order = ((Order)entity);
        dataBaseManager.open();
        String query = "Insert into Order values (?, ?, ?, ?);";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                Order.getOIC(),
                Order.getCustomerCIC(),
                Order.getPrice(),
                Order.getMethodPay()
        );
        dataBaseManager.close();
        return resultado ;
    }

    @Override
    public Optional update(Object o, Object entity) throws SQLException {
        Order order = ((Order)entity);
        dataBaseManager.open();
        String query = "Update Order set Customer = ?, Price = ? , Pay = ? where  OIC = ? ;";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                order.getCustomerCIC(),
                order.getPrice(),
                order.getMethodPay(),
                entity);
        dataBaseManager.close();

        return resultado ;
    }

    @Override
    public Optional shearchByUuid(Object identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from Order where OIC = ?;";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        Optional<Order> order = null;
        if (result.next()){
            StringProperty uuid = new SimpleStringProperty(result.getString("OIC"));
            StringProperty customerCIC =  new SimpleStringProperty(result.getString("Customer"));
            DoubleProperty price = new SimpleDoubleProperty(result.getDouble("Price"));;
            StringProperty methodPay =new SimpleStringProperty(result.getString("Pay"));

            ObjectProperty<Pay> methodPayFinal = transformMetodetails(methodPay);

            order = Optional.of(new Order(uuid, customerCIC, price, methodPayFinal));

        }
        dataBaseManager.close();
        return order;
    }
}
