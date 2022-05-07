package com.dam.gestionalmacendam.repositories.Orders;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.CRUDRepository;
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
    public Optional update(Object o, Object entity) throws SQLException {
        Order order = ((Order)entity);
        dataBaseManager.open();
        String query = "Update Order set CustomerCIC = ?, Price = ? , MethodPay = ? where  OIC = ? ;";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                order.getCustomerCIC(),
                order.getPrice(),
                order.getMethodPay().toString(),
                order.getOIC());

        dataBaseManager.close();

        return resultado ;
    }

    @Override
    public Optional searchByUuid(Object identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from Order where OIC = ?;";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        Optional<Order> order = null;
        if (result.next()){
            StringProperty uuid = new SimpleStringProperty(result.getString("UUID"));
            StringProperty customerCIC =  new SimpleStringProperty(result.getString("customer"));
            DoubleProperty price = new SimpleDoubleProperty(result.getDouble("price"));;
            String methodPay =result.getString("methodPay");

            ObjectProperty<Pay> methodPayFinal = getPayProperty(methodPay);

            order = Optional.of(new Order(uuid, customerCIC, price, methodPayFinal));

        }
        dataBaseManager.close();
        return order;
    }
}
