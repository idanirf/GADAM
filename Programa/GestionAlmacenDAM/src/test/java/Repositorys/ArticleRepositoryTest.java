package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArticleRepositoryTest {
      ArticleRepository repository= ArticleRepository.getInstance(DataBaseManager.getInstance());
      Article a = new Article("delete","Prueva1","grande","sala a",35.50D,5,false);

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
        db.delete(query, a.getPIC().get());
        db.close();
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
        Optional<Article> res = repository.findByName(a.getArticle().get());
        Optional<Article> res1 = repository.findByUuid(a.getPIC().get());
        assertAll(
                () -> assertNotNull(res.get()),
                () ->  assertTrue( res.get().getArticle().get().equalsIgnoreCase( res1.get().getArticle().get()))
        );
    }


    /**
     * @Test buscar un Article del repositorio por el PIC del articulo repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {
        //todo hacer no funciona

        Optional<Article> res = (Optional<Article>) repository.findByUuid(a.getPIC().get());
        Optional<Article> res2 = (Optional<Article>) repository.findByUuid(a.getPIC().get());
        assertAll(

                () -> assertTrue(res.isPresent()),
                () -> assertTrue(res.get().getArticle().get().equalsIgnoreCase(res2.get().getArticle().get())),
                () -> assertTrue( res.get().getPIC().get().equalsIgnoreCase( res2.get().getPIC().get()))
                );

    }


    /**
     * @Test salbar Article en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {
        //todo SI funciona


        assertAll(
                () -> assertTrue(repository.findByUuid(a.getPIC().get()).isPresent())
        );
    }

    /**
     * @Test cargar los datos nuevos del Article en el repositorio
     */
    @Test
    void updateTest() throws SQLException {

        a.setArticle(new SimpleStringProperty("mesita"));
        repository.update(a.getPIC().get(), a);
        assertAll(
                () -> assertTrue(repository.findByUuid(a.getPIC().get()).isPresent())

        );
    }





}
