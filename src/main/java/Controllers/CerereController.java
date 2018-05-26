package Controllers;

import Models.CategorieSanguina;
import Models.Prioritate;
import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.Date;

public class CerereController {


    @FXML
    private ComboBox comboBox3;
    @FXML
    private TextField textFieldUnitati;
    @FXML
    private ComboBox comboBox2;
    @FXML
    private ComboBox comboBox1;
    @FXML
    private TableView tableView;
    @FXML
    private ComboBox comboBox4;


    ObservableList<PacientEntity> modelM = FXCollections.observableArrayList();

    private ClientInterface client;

    ObservableList<TipSange> tipSange = FXCollections.observableArrayList();
    ObservableList<CategorieSanguina> categorieSanguina = FXCollections.observableArrayList();
    ObservableList<Prioritate> prioritate = FXCollections.observableArrayList();
    ObservableList<CentruTransfuziiEntity> centruTransfuziiEntities =FXCollections.observableArrayList();

    MedicEntity medic = null;

    public void setMedic(MedicEntity medic) {
        this.medic = medic;
    }

    @FXML
    public void initialize() {

    }

    public void setClient(ClientInterface client) {
        this.client = client;
        tipSange.setAll(TipSange.values());
        comboBox1.setItems(tipSange);
        categorieSanguina.setAll(CategorieSanguina.values());
        comboBox2.setItems(categorieSanguina);
        prioritate.setAll(Prioritate.values());
        comboBox3.setItems(prioritate);
        comboBox1.getSelectionModel().selectFirst();
        comboBox2.getSelectionModel().selectFirst();
        comboBox3.getSelectionModel().selectFirst();
        try {
            centruTransfuziiEntities.setAll(client.getAll(CentruTransfuziiEntity.class));
            comboBox4.setItems(centruTransfuziiEntities);
            comboBox4.getSelectionModel().selectFirst();
        } catch (NetworkException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            modelM.setAll(client.getAll(DetaliiCerereEntity.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        tableView.setItems(modelM);

    }

    public void handleTrimitere() {
        if (textFieldUnitati.getText() == null || textFieldUnitati.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Date necompletate.");
        } else {
            CerereEntity cerereEntityy = new CerereEntity();
            cerereEntityy.setMedicByMedic(medic);
            cerereEntityy.setData(String.valueOf(new Date()));
            cerereEntityy.setCentruTransfuziiByCentruTransfuzii((CentruTransfuziiEntity) comboBox4.getSelectionModel().getSelectedItem());

            try {
                client.saveOrUpdate(cerereEntityy);
                DetaliiCerereEntity cerereEntity = new DetaliiCerereEntity();
                cerereEntity.setPrioritate(String.valueOf(comboBox3.getSelectionModel().getSelectedItem()));
                cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati.getText())));
                cerereEntity.setProdusSange(String.valueOf(comboBox2.getSelectionModel().getSelectedItem()));
                cerereEntity.setTipSange(String.valueOf(comboBox1.getSelectionModel().getSelectedItem()));
                cerereEntity.setDataCompletare(String.valueOf(new Date() ));
                cerereEntity.setCompletata(Short.parseShort(String.valueOf(1)));

                cerereEntity.setCerereByCerere(cerereEntityy);
                client.saveOrUpdate(cerereEntity);
                JOptionPane.showMessageDialog(null, "Cerere salvata.");
                try {
                    modelM.setAll(client.getAll(DetaliiCerereEntity.class));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                tableView.setItems(modelM);
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

    }

    public void handleStergere() {

        if (tableView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Date neselectate.");
        } else {
            DetaliiCerereEntity cerereEntity = new DetaliiCerereEntity();
            cerereEntity.setPrioritate(String.valueOf(comboBox3.getSelectionModel().getSelectedItem()));
            cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati.getText())));
            cerereEntity.setProdusSange(String.valueOf(comboBox2.getSelectionModel().getSelectedItem()));
            cerereEntity.setTipSange(String.valueOf(comboBox1.getSelectionModel().getSelectedItem()));
            try {
                client.delete(cerereEntity);
                JOptionPane.showMessageDialog(null, "Cerere eliminata.");
                try {
                    modelM.setAll(client.getAll(DetaliiCerereEntity.class));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                tableView.setItems(modelM);
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
