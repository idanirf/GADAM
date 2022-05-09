package com.dam.gestionalmacendam.repositories.LinesOrder;

import com.dam.gestionalmacendam.repositories.CRUDRepository;
import com.dam.gestionalmacendam.repositories.SearchByUuid;

public interface LineOrderInterface extends CRUDRepository, SearchByOrderBelongs, SearchByUuid {
}
