package Controllers;

import Models.TipSange;
import Networking.Interfaces.ClientInterface;
import Networking.NetworkException;
import Persistence.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

public class AsistentController implements IUserController<AsistentEntity>{
    @FXML private TableView<DonatieEntity> tableViewDonatie;
    @FXML private  TableView<DetaliiCerereEntity> tableViewCerere;
    @FXML private Label labelPlasmaO,labelPlasmaA,labelPlasmaB,labelPlasmaAB,labelGlobuleO,labelGlobuleA,labelGlobuleB,labelGlobuleAB,labelTrombociteA,labelTrombociteB,labelTrombociteAB,labelTrombociteO;

    private AsistentEntity user;
    private ClientInterface client;
    ObservableList<DonatieEntity> modelDonatie = FXCollections.observableArrayList();
    ObservableList<DetaliiCerereEntity> modelDetaliiCerere = FXCollections.observableArrayList();


    @FXML public void initialize() {

        tableViewDonatie.setOnMouseClicked( event -> {
            if( event.getClickCount() == 2 ) {
                DonatieEntity selectedDonation = this.tableViewDonatie.getSelectionModel().getSelectedItem();

                final Stage dialog = new Stage();
                dialog.initStyle(StageStyle.UNDECORATED);
                dialog.initModality(Modality.APPLICATION_MODAL);
                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER);

                hBox.setStyle("-fx-padding: 10px;");

                GridPane gridPane = new GridPane();
                hBox.getChildren().add(gridPane);
                gridPane.setHgap(15);
                gridPane.setVgap(15);

                Button doneButton = new Button("OK");
                Button cancelButton = new Button("Anuleaza");

                gridPane.add(new Label("Actualizare donaţie"), 0, 0, 2, 1);

                gridPane.add(new Label("ID"), 0, 1);

                TextField donationCode = new TextField("" + selectedDonation.getId());
                donationCode.setDisable(true);
                gridPane.add(donationCode, 1, 1);


                TextField greutateTextField = new TextField("");
                gridPane.add(new Label("Greutate:"), 0, 2);
                gridPane.add(greutateTextField, 1, 2);

                TextField pulsTextField = new TextField("");
                gridPane.add(new Label("Puls:"), 0, 3);
                gridPane.add(pulsTextField, 1, 3);

                TextField tensiuneTextField = new TextField("");
                gridPane.add(new Label("Tensiune:"), 0, 4);
                gridPane.add(tensiuneTextField, 1, 4);

                ComboBox<String> bloodTypeChoiceBox = new ComboBox<>();
                String bloodType = "";

                bloodTypeChoiceBox.getItems().add("Necunoscută");
                for(TipSange tipSange : TipSange.values()) {
                    if(selectedDonation.getDonatorByDonator().getTipSange().equals(tipSange.name())) {
                        bloodType = tipSange.name();
                    }
                }

                if( ! bloodType.equals("")) {
                    bloodTypeChoiceBox.getItems().add(bloodType);
                    bloodTypeChoiceBox.setDisable(true);
                }
                else {
                    bloodTypeChoiceBox.getItems().add("Necunoscută");
                    for(TipSange tipSange : TipSange.values()) {
                        bloodTypeChoiceBox.getItems().add(tipSange.toString());
                    }
                    bloodTypeChoiceBox.getSelectionModel().select(0);
                }

                bloodTypeChoiceBox.getSelectionModel().select(0);

                gridPane.add(new Label("Grupa sanguniă:"), 0, 5);
                gridPane.add(bloodTypeChoiceBox, 1, 5);


                gridPane.add(doneButton, 0, 6);
                gridPane.add(cancelButton, 1, 6);

                doneButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            /*String customerName = clientTextField.getText().trim();
                            String customerAddress = clientAddress.getText().trim();

                            if(customerName.equals("") || customerAddress.equals(""))
                                throw new Exception("Nume client / adresa client invalide");

                            ArrayList<String> touristsStringName = new ArrayList<>();

                            for(TextField touristTextField : touristsName)
                                if(touristTextField.getText().trim().equals(""))
                                    throw new Exception("Nume turist invalid");
                                else
                                    touristsStringName.add(touristTextField.getText().trim());


                            clientController.buyTicket(new Ticket(flight.getID(), clientController.getCurrentlyLoggedInEmployee().getID(), customerName, customerAddress, numberOfSeats, touristsStringName  ));
                            // update flight number of tourists
                            flight.setAvailable_seats(flight.getAvailable_seats() - numberOfSeats);
                            dialog.close();
                            pageBorderPane.setStyle("-fx-opacity: 1;");

                            handleSearchFlights(null);*/

                            selectedDonation.setGreutate(Short.parseShort(greutateTextField.getText()));
                            selectedDonation.setPuls(Short.parseShort(pulsTextField.getText()));
                            selectedDonation.setTensiune(Short.parseShort(tensiuneTextField.getText()));

                            if( ! bloodTypeChoiceBox.isDisabled())
                            {
                                DonatorEntity donatorEntity = selectedDonation.getDonatorByDonator();
                                donatorEntity.setTipSange(bloodTypeChoiceBox.getSelectionModel().getSelectedItem());

                                client.saveOrUpdate(donatorEntity);
                            }

                            selectedDonation.setStadiu("Pregatire");

                            client.saveOrUpdate(selectedDonation);
                        }
                        catch(Exception exception) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText(exception.getMessage());
                            alert.setHeaderText("Eroare");
                            alert.showAndWait();
                        }
                    }
                });

                cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        dialog.close();
                    }
                });

                Scene dialogScene = new Scene(hBox, 400, 400);
                dialog.setScene(dialogScene);

                dialog.showAndWait();
            }
        });

    }

    public void setUser(AsistentEntity user){
        this.user = user;
    }

    public void setClient(ClientInterface client){
        this.client = client;
        tableViewDonatie.getItems().clear();
        try {
            /*client.getAll(DonatieEntity.class).forEach(object -> {
                DonatieEntity donatie = (DonatieEntity) object;
                if(donatie.getCentruTransfuziiByCentruTransfuzii().getId() == user.getId()) {
                    tableViewDonatie.getItems().add(donatie);
                }
            });*/

            /*System.out.println("luam donatiile de la server");
            List<DonatieEntity> donatieEntities = client.getAll(DonatieEntity.class);
            System.out.println(donatieEntities.get(0).getDonatorByDonator().getNume());
            System.out.println("punem donatiile");
            modelDonatie.setAll(donatieEntities);
            */

            this.updateDonatii();



            modelDetaliiCerere.setAll(client.getAll(DetaliiCerereEntity.class));
            tableViewCerere.setItems(modelDetaliiCerere);
            setLabels();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void updateDonatii() throws NetworkException, RemoteException {
        tableViewDonatie.getItems().clear();
        tableViewDonatie.setItems(
            FXCollections.observableArrayList(
                client.getAll(DonatieEntity.class)
                .stream()
                .map(obj -> (DonatieEntity) obj)
                .filter(donatieEntity -> donatieEntity.getCentruTransfuziiByCentruTransfuzii().getId() == this.user.getCentruTransfuziiByCentruTransfuzii().getId())
                .collect(Collectors.toList())
            )
        );
    }

    public void setLabels(){
        int[] valori = new int[12];
        try {
            List<UnitateSanguinaEntity> lista = client.getAll(UnitateSanguinaEntity.class);

            for (UnitateSanguinaEntity entity : lista) {
                if (entity.getTipSange().equals("OPozitiv") || entity.getTipSange().equals("ONegativ")) {
                    if (entity.getCategorie().equals("Plasma"))
                        valori[0]++;
                    else if (entity.getCategorie().equals("GlobuleRosii"))
                        valori[1]++;
                    else if (entity.getCategorie().equals("Trombocite"))
                        valori[2]++;
                } else if (entity.getTipSange().equals("APozitiv") || entity.getTipSange().equals("ANegativ")) {
                    if (entity.getCategorie().equals("Plasma"))
                        valori[3]++;
                    else if (entity.getCategorie().equals("GlobuleRosii"))
                        valori[4]++;
                    else if (entity.getCategorie().equals("Trombocite"))
                        valori[5]++;
                } else if (entity.getTipSange().equals("BPozitiv") || entity.getTipSange().equals("BNegativ")) {
                    if (entity.getCategorie().equals("Plasma"))
                        valori[6]++;
                    else if (entity.getCategorie().equals("GlobuleRosii"))
                        valori[7]++;
                    else if (entity.getCategorie().equals("Trombocite"))
                        valori[8]++;
                } else if (entity.getTipSange().equals("ABPozitiv") || entity.getTipSange().equals("ABNegativ")) {
                    if (entity.getCategorie().equals("Plasma"))
                        valori[9]++;
                    else if (entity.getCategorie().equals("GlobuleRosii"))
                        valori[10]++;
                    else if (entity.getCategorie().equals("Trombocite"))
                        valori[11]++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
