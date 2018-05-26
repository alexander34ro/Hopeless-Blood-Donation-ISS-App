package Teste;

import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Networking.NetworkException;
import Persistence.AsistentEntity;
import Services.DumbService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.List;

public class TestPacient {

    @Test
    public  void testPacient(){
        DumbService dumbService=new DumbService();
        List<AsistentEntity> asistentEntityListt= dumbService.getAll(AsistentEntity.class);
        if(asistentEntityListt.size()>0){
            AsistentEntity asistentEntity=asistentEntityListt.get(0);
            try {
                ClientController clientController = new ClientController( (ServerInterface) (new ClassPathXmlApplicationContext("classpath:clientSpring.xml")).getBean("bloodDonationService") );
                clientController.login(asistentEntity.getUsername(),asistentEntity.getParola());
            } catch (RemoteException e) {
            } catch (NetworkException e) {
            }

        }
    }
}
