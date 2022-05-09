package com.dam.gestionalmacendam.repositories.LinesRecepcion;

import java.sql.SQLException;
import java.util.Optional;

public interface SerachByReceptionBelongs <T,ID> {
    Optional<T> SerachByReceptionBelongs(ID identifier) throws SQLException;
}