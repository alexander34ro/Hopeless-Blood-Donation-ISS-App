package Networking;

import Networking.Interfaces.ServerInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartClient {

    public static void main(String[] args) {
        try {
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:clientSpring.xml");

            ServerInterface server = (ServerInterface) factory.getBean("bloodDonationService");

            ClientController clientController = new ClientController(server);

            System.out.println(clientController.modifyText("PERE"));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}