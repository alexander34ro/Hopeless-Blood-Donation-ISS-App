import Controllers.LoginController;
import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Services.DumbService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main extends Application {

    private static ClientController clientController;
    private DumbService service;

    @Override
    public void start(Stage primaryStage) throws Exception {

        clientController = new ClientController( (ServerInterface) (new ClassPathXmlApplicationContext("classpath:clientSpring.xml")).getBean("bloodDonationService") );


        primaryStage.setTitle("Hopeless");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/login.fxml"));
        Pane pane = (Pane) loader.load();
        LoginController ctrl = loader.getController();


        ctrl.setClient(clientController);
        ctrl.setService(service);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Autowired
    static DumbService dumbService = new DumbService();

    public static void main(String[] args) {
        /*SpitalEntity spitalEntity = new SpitalEntity();
        spitalEntity.setNume("Yo");
        spitalEntity.setOras("Yo");
        spitalEntity.setRegiune("Yo");

        dumbService.save(spitalEntity);
        List<SpitalEntity> spitalEntities = dumbService.getAll(SpitalEntity.class);
        System.out.println(spitalEntities.size());*/

        launch(args);
    }
}
