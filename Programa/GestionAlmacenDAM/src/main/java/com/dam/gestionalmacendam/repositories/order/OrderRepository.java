package com.dam.gestionalmacendam.repositories.order;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderRepository implements ICRUDOrder {
    private static OrderRepository instance;
    private final ObservableList<Order> repository = FXCollections.observableArrayList();
    DataBaseManager bbdd = DataBaseManager.getInstance();


    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }
    @Override
    public ObservableList<Order> findAll() throws SQLException {
        String sql = "SELECT * FROM Order";
        bbdd.open();
        ResultSet resultado = bbdd.select(sql).orElseThrow(()-> new SQLException("Se ha producido un error obteniendo los datos"));
        repository.clear();
        while(resultado.next()){
            repository.add(
                    new Order(
                            resultado.getString("OIC"),
                            resultado.getObject("Customer"),
                            resultado.getDouble("price"),
                            resultado.getObject("MethodPay")
                    )
            );
        }
        bbdd.close();
        return  repository;
    }

    @Override
    public Optional<Order> save(Order order) throws SQLException {
        String sql = "INSERT INTO Order (OIC, Customer, Price, Pay) values (?, ?, ?, ?)";
        bbdd.open();
        System.out.println("BBDD abierta");
        bbdd.insert(sql, order.getOIC(), order.getCustomer(), order.getPrice(), order.getMethodPay());
        bbdd.close();
        return Optional.of(order);
    }

    @Override
    public Optional<Order> update(String uuid, Order order) throws SQLException {
        int i = repository.indexOf(order);
        String sql = "UPDATE Order SET OIC = ?, Customer = ?, Price = ?, Pay = ? WHERE OIC = ?";
        bbdd.open();
        bbdd.update(sql, order.getCustomer(), order.getPrice(), order.getMethodPay());
        return Optional.of(order);
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

    public Order findByUUID(String OIC) throws SQLException {
        return repository.stream().filter(order ->  order.getOIC().equals(OIC)).findFirst().orElseThrow(
                ()-> new SQLException("No existen pedidos con ese OIC"));
    }
}
