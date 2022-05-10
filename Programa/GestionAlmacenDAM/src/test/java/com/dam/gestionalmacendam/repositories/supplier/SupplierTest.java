package com.dam.gestionalmacendam.repositories.supplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SupplierTest {
    DataBaseManager bbdd = DataBaseManager.getInstance();
    SupplierRepository supplierRepository = SupplierRepository.getInstance();
    Supplier supplierTest = new Supplier(UUID.randomUUID().toString(), "MESATABLA S,L,","Calle Valencia N12", "678908765", "mesatabala.valencia@mesatabla.com");

    @BeforeAll
    void setUp() throws Exception {
        supplierRepository.save(supplierTest);
    }

    @Test
    void findAll() throws SQLException {
        var list1 = supplierRepository.findAll();
        assertAll(
                () -> assertEquals(list1.size(), 2),
                () -> assertTrue(list1.contains(list1)),
                () -> assertEquals(list1.get(1).getNameSupplier(), supplierTest.getNameSupplier())
        );
    }

    @Test
    void findaBySIC() throws SQLException {
        Optional<Supplier> supplierOptional = Optional.ofNullable((supplierRepository.findByUUID(supplierTest.getSIC())));
        assertAll(
                () -> assertTrue(supplierOptional.isPresent()),
                () -> assertEquals(supplierOptional.get().getSIC(), supplierTest.getSIC()),
                () -> assertEquals(supplierOptional.get().getNameSupplier(), supplierTest.getNameSupplier()),
                () -> assertTrue(supplierOptional.get().toString().equals(supplierTest.toString()))
        );
    }

    @Test
    void save() throws SQLException {
        Supplier supplier = new Supplier("MALTABLE", "Calle Tabla Madera Nº20 Malaga", "685140838", "maltale.spain@maltable.com");
        var resultado = supplierRepository.save(supplier).get();

        Optional<Supplier> supplierOptional = Optional.ofNullable(supplierRepository.findByUUID(resultado.getSIC()));
        assertAll(
                () -> assertEquals(resultado.getSIC(), supplier.getSIC()),
                () -> assertEquals(resultado.getNameSupplier(), supplier.getNameSupplier()),
                () -> assertTrue(supplierOptional.isPresent())
        );
    }

    @Test
    void update() throws SQLException {
        supplierTest.setNameSupplier("SILLAFACTORY");
        supplierTest.setDirection("Calle Madrid N18 Madrid");
        supplierTest.setTelephoneNumber("605203547");
        supplierTest.setEmail("sillafactory.spain@sillafactory.com");

        var resultado = supplierRepository.update(supplierTest.getSIC(), supplierTest).get();
        Optional<Supplier> supplierOptional = Optional.ofNullable(supplierRepository.findByUUID(resultado.getSIC()));
        assertAll(
                () -> assertEquals(resultado.getSIC(), supplierTest.getSIC()),
                () -> assertEquals(resultado.getNameSupplier(), supplierTest.getNameSupplier()),
                () -> assertFalse(supplierOptional.isPresent())
        );
    }

}
