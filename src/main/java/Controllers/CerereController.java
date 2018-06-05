package Controllers;

import Models.CategorieSanguina;
import Models.Prioritate;
import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.CerereEntity;
import Persistence.DetaliiCerereEntity;
import Persistence.MedicEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    ObservableList<DetaliiCerereEntity> modelM = FXCollections.observableArrayList();

    private ClientInterface client;

    MedicController medicCtrl = null;

    ObservableList<TipSange> tipSange = FXCollections.observableArrayList();
    ObservableList<CategorieSanguina> categorieSanguina = FXCollections.observableArrayList();
    ObservableList<Prioritate> prioritate = FXCollections.observableArrayList();


    MedicEntity medic = null;

    short idTest;

    List<DetaliiCerereEntity> detaliiCerereEntityList = new ArrayList<>();
    List<DetaliiCerereEntity> detaliiCerereEntityListTest = new ArrayList<>();

    ObservableList<DetaliiCerereEntity> detaliiCerereEntities = FXCollections.observableArrayList();

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
    }

    public void handleTrimitere() {
        CerereEntity cerereEntity = new CerereEntity();
        try {
            List<CerereEntity> cerereList = client.getAll(CerereEntity.class);
            short id = 1;
            if (cerereList != null) {
                for (CerereEntity c : cerereList
                        ) {
                    if (c.getId() == id)
                        id++;
                }
            }

            cerereEntity.setId(id);
            cerereEntity.setSpitalBySpital(medic.getSpitalBySpital());
            cerereEntity.setData(String.valueOf(new Date()));

            client.saveOrUpdate(cerereEntity);

            List<DetaliiCerereEntity> detaliiCerereEntityListAll = client.getAll(DetaliiCerereEntity.class);
            for (DetaliiCerereEntity d : detaliiCerereEntityList) {
                System.out.println(detaliiCerereEntityList.size());
                short iid = 1;
                if (detaliiCerereEntityListAll != null) {
                    for (DetaliiCerereEntity dd : detaliiCerereEntityListAll) {
                        if (dd.getId() == iid)
                            iid++;
                    }
                }
                System.out.println("id=" + iid);
                d.setId(iid);
                d.setCerereByCerere(cerereEntity);
                client.saveOrUpdate(d);
                detaliiCerereEntityListAll = client.getAll(DetaliiCerereEntity.class);
            }
            detaliiCerereEntityList.clear();
            modelM.setAll(detaliiCerereEntityList);
            tableView.setItems(modelM);
            medicCtrl.actualizareTabel();
        } catch (NetworkException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    public void handleStergere() {

        if (tableView.getSelectionModel().getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Cerere neselectata");
        } else {
            DetaliiCerereEntity detaliiCerereEntity = (DetaliiCerereEntity) tableView.getSelectionModel().getSelectedItem();

            detaliiCerereEntityList.remove(detaliiCerereEntity);
            try {
                modelM.setAll(detaliiCerereEntityList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            tableView.setItems(modelM);
            JOptionPane.showMessageDialog(null, "Cerere stearsa");

        }


    }

    public void setFereastraMedic(MedicController controller) {
        this.medicCtrl = controller;

    }

    public void handleAdauga() {
        if (textFieldUnitati.getText() == null || textFieldUnitati.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Date necompletate.");
        } else {
            DetaliiCerereEntity cerereEntity = new DetaliiCerereEntity();
            cerereEntity.setPrioritate(String.valueOf(comboBox3.getSelectionModel().getSelectedItem()));
            cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati.getText())));
            cerereEntity.setProdusSange(String.valueOf(comboBox2.getSelectionModel().getSelectedItem()));
            cerereEntity.setTipSange(String.valueOf(comboBox1.getSelectionModel().getSelectedItem()));
            cerereEntity.setDataCompletare(String.valueOf(new Date()));
            cerereEntity.setCompletata(Short.parseShort(String.valueOf(1)));

            detaliiCerereEntityList.add(cerereEntity);
            try {
                modelM.setAll(detaliiCerereEntityList);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            tableView.setItems(modelM);
            JOptionPane.showMessageDialog(null, "Cerere adaugata.");

        }
    }

}