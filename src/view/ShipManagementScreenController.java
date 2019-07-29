package view;

import java.sql.Date;
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

	private boolean update;

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
			update = true;
		}
		else{
			setAutoID();
			ship = new CruiseShip(IDTextField.getText());
		}

	}

	private void setAutoID() {
		IDTextField.setText(ViewLogic.controller.autoIncrementCruiseShip()+"");
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveShip() {
		if (Validation.validName(nameTextField.getText())) {
			if (manuDatePicker.getValue() != null) {
				if (manuDatePicker.getValue().isBefore(LocalDate.now())) {
					Date mdate = Date.valueOf(manuDatePicker.getValue());
					try {
						int maxCap = Integer.parseInt(maxCapTextField.getText());
						if (maxCap > 0) {
							try {
								int maxPpl = Integer.parseInt(maxPeopleTextField.getText());
								if (maxPpl > 0) {
									if (maxPpl >= ViewLogic.controller.roomsAmountInShip(ship, null, false)) {
										ship.setCruiseShipID(IDTextField.getText());
										ship.setShipName(nameTextField.getText());
										ship.setManufacturingDate(mdate);
										ship.setMaxCapacity(maxCap);
										ship.setMaxNumberOfPeople(maxPpl);
										if (update) {
											ViewLogic.controller.updateShip(ship);
											errorLabel.setText("Ship updated successfully.");
										}
										else if (!update) {//&& ViewLogic.controller.insertShip(ship)) {
											ViewLogic.controller.insertShip(ship);
											errorLabel.setText("Ship added successfully. Add another?");
											setAutoID();
											nameTextField.setText("");
											manuDatePicker.setValue(null);
											maxPeopleTextField.setText("");
											maxCapTextField.setText("");
										} else if (!update)
											errorLabel.setText("Ship already exists.");
										ViewLogic.adminShipsRoomsScreenController.setShipTable();

									} else
										errorLabel.setText("Max number of people must be\nbigger than the ship's beds amount.");
								} else
									errorLabel.setText("Max number of people must be a positive integer.");
							} catch (NumberFormatException e) {
								errorLabel.setText("Max number of people must be an integer.");
							}
						} else
							errorLabel.setText("Max capacity must be a positive integer.");
					} catch (NumberFormatException e) {
						errorLabel.setText("Max capacity must be an integer.");
					}
				} else
					errorLabel.setText("Date must be before today.");
			} else
				errorLabel.setText("Please select a manufacturing date.");
		} else
			errorLabel.setText("Invalid ship name.");
	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveShip();
	}
}
