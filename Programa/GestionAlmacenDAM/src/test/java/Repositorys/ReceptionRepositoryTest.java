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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReceptionRepositoryTest {

}