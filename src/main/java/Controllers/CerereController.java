package Controllers;

import Models.CategorieSanguina;
import Models.Prioritate;
import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.DetaliiCerereEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.rmi.RemoteException;

public class CerereController {


    @FXML
    private ComboBox comboBox3;
    @FXML
    private TextField textFieldUnitati;
    @FXML
    private ComboBox comboBox2;
    @FXML
    private ComboBox comboBox1;

    private ClientInterface client;

    ObservableList<TipSange> tipSange = FXCollections.observableArrayList();
    ObservableList<CategorieSanguina> categorieSanguina = FXCollections.observableArrayList();
    ObservableList<Prioritate> prioritate = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
    }

    public  void setClient(ClientInterface client){
        this.client=client;
        tipSange.setAll(TipSange.values());
        comboBox1.setItems(tipSange);
        categorieSanguina.setAll(CategorieSanguina.values());
        comboBox2.setItems(categorieSanguina);
        prioritate.setAll(Prioritate.values());
        comboBox3.setItems(prioritate);

    }
    public void handleTrimitere(){
        DetaliiCerereEntity cerereEntity=new DetaliiCerereEntity();
        cerereEntity.setPrioritate(String.valueOf(comboBox3.getSelectionModel().getSelectedItem()));
        cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati.getText())));
        cerereEntity.setProdusSange(String.valueOf(comboBox2.getSelectionModel().getSelectedItem()));
        cerereEntity.setTipSange(String.valueOf(comboBox1.getSelectionModel().getSelectedItem()));
        try {
            client.saveOrUpdate(cerereEntity);
        } catch (NetworkException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public void handleStergere(){
        DetaliiCerereEntity cerereEntity=new DetaliiCerereEntity();
        cerereEntity.setPrioritate(String.valueOf(comboBox3.getSelectionModel().getSelectedItem()));
        cerereEntity.setCantitate(Short.parseShort(String.valueOf(textFieldUnitati.getText())));
        cerereEntity.setProdusSange(String.valueOf(comboBox2.getSelectionModel().getSelectedItem()));
        cerereEntity.setTipSange(String.valueOf(comboBox1.getSelectionModel().getSelectedItem()));
        try {
            client.delete(cerereEntity);
        } catch (NetworkException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
