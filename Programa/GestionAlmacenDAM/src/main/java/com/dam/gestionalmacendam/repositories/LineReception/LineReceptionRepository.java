package com.dam.gestionalmacendam.repositories.LineReception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineReception;
import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LineReceptionRepository  implements LineReceptionInterface{

    private static LineReceptionRepository instance;
    private final DataBaseManager dataBaseManager;

    private LineReceptionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static LineReceptionRepository getInstance(){
        if(instance==null){
            instance = new LineReceptionRepository(DataBaseManager.getInstance());
        }
        return instance;
    }

    @Override
    public ObservableList findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineReception";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        ObservableList listLineReception = null;
        if (result.next()){
            StringProperty RLIC = new SimpleStringProperty(result.getString("RLIC"));
            StringProperty articlePIC =  new SimpleStringProperty(result.getString("articleº"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));;
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));;
            StringProperty belongsRecepcion = new SimpleStringProperty(result.getString("belongsReception"));

            LineReception lineReception = new LineReception(RLIC, articlePIC, load, unitPrice, totalPrice, belongsRecepcion);
            listLineReception.add(lineReception);
        }
        dataBaseManager.close();
        return listLineReception;
    }

    @Override
    public Optional save(Object entity) throws SQLException {
        LineReception lineReception = ((LineReception)entity);
        dataBaseManager.open();
        String query = "Insert into LineReception values (?, ?, ?, ?, ?, ?);";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                lineReception.getRLIC(),
                lineReception.getArticlePIC(),
                lineReception.getLoad(),
                lineReception.getUnitPrice(),
                lineReception.getTotalPrice(),
                lineReception.getBelongsRecepcion()
        );
        dataBaseManager.close();
        return resultado ;
    }

    @Override
    public Optional update(Object o, Object entity) throws SQLException {
        LineReception lineReception = ((LineReception)entity);
        dataBaseManager.open();
        String query = "Update LineReception set articleº = ? , load = ? " +
                ", unitPrice = ?, totalPrice = ?, belongsReception = ? where  RLIC = ? ;";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                lineReception.getRLIC(),
                lineReception.getArticlePIC(),
                lineReception.getLoad(),
                lineReception.getUnitPrice(),
                lineReception.getTotalPrice(),
                lineReception.getBelongsRecepcion());
        dataBaseManager.close();

        return resultado ;
    }

    @Override
    public Optional SerachByReceptionsBelong(Object identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineReception where RILC = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        Optional<LineReception> lineReception= null;
        if (result.next()){
            StringProperty RLIC = new SimpleStringProperty(result.getString("RLIC"));
            StringProperty articlePIC =  new SimpleStringProperty(result.getString("articlePIC"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));;
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));;
            StringProperty belongsRecepcion = new SimpleStringProperty(result.getString("belongsRecepcion"));

            lineReception = Optional.of(new LineReception(RLIC, articlePIC, load, unitPrice, totalPrice, belongsRecepcion));

        }
        dataBaseManager.close();
        return lineReception;
    }
}
