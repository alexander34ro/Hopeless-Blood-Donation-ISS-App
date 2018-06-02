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
    @FXML
    private ComboBox comboBox4;

    ObservableList<PacientEntity> modelM = FXCollections.observableArrayList();

    private ClientInterface client;

    ObservableList<TipSange> tipSange = FXCollections.observableArrayList();
    ObservableList<CategorieSanguina> categorieSanguina = FXCollections.observableArrayList();
    ObservableList<Prioritate> prioritate = FXCollections.observableArrayList();
    ObservableList<CentruTransfuziiEntity> centruTransfuziiEntities = FXCollections.observableArrayList();

    MedicEntity medic = null;

    CerereEntity cerereEntityG;

    List<DetaliiCerereEntity> detaliiCerereEntityList = new ArrayList<>();

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
        cerereEntityG = new CerereEntity();
        cerereEntityG.setMedicByMedic(medic);
        cerereEntityG.setData(String.valueOf(new Date()));


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
        try {
            cerereEntityG.setCentruTransfuziiByCentruTransfuzii((CentruTransfuziiEntity) comboBox4.getSelectionModel().getSelectedItem());

            List<CerereEntity> cerereList = client.getAll(CerereEntity.class);

            short id = 1;
            if(cerereList!=null){
            for (CerereEntity c : cerereList
                    ) {
                if (c.getId() == id)
                    id++;
            }}
            cerereEntityG.setId(id);
             client.saveOrUpdate(cerereEntityG);

            for (DetaliiCerereEntity d : detaliiCerereEntityList
                    ) {
                d.setCerereByCerere(cerereEntityG);
                client.saveOrUpdate(d);
            }
            modelM.setAll(client.getAll(DetaliiCerereEntity.class));
            tableView.setItems(modelM);
        } catch (NetworkException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    public void handleStergere() {


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

            try {
                List<DetaliiCerereEntity> detaliiCerereEntityList = client.getAll(DetaliiCerereEntity.class);

                short id = 1;
                if (detaliiCerereEntityList != null) {
                    for (DetaliiCerereEntity d : detaliiCerereEntityList
                            ) {
                        if (d.getId() == id)
                            id++;
                    }
                }
                cerereEntity.setId(id);

            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            detaliiCerereEntityList.add(cerereEntity);
            JOptionPane.showMessageDialog(null, "Cerere adaugata.");

        }
    }
}
