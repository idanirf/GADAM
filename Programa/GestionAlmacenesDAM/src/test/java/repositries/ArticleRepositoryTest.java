package repositries;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleRepositoryTest {
    private static final ArticleRepository repository= ArticleRepository.getInstance(DataBaseManager.getInstance());

    @BeforeAll
     static void initDataTest(){
        Article a = new Article(
                new SimpleStringProperty("Armario"),
                new SimpleStringProperty("grande"),
                new SimpleStringProperty("sala a"),
                new SimpleDoubleProperty(35.50D),
                new SimpleIntegerProperty(5),
                new SimpleBooleanProperty(false));
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
        ObservableList res = repository.findAll();

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
        Optional<Article> res = repository.searchByName("Armario");

        assertAll(
                () -> assertTrue(res.isPresent()),
                () -> assertTrue((BooleanSupplier) res.get().getArticle().isEqualTo("Armario"))
        );
    }
    /**
     * @Test buscar un Article del repositorio por el PIC del articulo repositorio
     */
    @Test
    void searchByUuidTest() throws SQLException {
        //cojemos el Articulo con un name y de ahi escojemos el uuid
        Optional<Article> a = repository.searchByName("Armario");
        Optional<Article> res = repository.searchByUuid(a.get().getPIC());

        assertAll(
                () -> assertTrue(res.isPresent()),
                () -> assertTrue((BooleanSupplier) res.get().getPIC().isEqualTo(a.get().getPIC())),
                () -> assertTrue((BooleanSupplier) res.get().getDescription().isEqualTo(a.get().getDescription()))
        );
    }

    /**
     * @Test salbar Article en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {

        assertAll(
                () -> assertTrue((BooleanSupplier) dataBaseManager.s.get().getPIC().isEqualTo(a.get().getPIC())),
                () -> assertTrue((BooleanSupplier) res.get().getDescription().isEqualTo(a.get().getDescription()))
        );
    }

    /**
     * @Test cargar los datos nuevos del Article en el repositorio
     */
    @Test
    void updateTest() throws SQLException {
        //cojemos el Articulo con un name y de ahi escojemos el uuid
        Optional<Article> a = repository.searchByName("Armario");
        Optional<Article> res = repository.searchByUuid(a.get().getPIC());

        assertAll(
                () -> assertTrue(res.isPresent()),
                () -> assertTrue((BooleanSupplier) res.get().getPIC().isEqualTo(a.get().getPIC())),
                () -> assertTrue((BooleanSupplier) res.get().getDescription().isEqualTo(a.get().getDescription()))
        );
    }





}
