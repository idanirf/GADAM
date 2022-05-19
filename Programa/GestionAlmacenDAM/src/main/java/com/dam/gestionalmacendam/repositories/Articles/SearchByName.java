package com.dam.gestionalmacendam.repositories.Articles;


import java.sql.SQLException;
import java.util.Optional;

public interface SearchByName<T,ID> {
    Optional<T> findByName(ID name) throws SQLException;
}