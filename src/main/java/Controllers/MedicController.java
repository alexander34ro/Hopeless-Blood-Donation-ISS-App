package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.MedicEntity;
import Persistence.PacientEntity;
import Services.DumbService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicController implements IUserController<MedicEntity>{

    DumbService service;
    public void setService(DumbService service) {
        this.service=service;
    }


    ObservableList<PacientEntity> modelM = FXCollections.observableArrayList();

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
    private TableView tableView;


    @FXML
    public void initialize() {

/*        labelNume.setText(user.getNume());
        labelSpital.setText(user.getSpitalBySpital().getNume());
        modelM.setAll(service.getAll(PacientEntity.class));
        tableView.setItems(modelM);*/

    }

    public void setClient(ClientInterface client) {
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

        modelM.setAll(service.getAll(PacientEntity.class));
        tableView.setItems(modelM);
    }
}
