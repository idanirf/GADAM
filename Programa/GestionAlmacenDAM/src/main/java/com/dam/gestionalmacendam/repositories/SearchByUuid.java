package com.dam.gestionalmacendam.repositories;

import java.sql.SQLException;
import java.util.Optional;

public interface SearchByUuid<T, ID> {
    Optional<T> findByUuid(ID identifier) throws SQLException;
}