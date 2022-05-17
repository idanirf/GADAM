package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LineOrderRepositoryText {
    LineOrderRepository repository = LineOrderRepository.getInstance(DataBaseManager.getInstance());
    LineOrder lineOrder = new LineOrder("delete","lineaOrder prueva1",1,1.00,"no pertenece" );

    @BeforeEach
    public void initDataTest() throws SQLException {
        repository.save(lineOrder);
    }

    @AfterEach
    void setDown() throws SQLException {
        var db = repository.getDb();
        String query = "DELETE FROM LineOrder WHERE OLIC=?";
        db.open();
        db.delete(query, lineOrder.getOLIC().get());
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

        Optional<LineOrder> li = repository.findByUuid(lineOrder.getOLIC().get());
        Optional<LineOrder> li2 = repository.findByUuid(lineOrder.getOLIC().get());
        Optional<LineOrder> li3 = Optional.of(lineOrder);

        assertAll(
                () ->assertNotNull(li),
                () ->assertEquals(li.get().getOLIC().get(),(li2.get().getOLIC().get())),
                () ->assertEquals(li3.get().getArticle().get(),lineOrder.getArticle().get())
        );

    }
    @Test
    public void  updateTest() throws SQLException {
        lineOrder.setLoad(8);
        repository.update(lineOrder.getOLIC().get(),lineOrder);
        Optional<LineOrder> li = repository.findByUuid(lineOrder.getOLIC().get());

        assertAll(
                () ->assertNotNull(li),
                () ->assertTrue(li.get().getOLIC().get().equalsIgnoreCase(lineOrder.getOLIC().get())),
                () ->assertEquals(li.get().getLoad().get(),8)
        );
    }
    @Test
    public void  findByUuidTest() throws SQLException {
        Optional<LineOrder> res = repository.findByUuid(lineOrder.getOLIC().get());
        Optional<LineOrder> res2 = repository.findByUuid(lineOrder.getOLIC().get());
        assertAll(

                () -> assertTrue(res.isPresent()),
                () -> assertTrue(res2.isPresent()),
                () -> assertTrue(res.get().getArticle().get().equalsIgnoreCase(res2.get().getArticle().get())),
                () -> assertTrue( res.get().getOLIC().get().equalsIgnoreCase( res2.get().getOLIC().get()))
        );


    }
    @Test
    public void  searchByUuidOrder() throws SQLException {
        Optional<LineOrder> res = repository.findByUuid(lineOrder.getOLIC().get());
        Optional<LineOrder> res2 = repository.findByUuid(lineOrder.getOLIC().get());

        assertAll(

                () ->assertNotNull(res),
                () ->assertTrue(res.get().toString().equalsIgnoreCase(res2.get().toString()))
        );

    }





}
