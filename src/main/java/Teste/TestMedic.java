package Teste;

import Controllers.CerereController;
import Controllers.IUserController;
import Controllers.MedicController;
import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Networking.NetworkException;
import Persistence.DetaliiCerereEntity;
import Persistence.MedicEntity;
import Services.DumbService;
import javafx.fxml.FXMLLoader;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class TestMedic {

    @Test
    public void testMedic() {
        DumbService dumbService = new DumbService();
        List<MedicEntity> medicEntityList = dumbService.getAll(MedicEntity.class);
        if (medicEntityList.size() > 0) {
            MedicEntity medicEntity = medicEntityList.get(medicEntityList.size() - 1);
            MedicController medicController = null;
            CerereController cerereController = null;
            try {
                ClientController clientController = new ClientController((ServerInterface) (new ClassPathXmlApplicationContext("classpath:clientSpring.xml")).getBean("bloodDonationService"));
                MedicEntity medicEntity1 = (MedicEntity) clientController.login(medicEntity.getUsername(), medicEntity.getParola());
                assert (medicEntity1.getNume().equals(medicEntity.getNume()));

                FXMLLoader aloader = null;
                aloader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Medic.fxml"));
                int sizeInitial = clientController.getAll(DetaliiCerereEntity.class).size();
                IUserController controller = aloader.getController();
                clientController.setMedicController((MedicController) controller);

                medicController.setClient(clientController);
                cerereController.setClient(clientController);
                cerereController.setMedic(medicEntity);
                cerereController.adaugaCerere("Medie", Short.parseShort("1"), "Trombocite", "OPozitiv");
                cerereController.trimiteCerere();

                int sizeIntermediar = clientController.getAll(DetaliiCerereEntity.class).size();
                assert sizeInitial < sizeIntermediar;
                cerereController.stergeCerere();

                int sizeFinal = clientController.getAll(DetaliiCerereEntity.class).size();
                assert sizeFinal < sizeIntermediar;


            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
