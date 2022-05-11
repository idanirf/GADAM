package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Article;
import com.dam.gestionalmacendam.models.Order;
import com.dam.gestionalmacendam.models.Pay;
import com.dam.gestionalmacendam.repositories.Articles.ArticleRepository;
import com.dam.gestionalmacendam.repositories.Order.OrderRepository;
import javafx.beans.property.*;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderRepositoryTest {

        OrderRepository repository= OrderRepository.getInstance(DataBaseManager.getInstance());
        Order o = new Order("pagador", 34.54D, Pay.PAYPAL);

        @BeforeAll
        void initDataTest(){

            try{
                repository.save(o);
            } catch (Exception e){
                e.printStackTrace();
                System.out.println("objeto ya en el repositorio");
            }
        }


        /**
         * @Test para mostrar todos las Orders del repositorio
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
         * @Test buscar una order del repositorio por el UUid de la order del repositorio
         */
        @Test
        void searchByUuidTest() throws SQLException {
            //todo hacer no funciona

            Order res =repository.findByUUID(o.getOIC());
            Order res2 = repository.findByUUID(o.getOIC());
            assertAll(

                    () -> assertNotNull(res),
                    () -> assertTrue(res.getMethodPay().toString().equalsIgnoreCase(res2.getMethodPay().toString())),
                    () -> assertTrue(res.getOIC().toString().equalsIgnoreCase(res2.getOIC().toString()))
            );

        }


        /**
         * @Test salbar Order en el  repositorio
         */
        @Test
        void saveTest() throws SQLException {
            //todo SI funciona

            Order b = new Order(   "pagadorPrueva 2",50.0D,Pay.PAYPAL);

            repository.save(b);
            assertAll(
                    () -> assertNotNull(repository.findByUUID(b.getOIC()))
            );
        }

        /**
         * @Test cargar los datos nuevos del order en el repositorio
         */
        @Test
        void updateTest() throws SQLException {



            o.setMethodPay(new SimpleObjectProperty<Pay>(Pay.CARD));
            repository.update(o.getOIC(), o);
            assertAll(
                    () -> assertNotNull(repository.findByUUID(o.getOIC()))

            );
        }
}
