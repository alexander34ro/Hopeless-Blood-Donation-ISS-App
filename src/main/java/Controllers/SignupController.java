package Controllers;

import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.DonatorEntity;
import Utils.GenericStuff;
import Utils.MessageAllert;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class SignupController {
    private ClientInterface client;

    public void setClient(ClientInterface client){
        this.client = client;
    }

    /* Visual controls */

    @FXML private GridPane contentPane;

    @FXML private TextField numeTextField;
    @FXML private TextField prenumeTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
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
    @FXML private ChoiceBox<String> bloodTypeChoiceBox;

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

        /*
        * Blood group
        */
        this.bloodTypeChoiceBox.getItems().add("Necunoscută");
        for(TipSange tipSange : TipSange.values()) {
            this.bloodTypeChoiceBox.getItems().add(tipSange.toString());
        }
        this.bloodTypeChoiceBox.getSelectionModel().select(0);
    }


    @FXML private void handleSwitchedAddressCheckBox(ActionEvent actionEvent) {
        this.changedAddressTextArea.setDisable( ! this.changedAddressCheckBox.isSelected());
        this.changedCityTextField.setDisable( ! this.changedAddressCheckBox.isSelected());
        this.changedJudetChoiceBox.setDisable( ! this.changedAddressCheckBox.isSelected());
    }

    /*
    * Register button
    */
    @FXML private void handleRegistration(ActionEvent actionEvent) {

        /*
        * Reset input
        */
        for(Node node : contentPane.getChildren()) {
            // check if type input
            if(node instanceof TextInputControl || node instanceof DatePicker) {
                node.getStyleClass().remove("inputError");
            }
        }

        boolean fieldsCompleted = true;

        // check if every field is completed
        for(Node node : contentPane.getChildren()) {
            // check if type is text input
            if(node instanceof TextInputControl) {
                TextInputControl inputControl = (TextInputControl) node;
                if(inputControl.getText().trim().length() == 0) {
                    if( ! changedAddressCheckBox.isSelected() && ( inputControl == changedCityTextField || inputControl == changedAddressTextArea ) ) {
                        continue;
                    }
                    inputControl.getStyleClass().add("inputError");
                    fieldsCompleted = false;
                }
            }
        }
        if(birthDate.toString().trim().length() == 0) {
            // change style ... to do
            fieldsCompleted = false;
        }


        if(fieldsCompleted) {
            try {
                // store input
                String firstName = this.numeTextField.getText();
                String lastName = this.prenumeTextField.getText();
                String birthDate = this.birthDate.getValue().format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
                String county = judetChoiceBox.getSelectionModel().getSelectedItem();
                String city = cityTextField.getText();
                String address = addressTextArea.getText();

                String changedCounty = "";
                String changedCity = "";
                String changedAddress = "";

                boolean changedLocation = changedAddressCheckBox.isSelected();
                if(changedLocation) {
                    changedCounty = changedJudetChoiceBox.getSelectionModel().getSelectedItem();
                    changedCity = changedCityTextField.getText();
                    changedAddress = changedAddressTextArea.getText();
                }

                String email = emailAddressTextField.getText();
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String phone = telephoneNumberTextField.getText();
                String bloodType = bloodTypeChoiceBox.getSelectionModel().getSelectedItem();


                DonatorEntity donator = new DonatorEntity();
                donator.setNume(firstName);
                donator.setPrenume(lastName);
                donator.setDataNastere(birthDate);
                donator.setAdresa(address);
                donator.setOras(city);
                donator.setRegiune(county);
                donator.setOrasResedintegera(changedCity);
                donator.setRegiuneResedintegera(changedCounty);
                donator.setAdresaResedintegera(changedAddress);
                donator.setEmail(email);
                donator.setUsername(username);
                donator.setParola(password);
                donator.setTelefon(phone);

                short id;

                synchronized (client) {
                    List<DonatorEntity> donatorsList = client.getAll(DonatorEntity.class);

                    // check if username, email unique
                    if(donatorsList.stream().anyMatch(_donator -> _donator.getEmail().equals(email))) {
                        throw new Exception("Adresa email este deja folosita");
                    }

                    if(donatorsList.stream().anyMatch(_donator -> _donator.getUsername().equals(username))) {
                        throw new Exception("Username-ul este deja folosit");
                    }

                    id = 1;

                    Optional<DonatorEntity> result = donatorsList.stream()
                            .reduce((A, B) -> A.getId() > B.getId() ? A : B);
                    if (result.isPresent()) {
                        id = (short) (result.get().getId() + 1);
                    }
                }

                donator.setId(id);
                donator.setTipSange(bloodType);

                this.client.signUp(donator);

                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Contul a fost creat cu succes", ButtonType.CLOSE);
                alert.setOnCloseRequest((DialogEvent de) -> {
                    final Node source = (Node) actionEvent.getSource();
                    final Stage stage = (Stage) source.getScene().getWindow();
                    stage.close();
                });
                alert.showAndWait();

            }
            catch(NetworkException exception) {
                MessageAllert.showErrorMessage(null, exception.getMessage());
            }
            catch(Exception e) {
                MessageAllert.showErrorMessage(null, e.getMessage());
            }
        }
    }
}
