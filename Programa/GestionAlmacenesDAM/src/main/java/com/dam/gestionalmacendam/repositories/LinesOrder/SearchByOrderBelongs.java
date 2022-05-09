package com.dam.gestionalmacendam.repositories.LinesOrder;

import java.sql.SQLException;
import java.util.Optional;

public interface SearchByOrderBelongs<T,ID>  {
    Optional<T> searchByUuidOrder(ID identifier) throws SQLException;
}
