package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MedicController implements IUserController<MedicEntity> {

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
    private TableView tableVieww;

    @FXML
    private TableColumn<SpitalEntity, String> tableColumnSpital;
    @FXML
    private TableColumn<DetaliiCerereEntity, String> tableColumnCantitate;

    @FXML
    public void initialize() {
    }

    public void init() {
        labelNume.setText(user.getNume());
        labelSpital.setText(user.getSpitalBySpital().getNume());
        try {
            tableView.setItems(
                    FXCollections.observableArrayList(
                            client.getAll(PacientEntity.class)
                                    .stream()
                                    .map(obj -> (PacientEntity) obj)
                                    .filter(object -> object.getMedicByMedic().getId() == user.getId())
                                    .collect(Collectors.toList())
                    )
            );
            tableColumnSpital.setCellValueFactory(p -> {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(p.getValue().getNume());
                } else
                    return new SimpleStringProperty("N/A");
            });
            List<DetaliiCerereEntity> detaliiCerereEntity=client.getAll(DetaliiCerereEntity.class);
            tableColumnCantitate.setCellValueFactory(p -> {
                if (p.getValue() != null) {
                    return new SimpleStringProperty(""+detaliiCerereEntity
                            .stream()
                            .mapToInt(cerere -> (int) ((DetaliiCerereEntity)cerere).getCantitate())
                            .sum());

                } else
                    return new SimpleStringProperty("");
            });
            tableVieww.setItems(
                    FXCollections.observableArrayList(
                            client.getAll(CerereEntity.class)
                                    .stream()
                                    .map(obj -> (CerereEntity) obj)
                                    .filter(object -> object.getSpitalBySpital().getId() == user.getSpitalBySpital().getId())
                                    .collect(Collectors.toList())
                    )
            );

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
    }
}
