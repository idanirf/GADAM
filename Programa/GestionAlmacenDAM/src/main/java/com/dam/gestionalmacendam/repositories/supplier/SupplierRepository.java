package com.dam.gestionalmacendam.repositories.supplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Optional;


public class SupplierRepository implements ICRUDSupplier {
    private static SupplierRepository instance;
    private final ObservableList<Supplier> repository = FXCollections.observableArrayList();
    DataBaseManager bbdd = DataBaseManager.getInstance();

    public DataBaseManager getBbdd(){
        return bbdd;
    }
    private SupplierRepository() {
        //Constructor vacío
    }

    public static SupplierRepository getInstance(DataBaseManager instance) {
        if (SupplierRepository.instance == null) {
            SupplierRepository.instance = new SupplierRepository();
        }
        return SupplierRepository.instance;
    }

    @Override
    public ObservableList<Supplier> findAll() throws SQLException {
        String sql = "SELECT * FROM Supplier";
        bbdd.open();
        ResultSet resultado = bbdd.select(sql).orElseThrow(() -> new SQLException("Se ha producido un error obteniendo" +
                " los datos"));
        repository.clear();
        while (resultado.next()) {
            repository.add(
                    new Supplier(
                            resultado.getString("SIC"),
                            resultado.getString("nameSupplier"),
                            resultado.getString("direction"),
                            resultado.getString("telephoneNumber"),
                            resultado.getString("email")
                    )
            );
        }
        bbdd.close();
        return repository;
    }

    @Override
    public Optional<Supplier> save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO Supplier (SIC, nameSupplier, direction, telephoneNumber, email) VALUES (?, ?, ?, ?, ?)";
        bbdd.open();
        System.out.println("bbdd abierta");
        bbdd.insert(sql, supplier.getSIC(), supplier.getNameSupplier(), supplier.getDirection(), supplier.getTelephoneNumber(), supplier.getEmail());
        bbdd.close();
        return Optional.of(supplier);
    }

    @Override
    public Optional<Supplier> update(String uuid, Supplier supplier) throws SQLException {
        int i = repository.indexOf(supplier);
        String sql = "UPDATE Supplier SET nameSupplier = ?, direction = ?, telephoneNumber = ?, email = ? WHERE SIC = ?";
        bbdd.open();
        bbdd.update(sql, supplier.getNameSupplier(), supplier.getDirection(), supplier.getTelephoneNumber(), supplier.getEmail());
        return Optional.of(supplier);
    }

    public Supplier findByUUID(String SIC) throws SQLException {
        var resultado = findAll();
        return resultado.stream().filter(supplier -> supplier.getSIC().equals(SIC)).findFirst().orElseThrow(
                () -> new SQLException("No existe ningún proveedor con ese SIC"));
    }
}
