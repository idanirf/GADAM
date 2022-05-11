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
      Article a = new Article(
            new SimpleStringProperty("Armario"),
            new SimpleStringProperty("grande"),
            new SimpleStringProperty("sala a"),
            new SimpleDoubleProperty(35.50D),
            new SimpleIntegerProperty(5),
            new SimpleBooleanProperty(false));

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
    void searchByNameTest() throws SQLException {
        //todo hacer
        Optional<Article> res = repository.shearchByUuid(a.getPIC());
        Optional<Article> res2 = repository.searchByName(a.getArticle());
        assertAll(
                () -> assertTrue(res.isPresent()),
                () -> assertEquals(res,res2)
        );
    }


    /**
     * @Test buscar un Article del repositorio por el PIC del articulo repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {
        //todo hacer no funciona

        Optional<Article> res = (Optional<Article>) repository.shearchByUuid(a.getPIC());
        Optional<Article> res2 = (Optional<Article>) repository.shearchByUuid(a.getPIC());
        assertAll(

                () -> assertTrue(res.isPresent()),
                () -> assertEquals(res,res2),
                () -> assertEquals(res,res2),
                () -> assertTrue((BooleanSupplier) res.get().getPIC().isEqualTo( res2.get().getPIC()))
                );

    }


    /**
     * @Test salbar Article en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {
        //todo SI funciona

        Article b = new Article(
                new SimpleStringProperty("Armario"),
                new SimpleStringProperty("grande"),
                new SimpleStringProperty("sala a"),
                new SimpleDoubleProperty(35.50D),
                new SimpleIntegerProperty(5),
                new SimpleBooleanProperty(false));

            repository.save(b);

        assertAll(
                () -> assertTrue(repository.shearchByUuid(b.getPIC()).isPresent())
        );
    }

    /**
     * @Test cargar los datos nuevos del Article en el repositorio
     */
    @Test
    void updateTest() throws SQLException {
        //todo SI funciona


        a.setArticle(new SimpleStringProperty("mesita"));
        repository.update(a.getPIC(), a);
        assertAll(
                () -> assertTrue(repository.shearchByUuid(a.getPIC()).isPresent())

        );
    }





}
