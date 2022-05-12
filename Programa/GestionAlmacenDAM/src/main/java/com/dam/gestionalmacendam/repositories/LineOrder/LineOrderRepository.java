package com.dam.gestionalmacendam.repositories.LineOrder;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.Order;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LineOrderRepository implements LineOrderInterface {
    private static LineOrderRepository instance;
    private final DataBaseManager dataBaseManager;
    private final ObservableList<LineOrder> repository = FXCollections.observableArrayList();

    private LineOrderRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public static LineOrderRepository getInstance(DataBaseManager instance){
        if(LineOrderRepository.instance ==null){
            LineOrderRepository.instance = new LineOrderRepository(DataBaseManager.getInstance());
        }
        return LineOrderRepository.instance;
    }

    @Override
    public ObservableList findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineOrder";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);

        while (result.next()){
            StringProperty OLIC = new SimpleStringProperty(result.getString("OLIC"));
            StringProperty articlePIC =  new SimpleStringProperty(result.getString("articleº"));
            IntegerProperty load = new SimpleIntegerProperty(result.getInt("load"));
            DoubleProperty unitPrice = new SimpleDoubleProperty(result.getDouble("unitPrice"));;
            DoubleProperty totalPrice = new SimpleDoubleProperty(result.getDouble("totalPrice"));;
            StringProperty belongsOrder = new SimpleStringProperty(result.getString("belongsOrder"));

            LineOrder lineOrder = new LineOrder(OLIC, articlePIC, load, unitPrice, totalPrice, belongsOrder);
            repository.add(lineOrder);
        }
        dataBaseManager.close();
        return repository;
    }

    @Override
    public Optional save(Object entity) throws SQLException {
        LineOrder lineOrder = ((LineOrder)entity);
        dataBaseManager.open();
        String query = "Insert into LineOrder values (?, ?, ?, ?, ?, ?);";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                lineOrder.getOLIC().toString(),
                lineOrder.getArticle().toString(),
                lineOrder.getLoad().intValue(),
                lineOrder.getUnitPrice().doubleValue(),
                lineOrder.getBelongsOrder().toString()
        );
        dataBaseManager.close();
        return Optional.of(entity) ;
    }

    @Override
    public Optional update(Object o, Object entity) throws SQLException {
        LineOrder lineOrder = ((LineOrder)entity);
        dataBaseManager.open();
        String query = "Update LineOrder set articleº = ? , load = ? " +
                ", unitPrice = ?, totalPrice = ?, belongsOrder = ? where  OLIC = ? ;";
        Optional<ResultSet> resultado = dataBaseManager.insert(query,
                lineOrder.getOLIC().toString(),
                lineOrder.getArticle().toString(),
                lineOrder.getLoad().intValue(),
                lineOrder.getUnitPrice().doubleValue(),
                lineOrder.getBelongsOrder().toString());
        dataBaseManager.close();

        return resultado ;
    }

    @Override
    public Optional findByUuid(Object identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineOrder where OLIC = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
       LineOrder lineOrder = null;
        if (result.next()){
            lineOrder = (new LineOrder( result.getString("OLIC"),
                    result.getString("articleº"),
                    result.getInt("load"),
                    result.getDouble("unitPrice"),
                    result.getString("belongsOrder")));

        }
        dataBaseManager.close();
        return Optional.of(lineOrder);
    }

    @Override
    public Optional searchByUuidOrder(Object identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineOrder where belongOrder = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(SQLException::new);
        Optional<LineOrder> lineOrder = null;
        if (result.next()){
            lineOrder = Optional.of(new LineOrder( result.getString("OLIC"),
                    result.getString("articleº"),
                    result.getInt("load"),
                    result.getDouble("unitPrice"),
                    result.getString("belongsOrder")));

        }
        dataBaseManager.close();
        return lineOrder;
    }
}
