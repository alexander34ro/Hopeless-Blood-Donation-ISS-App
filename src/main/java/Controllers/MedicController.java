package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.MedicEntity;
import Persistence.PacientEntity;
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

public class MedicController implements IUserController<MedicEntity> {
    ObservableList<PacientEntity> modelM = FXCollections.observableArrayList();

    private MedicEntity user;
    private ClientInterface client;

    public void setUser(MedicEntity user) {
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
    }

    public void init() {
        labelNume.setText(user.getNume());
        labelSpital.setText(user.getSpitalBySpital().getNume());
        try {
            modelM.setAll(client.getAll(PacientEntity.class));
            tableView.setItems(modelM);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void setClient(ClientInterface client) {
        this.client = client;
        init();
    }

    public void handleTrimitere() {
        Stage stage = new Stage();
        stage.setTitle("Cerere");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/Cerere.fxml"));

        Pane pane = null;
        try {
            pane = (Pane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CerereController ctrl = loader.getController();
        ctrl.setMedic(user);
        ctrl.setClient(client);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        try {
            modelM.setAll(client.getAll(PacientEntity.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tableView.setItems(modelM);
    }
}
