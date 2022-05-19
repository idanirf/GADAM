package com.dam.gestionalmacendam.repositories;

import java.sql.SQLException;
import java.util.Optional;

public interface SearchByUuid<T, ID> {
    T findByUuid(ID identifier) throws SQLException;
}
