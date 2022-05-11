package com.dam.gestionalmacendam.repositories.Reception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Reception;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ReceptionRepository implements ReceptionInterface {
    private static ReceptionRepository instance;
    private final DataBaseManager dataBaseManager;


    private ReceptionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static ReceptionRepository getInstance(DataBaseManager instance){
        if(ReceptionRepository.instance ==null){
            ReceptionRepository.instance = new ReceptionRepository(DataBaseManager.getInstance());
        }
        return ReceptionRepository.instance;
    }

    @Override
    public ObservableList findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from Reception";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        ObservableList listReception = null;
        if (result.next()){
            StringProperty RIC = new SimpleStringProperty(result.getString("RIC"));
            StringProperty supliersIC =  new SimpleStringProperty(result.getString("Suplier "));
            StringProperty carrier= new SimpleStringProperty(result.getString("Carrier"));
            DoubleProperty cost = new SimpleDoubleProperty(result.getDouble("Cost"));

            Reception reception = new Reception(RIC, supliersIC, carrier, cost);
            listReception.add(reception);
        }
        dataBaseManager.close();
        return listReception;
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
                reception.getCost()
        );
        dataBaseManager.close();
        return resultado ;
    }



    //@Override
    // public Optional update(Object o, Object entity) throws SQLException {
    //    Reception reception= ((Reception)entity);
    //    dataBaseManager.open();
    //    String query = "Update Reception set SupplierSIC = ?, Carrier = ? , cost = ? where  RIC = ? ;";
    //    Optional<ResultSet> resultado = dataBaseManager.insert(query,
    //            reception.getSupplierSIC(),
    //            reception.getCarrier(),
    //            reception.getCost(),
    //            entity);
    //    dataBaseManager.close();

    //    return resultado ;
    //}
}
