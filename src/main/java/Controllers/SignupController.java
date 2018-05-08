package Controllers;

import Networking.Interfaces.ClientInterface;
import Services.DumbService;
import Utils.GenericStuff;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SignupController {
    DumbService service;
    private ClientInterface client;

    public void  setService(DumbService service){this.service=service;}
    public void setClient(ClientInterface client){
        this.client = client;
    }

    /* Visual controls */
    @FXML private TextField numeTextField;
    @FXML private TextField prenumeTextField;
    @FXML private DatePicker birthDate;
    @FXML private ChoiceBox<String> judetChoiceBox;
    @FXML private TextField cityTextField;
    @FXML private TextArea addressTextArea;
    @FXML private CheckBox changedAddressCheckBox;
    @FXML private ChoiceBox<String> changedJudetChoiceBox;
    @FXML private TextField changedCityTextField;
    @FXML private TextArea changedAddressTextArea;
    @FXML private TextField emailAddressTextField;
    @FXML private TextField telephoneNumberTextField;

    @FXML
    private void initialize() {

        /*
        * Birth date datePicker format
        * */
        birthDate.setPromptText("zz.ll.aaaa");
        birthDate.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            @Override
            public String toString(LocalDate localDate) {
                if(localDate == null) {
                    return "";
                }
                return dateTimeFormatter.format(localDate);
            }
            @Override
            public LocalDate fromString(String dateString) {
                if(dateString == null || dateString.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(dateString, dateTimeFormatter);
            }
        });

        /*
        * Changed address formats
        * */
        this.changedAddressCheckBox.selectedProperty().setValue(false);
        this.changedAddressTextArea.setDisable(true);
        this.changedCityTextField.setDisable(true);
        this.changedJudetChoiceBox.setDisable(true);

        /*
        * Counties
        * */
        this.judetChoiceBox.setItems(FXCollections.observableArrayList(GenericStuff.getCountiesList()));
        this.changedJudetChoiceBox.setItems(FXCollections.observableArrayList(GenericStuff.getCountiesList()));

        this.judetChoiceBox.getSelectionModel().select(0);
        this.changedJudetChoiceBox.getSelectionModel().select(0);
    }

    @FXML private void handleSwitchedAddressCheckBox(ActionEvent actionEvent) {
        this.changedAddressTextArea.setDisable( ! this.changedAddressCheckBox.isSelected());
        this.changedCityTextField.setDisable( ! this.changedAddressCheckBox.isSelected());
        this.changedJudetChoiceBox.setDisable( ! this.changedAddressCheckBox.isSelected());
    }

    @FXML private void handleRegistration(ActionEvent actionEvent) {
        // get input
        String firstName = this.numeTextField.getText();
        String lastName = this.prenumeTextField.getText();
        LocalDate birthDate = this.birthDate.getValue();


        // to do
    }
}
