package controladores.control;

import javafx.scene.control.Alert;
import modelo.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;





public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> NombresS;
    @FXML
    private TableColumn<Person, String> ApellidosS;

    @FXML
    private Label Nombres;
    @FXML
    private Label Apellidos;
    @FXML
    private Label Calle;
    @FXML
    private Label CodPostal;
    @FXML
    private Label Ciudad;
    private Label Cumple;

    // Reference to the main application.
    private MainApp mainApp;



    public PersonOverviewController() {

    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        NombresS.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        ApellidosS.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            Nombres.setText(person.getFirstName());
            Apellidos.setText(person.getLastName());
            Calle.setText(person.getStreet());
            CodPostal.setText(Integer.toString(person.getPostalCode()));
            Ciudad.setText(person.getCity());

            // TODO: We need a way to convert the birthday into a String!
            // birthdayLabel.setText(...);
        } else {
            // Person is null, remove all the text.
            Nombres.setText("");
            Apellidos.setText("");
            Calle.setText("");
            CodPostal.setText("");
            Ciudad.setText("");
            Cumple.setText("");
        }
    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        NombresS.setCellValueFactory(
                cellData -> cellData.getValue().firstNameProperty());
        ApellidosS.setCellValueFactory(
                cellData -> cellData.getValue().lastNameProperty());

        // Clear person details.
        showPersonDetails(null);

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        personTable.getItems().remove(selectedIndex);
    }

    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
