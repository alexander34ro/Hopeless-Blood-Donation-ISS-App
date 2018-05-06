package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.DonatieEntity;
import Persistence.DonatorEntity;
import Services.DumbService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DonatorController implements IUserController<DonatorEntity> {
    DumbService service;
    private DonatorEntity user;
    private ClientInterface client;
    public DonatorController(){}
    public void setUser(DonatorEntity user){
        this.user = user;
    }

    public void setService(DumbService service){this.service=service;}
    public void setClient(ClientInterface client){
        this.client = client;
    }


    @FXML
    private TextField textFieldGreutate;
    @FXML
    private TextField textFieldPuls;
    @FXML
    private TextField textFieldTensiune;
    @FXML
    private TextField textFieldGrS;
    @FXML
    private TextField textFieldRh;
    @FXML
    private ToggleGroup interventii;
    @FXML
    private ToggleGroup consum;
    @FXML
    private ToggleGroup tratament;
    @FXML
    private ToggleGroup boli;
    @FXML
    private ToggleGroup femei;

    public void handleTrimitere(){
        DonatieEntity donatieEntity=new DonatieEntity();
        donatieEntity.setPuls(Short.parseShort(textFieldPuls.getText()));
        donatieEntity.setGreutate(Short.parseShort(textFieldGreutate.getText()));
        donatieEntity.setTensiune(Short.parseShort(textFieldTensiune.getText()));
        donatieEntity.setaSuferitInterventii(Short.parseShort(interventii.getSelectedToggle().getUserData().toString()));
        donatieEntity.setaConsumatAlcool(Short.parseShort(consum.getSelectedToggle().getUserData().toString()));
        donatieEntity.setInsarcinataLauzieMenstruatie(Short.parseShort(femei.getSelectedToggle().getUserData().toString()));
        donatieEntity.setData("22.03.2018");
        donatieEntity.setaSuferitBoli(Short.parseShort(boli.getSelectedToggle().getUserData().toString()));
        donatieEntity.setSubTratament(Short.parseShort(tratament.getSelectedToggle().getUserData().toString()));
        service.saveOrUpdate(donatieEntity);


    }

}
