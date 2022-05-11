package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleRepositoryTest {
      ArticleRepository repository= ArticleRepository.getInstance(DataBaseManager.getInstance());
      Article a = new Article("Prueva1","grande","sala a",35.50D,5,false);

    @BeforeAll
     void initDataTest(){

        try{
            repository.save(a);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("objeto ya en el repositorio");
        }
    }


    /**
     * @Test para mostrar todos los articulos del repositorio
     */
    @Test
    void findAllTest() throws SQLException {
        //todo si funciona
        ObservableList<Article> res = repository.findAll();

        assertAll(
                () -> assertTrue(res.size()>0),
                () -> assertFalse(res.size()==0)
        );
    }

    /**
     * @Test buscar un Article del repositorio por el nombre del articulo repositorio
     */
    @Test
    void findByNameTest() throws SQLException {
        //todo hacer
        Optional<Article> res = repository.findByName(a.getArticle().toString());
        assertAll(
                () -> assertNotNull(res.get()),
                () -> assertTrue(res.get().getPIC().toString().equalsIgnoreCase( a.getPIC().toString()))
        );
    }


    /**
     * @Test buscar un Article del repositorio por el PIC del articulo repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {
        //todo hacer no funciona

        Optional<Article> res = (Optional<Article>) repository.findByUuid(a.getPIC().toString());
        Optional<Article> res2 = (Optional<Article>) repository.findByUuid(a.getPIC().toString());
        assertAll(

                () -> assertTrue(res.isPresent()),
                () -> assertTrue(res.get().getArticle().toString().equalsIgnoreCase(res2.get().getArticle().toString())),
                () -> assertTrue( res.get().getPIC().toString().equalsIgnoreCase( res2.get().getPIC().toString()))
                );

    }


    /**
     * @Test salbar Article en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {
        //todo SI funciona

        Article b = new Article("Prueva2","grande","sala a",35.50D,5,false);

            repository.save(b);

        assertAll(
                () -> assertTrue(repository.findByUuid(b.getPIC().toString()).isPresent())
        );
    }

    /**
     * @Test cargar los datos nuevos del Article en el repositorio
     */
    @Test
    void updateTest() throws SQLException {

        a.setArticle(new SimpleStringProperty("mesita"));
        repository.update(a.getPIC().toString(), a);
        assertAll(
                () -> assertTrue(repository.findByUuid(a.getPIC().toString()).isPresent())

        );
    }





}
