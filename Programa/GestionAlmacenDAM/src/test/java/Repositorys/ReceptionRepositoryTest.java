package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.models.Reception;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import com.dam.gestionalmacendam.repositories.Reception.ReceptionRepository;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReceptionRepositoryTest {
    ReceptionRepository repository= ReceptionRepository.getInstance(DataBaseManager.getInstance());
    Reception o = new Reception("delete","ReceptionTest", "ReceptionTest",55D );


    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM Reception WHERE RIC=?";
        db.open();
        db.delete(query, o.getRIC().get());
        db.close();
    }

    /**
     * @Test para mostrar todos las Receptions del repositorio
     */
    @Test
    void findAllTest() throws SQLException {

        repository.save(o);
        ObservableList<Reception> res = repository.findAll();

        assertAll(
                () -> assertTrue(res.size()>0)

        );
    }

    /**
     * @Test salbar Order en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {

        ObservableList<Reception> res = repository.findAll();
        repository.save(o);
        ObservableList<Reception> res1 = repository.findAll();

        assertAll(
                () -> assertTrue(repository.findAll().size()>0),
                () -> assertEquals(res.size(), res1.size())
        );
    }


}