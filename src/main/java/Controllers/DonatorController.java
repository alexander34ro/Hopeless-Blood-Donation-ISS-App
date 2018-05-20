package Controllers;

import Networking.Interfaces.ClientInterface;
import Persistence.CentruTransfuziiEntity;
import Persistence.DonatieEntity;
import Persistence.DonatorEntity;
import Utils.MessageAllert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class DonatorController implements IUserController<DonatorEntity> {

    private DonatorEntity user;
    private ClientInterface client;

    @FXML private Label dataLabel;

    @FXML private CheckBox acceptedRulesAndConditionsCheckBox;
    @FXML private CheckBox customPersonCheckBox;
    @FXML private TextField customPersonNameTextField;
    @FXML private ComboBox<String> transfusionCentersComboBox;

    private List<CentruTransfuziiEntity> transfusionCentersList;

    @FXML private Button sendButton;

    @FXML private TableView<DonatieEntity> tableView;
    ObservableList<DonatieEntity> model = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        /*model.setAll(service.getAll(DonatieEntity.class));
        tableView.setItems(model);
        setDataLabel();*/

        sendButton.setDisable(true);

        acceptedRulesAndConditionsCheckBox.setOnAction(event -> {
            sendButton.setDisable( ! acceptedRulesAndConditionsCheckBox.isSelected());
        });

        customPersonCheckBox.setOnAction(event -> {
            customPersonNameTextField.setDisable( ! customPersonCheckBox.isSelected());
        });
    }

    public DonatorController(){}

    public void setUser(DonatorEntity user) {
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;

        try {
            transfusionCentersList = client.getAll(CentruTransfuziiEntity.class);

            transfusionCentersList.forEach(center -> {
                transfusionCentersComboBox.getItems().add(center.getId() + ". " + center.getNume() + ", " + center.getRegiune() + ", " + center.getOras());
            });

            transfusionCentersComboBox.getSelectionModel().selectFirst();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setDataLabel(){
        /*LocalDate date=LocalDate.parse(model.get(0).getData());
        for(DonatieEntity donatieEntity:model){
                if(LocalDate.parse(donatieEntity.getData()).isAfter(date) )
                    date=LocalDate.parse(donatieEntity.getData());
        }
        dataLabel.setText(date.toString());*/
    }

    public void handleTrimitere(ActionEvent event) {
        try{
            DonatieEntity donatieEntity=new DonatieEntity();
            donatieEntity.setData(new SimpleDateFormat("dd.MM.yyyy").format(Calendar.getInstance().getTime()));
            donatieEntity.setDonatorByDonator(user);

            if(customPersonCheckBox.isSelected()) {
                donatieEntity.setNumePacient(customPersonNameTextField.getText());
            }
            else {
                donatieEntity.setNumePacient("");
            }

            donatieEntity.setaConsumatAlcool((short) 0);
            donatieEntity.setaSuferitBoli((short) 0);
            donatieEntity.setaSuferitInterventii((short) 0);
            donatieEntity.setGreutate((short) 0);
            donatieEntity.setInsarcinataLauzieMenstruatie((short) 0);
            donatieEntity.setPuls((short) 0);
            donatieEntity.setRespins((short) 0);
            donatieEntity.setSubTratament((short) 0);
            donatieEntity.setTensiune((short) 0);
            donatieEntity.setStadiu("dunno");

            donatieEntity.setCentruTransfuziiByCentruTransfuzii( transfusionCentersList.get( transfusionCentersComboBox.getSelectionModel().getSelectedIndex() ) );

            client.saveOrUpdate(donatieEntity);

            //donatieEntity.setDonator(user.getId());
            //service.saveOrUpdate(donatieEntity);
            //model.setAll(service.getAll(DonatieEntity.class));
            //String date= LocalDate.parse(donatieEntity.getData()).plusDays(1).toString();
            dataLabel.setText(donatieEntity.getData());
            tableView.setItems(model);
        }
        catch (Exception e){
            e.printStackTrace();
            MessageAllert.showErrorMessage(null,e.getMessage());
        }
    }
}
