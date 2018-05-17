package Networking;

import Utils.DumbGeocoder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartServer {
    public static void main(String[] args) {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:serverSpring.xml");

        System.out.println(DumbGeocoder.getDistanceBetween("Cluj-Napoca", "Huedin"));
    }
}
