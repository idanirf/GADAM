package Repositorys;

import com.dam.gestionalmacendam.managers.DataBaseManager;
import com.dam.gestionalmacendam.models.LineOrder;
import com.dam.gestionalmacendam.repositories.LineOrder.LineOrderRepository;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LineOrderRepositoryText {
    LineOrderRepository repository = LineOrderRepository.getInstance(DataBaseManager.getInstance());
    LineOrder lineOrder = new LineOrder("lineaOrder prueva1",1,1.00,"no pertenece" );



}
