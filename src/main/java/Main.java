import Controllers.LoginController;
import Persistence.SpitalEntity;
import Services.DumbService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hopeless");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/login.fxml"));
        Pane pane = (Pane) loader.load();
        LoginController ctrl = loader.getController();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Autowired
    static DumbService dumbService = new DumbService();

    public static void main(String[] args) {
        SpitalEntity spitalEntity = new SpitalEntity();
        spitalEntity.setNume("Yo");
        spitalEntity.setOras("Yo");
        spitalEntity.setRegiune("Yo");

        dumbService.save(spitalEntity);
        List<SpitalEntity> spitalEntities = dumbService.getAll(SpitalEntity.class);
        System.out.println(spitalEntities.size());

        launch(args);
    }
}
