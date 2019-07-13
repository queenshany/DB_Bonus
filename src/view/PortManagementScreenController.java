package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.Port;
public class PortManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXComboBox<Country> countryCombo;

	@FXML
	private JFXTextField portTextField;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton savePortBut;

	protected Port port;

	// =============================== Methods ==============================

	public void initialize() {
		mainPane.setStyle("-fx-background-image: url(\"/rsc/port-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");
		setCountryCombo();
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	private void setCountryCombo() {
		countryCombo.getItems().setAll(ViewLogic.controller.getAllCountries());
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void savePort() {
		Country c = countryCombo.getValue();
		if (c != null) {
			String port = portTextField.getText();
			if (port != null || (port != null && !port.isEmpty())) {
				if (Validation.validName(port)) {
					try {
						//TODO CHECK IF PORT EXISTS
						ViewLogic.controller.insertPort(new Port(c.getCountryName(), port));
						ViewLogic.adminCountriesPortsScreenController.setPortTable();
						errorLabel.setText("Port added successfully. Add another?");
					}catch(Exception e) {
						errorLabel.setText("Error occured.");
					}
				} else
					errorLabel.setText("Invalid port name.");
			} else
				errorLabel.setText("Please type a port name.");
		} else
			errorLabel.setText("Please select a country.");

	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			savePort();
	}
}
