package Controllers;

import Models.Prioritate;
import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.MedicEntity;
import Persistence.PacientEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.List;

public class AdaugaPacientController {
    private ClientInterface client;
    MedicEntity medic = null;

    ObservableList<TipSange> tipSange = FXCollections.observableArrayList();
    ObservableList<Prioritate> prioritate = FXCollections.observableArrayList();

    @FXML
    private ComboBox comboBoxPrioritate;
    @FXML
    private ComboBox comboBoxTipSange;
    @FXML
    private TextField textFieldNume;
    @FXML
    private TextField textFieldPrenume;
    @FXML
    private TextField textFieldTrombocite;
    @FXML
    private TextField textFieldGlobule;
    @FXML
    private TextField textFieldPlasma;
    @FXML
    private TextField textFieldSange;

    Stage stage;

    MedicController medicCtrl = null;

    public void setMedic(MedicEntity medic) {
        this.medic = medic;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setFereastraMedic(MedicController controller) {
        this.medicCtrl = controller;
    }

    public void setClient(ClientInterface client) {
        this.client = client;
        tipSange.setAll(TipSange.values());
        comboBoxTipSange.setItems(tipSange);
        prioritate.setAll(Prioritate.values());
        comboBoxPrioritate.setItems(prioritate);
        comboBoxTipSange.getSelectionModel().selectFirst();
        comboBoxPrioritate.getSelectionModel().selectFirst();
    }

    public void handleAdauga() {
        PacientEntity pacientEntity = new PacientEntity();
        if (textFieldGlobule.getText().equals("") || textFieldNume.getText().equals("")|| textFieldPlasma.getText().equals("") || textFieldPrenume.getText().equals("")  || textFieldSange.getText().equals("")  || textFieldTrombocite.getText().equals("") ) {
            JOptionPane.showMessageDialog(null, "Date necompletate");
        } else {
            List<PacientEntity> entityList = null;
            try {
                entityList = client.getAll(PacientEntity.class);
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            PacientEntity lastEntity = entityList.get(entityList.size() - 1);
            short id = (short)(lastEntity.getId() + 1);
            pacientEntity.setId(id);
            pacientEntity.setNume(textFieldNume.getText());
            pacientEntity.setPrenume(textFieldPrenume.getText());
            pacientEntity.setGlobuleRosiiNecesare(Short.valueOf(textFieldGlobule.getText()));
            pacientEntity.setMedicByMedic(medic);
            pacientEntity.setPlasmaNecesara(Short.valueOf(textFieldPlasma.getText()));
            pacientEntity.setSangeNecesar(Short.valueOf(textFieldSange.getText()));
            pacientEntity.setTrombociteNecesare(Short.valueOf(textFieldTrombocite.getText()));
            String prioritate = comboBoxPrioritate.getSelectionModel().getSelectedItem().toString();
            short prioritatee = 0;
            if (prioritate == "Medie") prioritatee = 1;
            else if (prioritate == "Mare") prioritatee = 2;
            else prioritatee = 3;
            pacientEntity.setPrioritate(Short.parseShort(String.valueOf(prioritatee)));
            pacientEntity.setTipSange(comboBoxTipSange.getSelectionModel().getSelectedItem().toString());
            try {
                client.saveOrUpdate(pacientEntity);
            } catch (NetworkException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            medicCtrl.actualizareTabel();
            stage.close();
        }
    }
}
