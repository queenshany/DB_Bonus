package view;

import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.CruiseShip;
public class ShipManagementScreenController {

	// ============================== Variables =============================

	@FXML
    private StackPane pane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private JFXTextField IDTextField;

    @FXML
    private JFXTextField nameTextField;

    @FXML
    private JFXDatePicker manuDatePicker;

    @FXML
    private JFXTextField maxCapTextField;

    @FXML
    private JFXTextField maxPeopleTextField;

    @FXML
    private Label errorLabel;

    @FXML
    private JFXButton saveShipBut;

	private CruiseShip ship;

	// =============================== Methods ==============================

	public void initialize() {
		ship = ViewLogic.adminShipsRoomsScreenController.ship;
		pane.setStyle("-fx-background-image: url(\"/rsc/ship-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// for update
		if (ship != null) {
			IDTextField.setText(ship.getCruiseShipID());
			nameTextField.setText(ship.getShipName());
			LocalDate manudt = ship.getManufacturingDate().toLocalDate();
			manuDatePicker.setValue(manudt);
			maxCapTextField.setText(Integer.toString(ship.getMaxCapacity()));
			maxPeopleTextField.setText(Integer.toString(ship.getMaxNumberOfPeople()));
		}
		//		else TODO ADD AUTOMATIC ID
		//			IDTextField.setText(ViewLogic.controller.);

	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveShip() {
		errorLabel.setText("hello");
		//		CruiseShip s = shipCombo.getValue();
		//		if (s != null) {
		//			String port = portTextField.getText();
		//			if (port != null || (port != null && !port.isEmpty())) {
		//				if (Validation.validName(port)) {
		//					try {
		//						ViewLogic.controller.insertPort(new Port(c.getCountryName(), port));
		//						ViewLogic.adminCountriesPortsScreenController.setPortTable();
		//						errorLabel.setText("Port added successfully. Add another?");
		//					}catch(Exception e) {
		//						errorLabel.setText("Error occured.");
		//					}
		//				} else
		//					errorLabel.setText("Invalid port name.");
		//			} else
		//				errorLabel.setText("Please type a port name.");
		//		} else
		//			errorLabel.setText("Please select a country.");

	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveShip();
	}
}
