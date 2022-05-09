package com.dam.gestionalmacendam.repositories.supplier;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.Supplier;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SupplierTest {
    SupplierRepository supplierRepository = SupplierRepository.getInstance(DataBaseManager.getInstance());

    Supplier supplierTest = new Supplier("MADER", "Calle Madera Nº23 Valencia", "632000888", "mader.spain@mader.com");

    @Test
    void findAll() throws SQLException {
        var list1 = supplierRepository.findAll();
        supplierRepository.save(supplierTest);
        var list2 = supplierRepository.findAll();

        assertAll(
                () -> assertEquals(list1.size(), 0),
                () -> assertEquals(list2.size(), 1),
                () -> assertEquals(list2.get(0).getSIC(), supplierTest.getSIC()),
                () -> assertEquals(list2.get(0).getNameSupplier(), supplierTest.getNameSupplier()),
                () -> assertEquals(list2.get(0).getDirection(), supplierTest.getDirection()),
                () -> assertEquals(list2.get(0).getTelephoneNumber(), supplierTest.getTelephoneNumber()),
                () -> assertEquals(list2.get(0).getEmail(), supplierTest.getEmail())
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
