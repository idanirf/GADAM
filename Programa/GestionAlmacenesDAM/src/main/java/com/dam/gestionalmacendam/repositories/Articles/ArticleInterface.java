package com.dam.gestionalmacendam.repositories.Articles;

import com.dam.gestionalmacendam.repositories.CRUDRepository;
import com.dam.gestionalmacendam.repositories.SearchByUuid;

public interface ArticleInterface extends CRUDRepository, SeachByName, SearchByUuid {
}
