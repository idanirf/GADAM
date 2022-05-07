package com.dam.gestionalmacendam.repositories.Recepcions;

import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.Optional;

public interface ReceptionInterface <T , ID>{
    ObservableList<T> findAll() throws SQLException;

    Optional<T> save(T entity) throws SQLException;
}
