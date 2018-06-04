package Teste;

import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Networking.NetworkException;
import Persistence.MedicEntity;
import Services.DumbService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.List;
public class TestLogin {
    @Test
    public void  testLogin(){
        DumbService dumbService=new DumbService();
        List<MedicEntity> medicEntityList=dumbService.getAll(MedicEntity.class);
        if(medicEntityList.size()>0){
            MedicEntity medicEntity=medicEntityList.get(medicEntityList.size()-1);
            try {
                ClientController clientController = new ClientController((ServerInterface) (new ClassPathXmlApplicationContext("classpath:clientSpring.xml")).getBean("bloodDonationService"));
                MedicEntity medicEntity1= (MedicEntity) clientController.login(medicEntity.getUsername(),medicEntity.getParola());
                assert(medicEntity1.getNume().equals(medicEntity.getNume()));
            } catch (RemoteException e) {

            } catch (NetworkException e) {

            }
        }
    }
}
