package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.AsistentEntity;
import Persistence.DetaliiCerereEntity;
import Persistence.DonatieEntity;
import Persistence.UnitateSanguinaEntity;
import Services.DumbService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.util.List;

public class AsistentController implements IUserController<AsistentEntity>{
    @FXML private TableView<DonatieEntity> tableViewDonatie;
    @FXML private  TableView<DetaliiCerereEntity> tableViewCerere;
    @FXML private Label labelPlasmaO,labelPlasmaA,labelPlasmaB,labelPlasmaAB,labelGlobuleO,labelGlobuleA,labelGlobuleB,labelGlobuleAB,labelTrombociteA,labelTrombociteB,labelTrombociteAB,labelTrombociteO;
    DumbService service;
    private AsistentEntity user;
    private ClientInterface client;
    ObservableList<DonatieEntity> modelDonatie = FXCollections.observableArrayList();
    ObservableList<DetaliiCerereEntity> modelDetaliiCerere = FXCollections.observableArrayList();

    public void setService(DumbService service){this.service=service;}
    public void setUser(AsistentEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
    }
    @FXML
    public void initialize() {
        modelDonatie.setAll(service.getAll(DonatieEntity.class));
        modelDetaliiCerere.setAll(service.getAll(DetaliiCerereEntity.class));
        tableViewDonatie.setItems(modelDonatie);
        tableViewCerere.setItems(modelDetaliiCerere);
        setLabels();
    }
    public void setLabels(){
        List<UnitateSanguinaEntity> lista=service.getAll(UnitateSanguinaEntity.class);
        int[] valori=new int[12];
        for(UnitateSanguinaEntity entity:lista){
            if(entity.getTipSange().equals("OPozitiv")||entity.getTipSange().equals("ONegativ")){
                if(entity.getCategorie().equals("Plasma"))
                    valori[0]++;
                else if(entity.getCategorie().equals("GlobuleRosii"))
                    valori[1]++;
                else if(entity.getCategorie().equals("Trombocite"))
                    valori[2]++;
            }
            else  if(entity.getTipSange().equals("APozitiv")||entity.getTipSange().equals("ANegativ")){
                if(entity.getCategorie().equals("Plasma"))
                    valori[3]++;
                else if(entity.getCategorie().equals("GlobuleRosii"))
                    valori[4]++;
                else if(entity.getCategorie().equals("Trombocite"))
                    valori[5]++;
            }
            else  if(entity.getTipSange().equals("BPozitiv")||entity.getTipSange().equals("BNegativ")){
                if(entity.getCategorie().equals("Plasma"))
                    valori[6]++;
                else if(entity.getCategorie().equals("GlobuleRosii"))
                    valori[7]++;
                else if(entity.getCategorie().equals("Trombocite"))
                    valori[8]++;
            }
            else  if(entity.getTipSange().equals("ABPozitiv")||entity.getTipSange().equals("ABNegativ")){
                if(entity.getCategorie().equals("Plasma"))
                    valori[9]++;
                else if(entity.getCategorie().equals("GlobuleRosii"))
                    valori[10]++;
                else if(entity.getCategorie().equals("Trombocite"))
                    valori[11]++;
            }
        }
        labelPlasmaO.setText(String.valueOf(valori[0]));
        labelGlobuleO.setText(String.valueOf(valori[1]));
        labelTrombociteO.setText(String.valueOf(valori[2]));
        labelPlasmaA.setText(String.valueOf(valori[3]));
        labelGlobuleA.setText(String.valueOf(valori[4]));
        labelTrombociteA.setText(String.valueOf(valori[5]));
        labelPlasmaB.setText(String.valueOf(valori[6]));
        labelGlobuleB.setText(String.valueOf(valori[7]));
        labelTrombociteB.setText(String.valueOf(valori[8]));
        labelPlasmaAB.setText(String.valueOf(valori[9]));
        labelGlobuleAB.setText(String.valueOf(valori[10]));
        labelTrombociteAB.setText(String.valueOf(valori[11]));
    }
}
