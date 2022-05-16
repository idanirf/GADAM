package com.dam.gestionalmacendam.repositories.supplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SupplierTest {
    SupplierRepository supplierRepository = SupplierRepository.getInstance(DataBaseManager.getInstance());
    Supplier supplierTest = new Supplier(UUID.randomUUID().toString(), "MESATABLA S,L,",
            "Calle Valencia N12", "678908765", "mesatabala.valencia@mesatabla.com");

        @BeforeEach
        void setDown() throws SQLException {
            var db = supplierRepository.getBbdd();
            String query = "DELETE FROM SUPPLIER WHERE nameSupplier=?";
            db.open();
            db.delete(query, supplierTest.getNameSupplier());
            db.close();
        }


    @Test
    void findAll() throws SQLException {
            supplierRepository.save(supplierTest);
        var list1 = supplierRepository.findAll();
        assertAll(
                () -> assertEquals(list1.size(), 1),
                () -> assertEquals(list1.get(0).getSIC(), supplierTest.getSIC()),
                ()-> assertEquals(list1.get(0).getNameSupplier(), supplierTest.getNameSupplier())
        );
    }

    @Test
    void findaByUUID() throws SQLException {
       var resultado = supplierRepository.save(supplierTest);
        assertAll(
                () -> assertTrue(resultado.isPresent()),
                () -> assertEquals(resultado.get().getSIC(), supplierTest.getSIC()),
                () -> assertEquals(resultado.get().getNameSupplier(), supplierTest.getNameSupplier()),
                () -> assertTrue(resultado.get().toString().equals(supplierTest.toString()))
        );
    }

    @Test
    void save() throws SQLException {
        var resultado = supplierRepository.save(supplierTest);
        assertAll(
                () -> assertEquals(resultado.get().getNameSupplier(), supplierTest.getNameSupplier()),
                () -> assertTrue(resultado.isPresent())
        );
    }

    @Test
    void update() throws SQLException {
            supplierRepository.save(supplierTest);
        supplierTest.setDirection("Calle Madrid N18 Madrid");
        supplierTest.setTelephoneNumber("605203547");
        supplierTest.setEmail("sillafactory.spain@sillafactory.com");
        var resultado = supplierRepository.update(supplierTest.getSIC(), supplierTest);

        assertAll(
                () -> assertEquals(resultado.get().getDirection(), supplierTest.getDirection()),
                () -> assertTrue(resultado.isPresent())
        );
    }
}