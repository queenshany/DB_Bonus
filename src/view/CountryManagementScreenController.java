package view;

import com.jfoenix.controls.JFXButton;
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
public class CountryManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXTextField countryTextField;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton saveCountryBut;

	// =============================== Methods ==============================

	public void initialize() {
		mainPane.setStyle("-fx-background-image: url(\"/rsc/country-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveCountry() {
		String country = countryTextField.getText();
		if (country != null || (country != null && !country.isEmpty())) {
			if (Validation.validName(country)) {
				try {
					if (ViewLogic.controller.insertCountry(new Country(country))) {
						ViewLogic.adminCountriesPortsScreenController.setCountryList();
						errorLabel.setText("Country added successfully. Add another?");
					}
					else 
						errorLabel.setText("Country already exists.");
				}catch(Exception e) {
					errorLabel.setText("Error occured.");
				}
			} else
				errorLabel.setText("Invalid country name.");
		} else
			errorLabel.setText("Please type a country name.");
	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveCountry();
	}
}
