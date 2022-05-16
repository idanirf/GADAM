package com.dam.gestionalmacendam.repositories.Reception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Reception;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReceptionRepository implements ReceptionInterface {
    private static ReceptionRepository instance;
    private final ObservableList<Reception> repository = FXCollections.observableArrayList();
    private final DataBaseManager dataBaseManager;


    private ReceptionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public DataBaseManager getDb(){
        return dataBaseManager;
    }

    public static ReceptionRepository getInstance(DataBaseManager instance) {
        if (ReceptionRepository.instance == null) {
            ReceptionRepository.instance = new ReceptionRepository(DataBaseManager.getInstance());
        }
        return ReceptionRepository.instance;
    }

    @Override
    public ObservableList<Reception> findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from Reception";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        ObservableList listReception ;
        repository.clear();
        while (result.next()) {

            repository.add(
                    new Reception(
                            result.getString("RIC"),
                            result.getString("Supplier"),
                            result.getString("Carrier"),
                            result.getDouble("Cost")
                    ));

        }
        dataBaseManager.close();
        return repository;
    }


    @Override
    public Optional save(Object entity) throws SQLException {
        Reception reception = ((Reception) entity);
        dataBaseManager.open();
        String query = "Insert into Reception(RIC, Supplier, Carrier, Cost) values (?, ?, ?, ?);";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                reception.getRIC().toString(),
                reception.getSupplierSIC().toString(),
                reception.getCarrier().toString(),
                reception.getCost().doubleValue()
        );
        dataBaseManager.close();
        return resultado;
    }


}

