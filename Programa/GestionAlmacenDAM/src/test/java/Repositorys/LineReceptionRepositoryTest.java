package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.models.LineReception;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import com.dam.gestionalmacendam.repositories.LineReception.LineReceptionRepository;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LineReceptionRepositoryTest {
    LineReceptionRepository repository = LineReceptionRepository.getInstance(DataBaseManager.getInstance());
    LineReception linereception = new LineReception("delete","nuevo", 3, 30D, "pertenece a");


    @BeforeAll
    public void initDataTest() throws SQLException {
        repository.save(linereception);
    }
    @BeforeEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM LineReception WHERE RLIC=?";
        db.open();
        db.delete(query, linereception.getRLIC());
        db.close();
    }

    @Test
    public void findAllTest() throws SQLException {


        ObservableList<LineReception> l = repository.findAll();

        assertAll(
                () -> assertNotNull(l),
                () -> assertFalse(l.size() < 1)
        );
    }

    @Test
    public void saveTest() throws SQLException {

        ObservableList li = repository.SerachByReceptionsBelong(linereception.getBelongsRecepcion().toString());
        int sice= li.size();
        LineReception lineR = new LineReception("delete","otro", 3, 30D, "pertenece a");
        repository.save(lineR);
        ObservableList li2 = repository.SerachByReceptionsBelong(linereception.getBelongsRecepcion().toString());


        assertAll(
                () -> assertNotNull(li),
                () -> assertEquals(sice,li2.size()-1)
        );

    }

    @Test
    public void updateTest() throws SQLException {
        String s = linereception.getBelongsRecepcion().toString();
        ObservableList l = repository.SerachByReceptionsBelong(s);
        int size = l.size();
        linereception.setBelongsRecepcion(new SimpleStringProperty("otra nueva"));
        repository.update(linereception.getRLIC().toString(),linereception);
        ObservableList l2 = repository.SerachByReceptionsBelong(s);

        assertAll(
                () -> assertTrue(size!=l2.size()),
                () -> assertNotSame(s, linereception.getBelongsRecepcion().toString())
        );

    }


    @Test
    public void searchByUuidReception() throws SQLException {
        ObservableList<LineReception> res = repository.SerachByReceptionsBelong(linereception.getBelongsRecepcion().toString());
        System.out.println(res.size());
        Optional<LineReception> l = res.stream().findFirst();
        System.out.println(l);
        ObservableList<LineReception> res2 = repository.SerachByReceptionsBelong(l.get().getBelongsRecepcion().toString());
        System.out.println(res2.size());

        assertAll(

                () -> assertNotNull(res),
                () -> assertTrue(res.size()==res2.size()),
                () -> assertTrue(res.get(0).getBelongsRecepcion().toString().equalsIgnoreCase(res2.get(0).getBelongsRecepcion().toString()))
        );

    }
}
