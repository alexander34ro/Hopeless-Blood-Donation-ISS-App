import Controllers.LoginController;
import Networking.ClientController;
import Networking.Interfaces.ServerInterface;
import Services.DumbService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main extends Application {

    private static ClientController clientController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        clientController = new ClientController( (ServerInterface) new ClassPathXmlApplicationContext("classpath:clientSpring.xml").getBean("bloodDonationService") );

        primaryStage.setTitle("Hopeless");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/login.fxml"));
        Pane pane = (Pane) loader.load();
        LoginController ctrl = loader.getController();


        ctrl.setClient(clientController);

        Scene scene = new Scene(pane);

        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            System.exit(0);
        });

        //scene.getStylesheets().add(getClass().getResource("Views/styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Autowired
    static DumbService dumbService = new DumbService();

    public static void main(String[] args) {
        /*
        List<DetaliiCerereEntity> detaliiCerereEntities = dumbService.getAll(DetaliiCerereEntity.class);
        System.out.println(detaliiCerereEntities.size());
        DetaliiCerereEntity lastEntity = detaliiCerereEntities.get(detaliiCerereEntities.size() - 1);
        short id = (short)(lastEntity.getId() + 1);
        System.out.println(id);
        */
        /*
        List<SpitalEntity> spitalEntities = dumbService.getAll(SpitalEntity.class);
        SpitalEntity lastEntity = spitalEntities.get(spitalEntities.size() - 1);
        short id = (short)(lastEntity.getId() + 1);
        System.out.println(id);

        SpitalEntity spitalEntity = new SpitalEntity();
        spitalEntity.setId(id);
        spitalEntity.setNume("Spital");
        spitalEntity.setOras("Cluj-Napoca");
        spitalEntity.setRegiune("Cluj");

        dumbService.save(spitalEntity);

        System.out.println(spitalEntity.getId());
        spitalEntities = dumbService.getAll(SpitalEntity.class);
        System.out.println(spitalEntities.size());
        */

        launch(args);
    }
}
