package com.dam.gestionalmacendam.repositories.Recepcions;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.CRUDRepository;
import com.dam.gestionalmacendam.repositories.Orders.OrderRepository;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class RecepcionRepository implements ReceptionInterface {
    private static RecepcionRepository instance;
    private final DataBaseManager dataBaseManager;

    private RecepcionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static RecepcionRepository getInstance(){
        if(instance==null){
            instance = new RecepcionRepository (DataBaseManager.getInstance());
        }
        return instance;
    }

    @Override
    public ObservableList findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from Order";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        ObservableList listReception = null;
        if (result.next()){
            StringProperty uuid = new SimpleStringProperty(result.getString("RIC"));
            StringProperty suplierSIC =  new SimpleStringProperty(result.getString("suplierSIC"));
            StringProperty carrier = new SimpleStringProperty(result.getString("carrier"));
            DoubleProperty cost = new SimpleDoubleProperty(result.getDouble("cost"));;

            Reception reception = new Reception(uuid, suplierSIC, carrier, cost);
            listReception.add(reception);
        }
        dataBaseManager.close();
        return listReception;
    }

    private ObjectProperty<Pay> getPayProperty(String methodPay) {
        Pay methodPayOk;
        if (methodPay.equalsIgnoreCase("CARD")) {
            methodPayOk = Pay.CARD;
        } else {
            methodPayOk = Pay.PAYPAL;
        }
        return new SimpleObjectProperty<Pay>(methodPayOk);
    }

    @Override
    public Optional save(Object entity) throws SQLException {
        Reception reception = ((Reception)entity);
        dataBaseManager.open();
        String query = "Insert into Reception values (?, ?, ?, ?);";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                reception.getRIC(),
                reception.getSupplierSIC(),
                reception.getCarrier(),
                reception.getCost());
        dataBaseManager.close();
        return resultado ;
    }
}
