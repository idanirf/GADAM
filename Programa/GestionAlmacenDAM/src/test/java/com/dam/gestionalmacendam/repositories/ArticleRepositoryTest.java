package com.dam.gestionalmacendam.repositories;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleRepositoryTest {
      ArticleRepository repository= ArticleRepository.getInstance(DataBaseManager.getInstance());
      Article a = new Article("delete","Prueba1","grande","sala a",35.50D,5,false,"x");

    @BeforeEach
    void initDataTest(){
        try{
            repository.save(a);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("objeto ya en el repositorio");
        }
    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM Article WHERE PIC =?";
        db.open();
        db.delete(query, a.getPIC());
        db.close();
    }


    /**
     * @Test para mostrar todos los articulos del repositorio
     */
    @Test
    void findAllTest() throws SQLException {
        ObservableList<Article> res = repository.findAll();
        Article article = res.get(0);

        assertAll(
                () -> assertTrue(res.size()>0),
                () -> assertFalse(res.size()==0),
                () -> assertEquals(a.getArticle(),article.getArticle())
        );
    }

    /**
     * @Test buscar un Article del repositorio por el nombre del articulo repositorio
     */
    @Test
    void findByNameTest() throws SQLException {
        Article res = repository.findByName(a.getArticle());
        Article res1 = repository.findByUuid(a.getPIC());
        assertAll(
                () ->  assertTrue( res.getArticle().equalsIgnoreCase( res1.getArticle()))
        );
    }

    /**
     * @Test buscar un Article del repositorio por el PIC del articulo repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {
        var aux = repository.findByUuid(a.getPIC());
        assertAll(

                () -> assertEquals(aux.getArticle(),a.getArticle()),
                () -> assertEquals(aux.getPIC(),a.getPIC()),
                () -> assertEquals(aux.getPrice(),a.getPrice()),
                () -> assertEquals(aux.getDescription(),a.getDescription())
                );

    }


    /**
     * @Test salbar Article en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {
        var a1= repository.findByUuid(a.getPIC());
        assertAll(
                ()->assertEquals(a1.getArticle(),a.getArticle()),
                ()->assertEquals(a1.getPrice(),a.getPrice()),
                ()->assertEquals(a1.getDescription(),a.getDescription()),
                ()->assertEquals(a1.getStock(),a.getStock())
        );
    }

    /**
     * @Test cargar los datos nuevos del Article en el repositorio
     */
    @Test
    void updateTest() throws SQLException {
        a.setArticle("mesita");
        var aux=repository.update(a.getPIC(), a);
        assertAll(
                ()->assertEquals(aux.get().getArticle(),a.getArticle()),
                ()->assertEquals(aux.get().getPrice(),a.getPrice()),
                ()->assertEquals(aux.get().getDescription(),a.getDescription()),
                ()->assertEquals(aux.get().getStock(),a.getStock())
        );
    }
}
