package com.dam.gestionalmacendam.repositories.LineReception;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.LineReception;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LineReceptionRepository  implements LineReceptionInterface{

    private static LineReceptionRepository instance;
    private final DataBaseManager dataBaseManager;
    private  ObservableList<LineReception> repository = FXCollections.observableArrayList();

    private LineReceptionRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static LineReceptionRepository getInstance(DataBaseManager instance){
        if(LineReceptionRepository.instance ==null){
            LineReceptionRepository.instance = new LineReceptionRepository(DataBaseManager.getInstance());
        }
        return LineReceptionRepository.instance;
    }

    @Override
    public ObservableList findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineReception";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);
        repository.clear();
        if (result.next()){
            StringProperty RLIC = new SimpleStringProperty(result.getString("RLIC"));
            StringProperty articlePIC =  new SimpleStringProperty(result.getString("articleº"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));;
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));;
            StringProperty belongsRecepcion = new SimpleStringProperty(result.getString("belongsReception"));

            LineReception lineReception = new LineReception(RLIC, articlePIC, load, unitPrice, totalPrice, belongsRecepcion);
            repository.add(lineReception);
        }
        dataBaseManager.close();
        return repository;
    }

    @Override
    public Optional save(Object entity) throws SQLException {
        LineReception lineReception = ((LineReception)entity);
        dataBaseManager.open();
        String query = "Insert into LineReception values (?, ?, ?, ?, ?, ?);";
       ResultSet resultado = dataBaseManager.insert(query,
                lineReception.getRLIC(),
                lineReception.getArticlePIC(),
                lineReception.getLoad(),
                lineReception.getUnitPrice(),
                lineReception.getTotalPrice(),
                lineReception.getBelongsRecepcion()
        ).orElseThrow(SQLException::new);
        dataBaseManager.close();
        return Optional.of(entity) ;
    }

    @Override
    public Optional update(Object o, Object entity) throws SQLException {
        LineReception lineReception = ((LineReception)entity);
        dataBaseManager.open();
        String query = "Update LineReception set articleº = ? , load = ? " +
                ", unitPrice = ?, totalPrice = ?, belongsReception = ? where  RLIC = ? ;";
        int resultado = dataBaseManager.update(query,
                lineReception.getRLIC(),
                lineReception.getArticlePIC(),
                lineReception.getLoad(),
                lineReception.getUnitPrice(),
                lineReception.getTotalPrice(),
                lineReception.getBelongsRecepcion());
        dataBaseManager.close();

        return  Optional.of(resultado);
    }

    @Override
    public ObservableList SerachByReceptionsBelong(Object identifier) throws SQLException {
        repository.clear();
        dataBaseManager.open();
        String query = "select * from LineReception where belongsReception = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        while (result.next()){
            StringProperty RLIC = new SimpleStringProperty(result.getString("RLIC"));
            StringProperty articlePIC =  new SimpleStringProperty(result.getString("articleº"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));;
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));;
            StringProperty belongsRecepcion = new SimpleStringProperty(result.getString("belongsReception"));

            LineReception lineReception = new LineReception(RLIC, articlePIC, load, unitPrice, totalPrice, belongsRecepcion);
            repository.add(lineReception);
        }
        dataBaseManager.close();
        return repository;
    }
}
