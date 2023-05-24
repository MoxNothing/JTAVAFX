package controladores.control;

import utilidad.DateUtil;
import modelo.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class PersonEditDialogController {

    @FXML
    private TextField NombresF;
    @FXML
    private TextField ApellidosF;
    @FXML
    private TextField CalleF;
    @FXML
    private TextField CodPostalF;
    @FXML
    private TextField CiudadF;
    @FXML
    private TextField CumpleF;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;

        NombresF.setText(person.getFirstName());
        ApellidosF.setText(person.getLastName());
        CalleF.setText(person.getStreet());
        CodPostalF.setText(Integer.toString(person.getPostalCode()));
        CiudadF.setText(person.getCity());
        CumpleF.setText(DateUtil.format(person.getBirthday()));
        CumpleF.setPromptText("dd.mm.yyyy");
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(NombresF.getText());
            person.setLastName(ApellidosF.getText());
            person.setStreet(CalleF.getText());
            person.setPostalCode(Integer.parseInt(CodPostalF.getText()));
            person.setCity(CiudadF.getText());
            person.setBirthday(DateUtil.parse(CumpleF.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (NombresF.getText() == null || NombresF.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (ApellidosF.getText() == null || ApellidosF.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (CalleF.getText() == null || CalleF.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (CodPostalF.getText() == null || CodPostalF.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(CodPostalF.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (CiudadF.getText() == null || CiudadF.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        if (CumpleF.getText() == null || CumpleF.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(CumpleF.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}