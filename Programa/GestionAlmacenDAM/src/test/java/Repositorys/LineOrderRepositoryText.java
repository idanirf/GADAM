package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LineOrderRepositoryText {
    LineOrderRepository repository = LineOrderRepository.getInstance(DataBaseManager.getInstance());
    LineOrder lineOrder = new LineOrder("delete","lineaOrder prueva1",1,1.00,"no pertenece" );

    @BeforeAll
    public void initDataTest() throws SQLException {
        repository.save(lineOrder);
    }

    @BeforeEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM LineOrder WHERE OLIC=?";
        db.open();
        db.delete(query, lineOrder.getOLIC());
        db.close();
    }

    @Test
    public void  findAllTest() throws SQLException {


        ObservableList<LineOrder> l =repository.findAll();

        assertAll(
                () ->assertNotNull(l),
                () ->assertFalse(l.size()<1)
        );
    }
    @Test
    public void  saveTest() throws SQLException {

        Optional<LineOrder> li = repository.findByUuid(lineOrder.getOLIC().toString());
        Optional<LineOrder> li2 = repository.findByUuid(lineOrder.getOLIC().toString());
        Optional<LineOrder> li3 = Optional.of(lineOrder);

        assertAll(
                () ->assertNotNull(li),
                () ->assertEquals(li.get().getOLIC().toString(),(li2.get().getOLIC().toString())),
                () ->assertEquals(li3.get().getArticle().toString(),lineOrder.getArticle().toString())
        );

    }
    @Test
    public void  updateTest() throws SQLException {
        lineOrder.setLoad(8);
        repository.update(lineOrder.getOLIC().toString(),lineOrder);
        Optional<LineOrder> li = repository.findByUuid(lineOrder.getOLIC().toString());

        assertAll(
                () ->assertNotNull(li),
                () ->assertTrue(li.get().getOLIC().toString().equalsIgnoreCase(lineOrder.getOLIC().toString())),
                () ->assertEquals(li.get().getLoad(),8)
        );
    }
    @Test
    public void  findByUuidTest() throws SQLException {
        Optional<LineOrder> res = repository.findByUuid(lineOrder.getOLIC().toString());
        Optional<LineOrder> res2 = repository.findByUuid(lineOrder.getOLIC().toString());
        assertAll(

                () -> assertTrue(res.isPresent()),
                () -> assertTrue(res2.isPresent()),
                () -> assertTrue(res.get().getArticle().toString().equalsIgnoreCase(res2.get().getArticle().toString())),
                () -> assertTrue( res.get().getOLIC().toString().equalsIgnoreCase( res2.get().getOLIC().toString()))
        );


    }
    @Test
    public void  searchByUuidOrder() throws SQLException {
        Optional<LineOrder> res = repository.findByUuid(lineOrder.getOLIC().toString());
        Optional<LineOrder> res2 = repository.findByUuid(lineOrder.getOLIC().toString());

        assertAll(

                () ->assertNotNull(res),
                () ->assertTrue(res.get().toString().equalsIgnoreCase(res2.get().toString()))
        );

    }





}
