package com.dam.gestionalmacendam.repositories.LineOrder;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LineOrderRepository implements LineOrderInterface<LineOrder,String> {
    private static LineOrderRepository instance;
    private final DataBaseManager dataBaseManager;
    private final ObservableList<LineOrder> repository = FXCollections.observableArrayList();

    private LineOrderRepository(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    public DataBaseManager getDb(){
        return dataBaseManager;
    }

    public static LineOrderRepository getInstance(DataBaseManager dataBaseManager){
        if(instance ==null){
            instance = new LineOrderRepository(dataBaseManager);
        }
        return instance;
    }

    @Override
    public ObservableList<LineOrder> findAll() throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineOrder";
        ResultSet result = dataBaseManager.select(query).orElseThrow(SQLException::new);

        while (result.next()){
            String OLIC= result.getString("OLIC");
            String articlePIC =  result.getString("article");
            Integer load = result.getInt("load");
            Double unitPrice = result.getDouble("unitPrice");;
            Double totalPrice = result.getDouble("totalPrice");;
            String belongsOrder =result.getString("belongsOrder");

            LineOrder lineOrder = new LineOrder(OLIC, articlePIC, load, unitPrice, belongsOrder);
            repository.add(lineOrder);
        }
        dataBaseManager.close();
        return repository;
    }

    @Override
    public Optional<LineOrder> save(LineOrder lineOrder) throws SQLException {
        dataBaseManager.open();
        String query = "Insert into LineOrder (OLIC, article, load, unitPrice, totalPrice, BelongsOrder) " +
                "values (?, ?, ?, ?, ?, ?);";
        ResultSet resultado = dataBaseManager.insert(query,
                lineOrder.getOLIC(),
                lineOrder.getArticle(),
                lineOrder.getLoad(),
                lineOrder.getUnitPrice(),
                lineOrder.getTotalPrice(),
                lineOrder.getBelongsOrder()
        ).orElseThrow(()-> new SQLException("Error al obtener las lineas."));
        dataBaseManager.close();
        return Optional.of(lineOrder) ;
    }

    @Override
    public Optional<LineOrder> update(String olic,  LineOrder lineOrder) throws SQLException {
        var line= findByUuid(olic);
        int index= repository.indexOf(line);
        dataBaseManager.open();
        String query = "Update LineOrder set article = ? ,  load = ? " +
                ", unitPrice = ?, totalPrice = ?, BelongsOrder = ? where  OLIC = ? ;";
        dataBaseManager.update(query,
                lineOrder.getArticle(),
                lineOrder.getLoad(),
                lineOrder.getUnitPrice(),
                lineOrder.getTotalPrice(),
                lineOrder.getBelongsOrder(),
                olic);
        dataBaseManager.close();
        repository.set(index,lineOrder);
        return Optional.of(lineOrder) ;
    }

    @Override
    public LineOrder findByUuid(String uuid) throws SQLException {
        var repo= findAll();
        return repo.stream().filter(lineOrder-> lineOrder.getOLIC().equals(uuid)).findFirst().orElseThrow(() -> new SQLException("No existe"));
    }

    @Override
    public ObservableList<LineOrder> searchByUuidOrder(String identifier) throws SQLException {
        dataBaseManager.open();
        String query = "select * from LineOrder where belongsOrder = ?";
        ResultSet result = dataBaseManager.select(query, identifier).orElseThrow(()-> new SQLException("No existe lineas con el identificador."));
        repository.clear();
        while (result.next()){
            String OLIC= result.getString("OLIC");
            String articlePIC =  result.getString("article");
            Integer load = result.getInt("load");
            Double unitPrice = result.getDouble("unitPrice");;
            Double totalPrice = result.getDouble("totalPrice");;
            String belongsOrder =result.getString("belongsOrder");

            LineOrder lineOrder = new LineOrder(OLIC, articlePIC, load, unitPrice, belongsOrder);
           repository.add(lineOrder);
        }
        dataBaseManager.close();
        return repository;

    }
}
