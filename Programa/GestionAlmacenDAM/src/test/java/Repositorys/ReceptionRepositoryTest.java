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
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReceptionRepositoryTest {
    ReceptionRepository repository= ReceptionRepository.getInstance(DataBaseManager.getInstance());
    Reception o = new Reception("delete","ReceptionTest", "ReceptionTest",55D );

    @BeforeAll
    void initDataTest(){

        try{
            repository.save(o);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("objeto ya en el repositorio");
        }
    }
    @BeforeEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM Reception WHERE UUID=?";
        db.open();
        db.delete(query, o.getRIC());
        db.close();
    }

    /**
     * @Test para mostrar todos las Receptions del repositorio
     */
    @Test
    void findAllTest() throws SQLException {
        //todo si funciona
        ObservableList res = repository.findAll();

        assertAll(
                () -> assertTrue(res.size()>0),
                () -> assertFalse(res.size()==0)
        );
    }

    /**
     * @Test salbar Order en el  repositorio
     */
    @Test
    void saveTest() throws SQLException {

        int sice = repository.findAll().size();
        Reception o2 = new Reception("ReceptionTest", "ReceptionTest",55D );
        repository.save(o2);
        int sice2 = repository.findAll().size();

        assertAll(
                () -> assertNotNull(repository.findAll()),
                () -> assertEquals(sice+1,sice2)
        );
    }


}