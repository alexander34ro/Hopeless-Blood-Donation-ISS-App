package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.MedicEntity;
import Services.DumbService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicController implements IUserController<MedicEntity>{

    DumbService service;
    public MedicController(DumbService service) {
        this.service=service;
    }


    private MedicEntity user;
    private ClientInterface client;

    public void setUser(MedicEntity user){
        this.user = user;

    }
    @FXML
    private Label labelNume;
    @FXML
    private Label labelSpital;



    @FXML
    public void initialize() {

        labelNume.setText(user.getNume());
        labelSpital.setText(user.getNumeSpital());

    }

    public void setClient(ClientInterface client){
        this.client = client;
    }

    public void handleTrimitere(){
        Stage stage=new Stage();
        stage.setTitle("Cerere");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Views/Cerere.fxml"));
        Pane pane = null;
        try {
            pane = (Pane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CerereController ctrl = loader.getController();
        ctrl.setService(service);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
