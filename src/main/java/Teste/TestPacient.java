package Teste;

import Controllers.CerereController;
import Controllers.MedicController;
import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Networking.NetworkException;
import Persistence.MedicEntity;
import Services.DumbService;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;

public class TestPacient {

    @Test
    public void testPacient() {
        DumbService dumbService = new DumbService();
        MedicEntity medicEntity = dumbService.getAll(MedicEntity.class).get(0);

        ClientController clientController=null;
        MedicController medicController=null;
        CerereController cerereController=null;
        try {
            clientController = new ClientController((ServerInterface) (new ClassPathXmlApplicationContext("classpath:clientSpring.xml")).getBean("bloodDonationService"));

            MedicEntity medicEntity1 = (MedicEntity) clientController.login(medicEntity.getUsername(), medicEntity.getParola());
            assert (medicEntity1.getNume().equals(medicEntity.getNume()));

            clientController.setMedicController(medicController);
            cerereController.setMedic(medicEntity);
            cerereController.setClient(clientController);




        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NetworkException e) {
            e.printStackTrace();
        }


    }
}
