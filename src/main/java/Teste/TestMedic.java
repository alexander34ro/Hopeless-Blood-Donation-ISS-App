package Teste;

import Controllers.AdaugaPacientController;
import Controllers.IUserController;
import Controllers.MedicController;
import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Networking.NetworkException;
import Persistence.IUser;
import Persistence.MedicEntity;
import Persistence.PacientEntity;
import Services.DumbService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.List;

public class TestMedic {

    @Test
    public void testMedic() {
        DumbService dumbService = new DumbService();
        List<MedicEntity> medicEntityList = dumbService.getAll(MedicEntity.class);
        if (medicEntityList.size() > 0) {
            MedicEntity medicEntity = medicEntityList.get(medicEntityList.size() - 1);
            MedicController medicController = new MedicController();
            AdaugaPacientController adaugaPacientController = null;
            try {
                ClientController clientController = new ClientController( (ServerInterface) new ClassPathXmlApplicationContext("classpath:clientSpring.xml").getBean("bloodDonationService") );
                IUser response =   clientController.login(medicEntity.getUsername(), medicEntity.getParola());
                //FXMLLoader aloader = new FXMLLoader(getClass().getClassLoader().getResource("/Views/Medic.fxml"));
                //Parent aroot = aloader.load();

                IUserController controller = new MedicController();//aloader.getController();
                clientController.setMedicController((MedicController) controller);
                controller.setUser(response);
                //controller.setClient(clientController);
                medicController.setClient(clientController, false);

                int sizeInitial = clientController.getAll(PacientEntity.class).size();
                medicController.adaugaPacient("Baraian", "Iulia", Short.parseShort(String.valueOf(1)), Short.parseShort(String.valueOf(2)), Short.parseShort(String.valueOf(4)), Short.parseShort(String.valueOf(2)), Short.parseShort(String.valueOf(4)), "OPozitiv");
                int sizeIntermediar = clientController.getAll(PacientEntity.class).size();
                assert sizeIntermediar > sizeInitial;
                medicController.stergePacient("Baraian", "Iulia");
                int sizeFinal = clientController.getAll(PacientEntity.class).size();
                assert sizeFinal == sizeInitial;
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
