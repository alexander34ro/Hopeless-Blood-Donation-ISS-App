package Controllers;

import Models.Prioritate;
import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.CerereEntity;
import Persistence.DetaliiCerereEntity;
import Persistence.MedicEntity;
import Persistence.PacientEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class MedicController implements IUserController<MedicEntity> {

    private MedicEntity user;
    private ClientInterface client;

    public void setUser(MedicEntity user) {
        this.user = user;
    }

    ObservableList<TipSange> tipSange = FXCollections.observableArrayList();
    ObservableList<Prioritate> prioritate = FXCollections.observableArrayList();
    @FXML
    private Label labelNume;
    @FXML
    private Label labelSpital;
    @FXML
    private TableView tableView;
    @FXML
    private TableView tableVieww;


    @FXML
    private TableColumn<CerereEntity, String> tableColumnCantitate;

    @FXML
    public void initialize() {
    }

    public void init() {
        labelNume.setText(user.getNume());
        labelSpital.setText(user.getSpitalBySpital().getNume());
        actualizareTabel();

    }

    public void actualizareTabel() {
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

            List<DetaliiCerereEntity> detaliiCerereEntity = client.getAll(DetaliiCerereEntity.class);
            tableColumnCantitate.setCellValueFactory(p -> {
                if (p.getValue() != null) {
                    return new SimpleStringProperty("" + detaliiCerereEntity
                            .stream()
                            .filter(object -> object.getCerereByCerere().getId() == p.getValue().getId())
                            .mapToInt(cerere -> (int) ((DetaliiCerereEntity) cerere).getCantitate())
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

    public void handleAdaugaPacient() {
        Stage stage = new Stage();
        stage.setTitle("Adauga Pacient");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("./Views/AdaugaPacient.fxml"));

        Pane pane = null;
        try {
            pane = (Pane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AdaugaPacientController ctrl = loader.getController();
        ctrl.setMedic(user);
        ctrl.setClient(client);
        ctrl.setStage(stage);
        ctrl.setFereastraMedic(this);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();


    }

    public void handleStergePacient()
    {
        if(tableView.getSelectionModel().getSelectedItem()==null){
            JOptionPane.showMessageDialog(null,"Nu ati selectat pacient.");
        }
        else
        {
            PacientEntity pacientEntity= (PacientEntity) tableView.getSelectionModel().getSelectedItem();
            try {
                client.delete(pacientEntity);
                JOptionPane.showMessageDialog(null,"Pacient sters.");
                actualizareTabel();
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
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
        ctrl.setFereastraMedic(this);
        ctrl.setClient(client);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }
}
