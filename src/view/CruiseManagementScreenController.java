package view;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.CruiseSailing;
import model.CruiseShip;
public class CruiseManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private StackPane pane;

	@FXML
	private JFXTextField IDTextField;

	@FXML
	private JFXComboBox<CruiseShip> shipCombo;

	@FXML
	private JFXDatePicker leavingDatePicker;

	@FXML
	private JFXTimePicker leavingTimePicker;

	@FXML
	private JFXDatePicker returnDatePicker;

	@FXML
	private JFXTimePicker returnTimePicker;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton saveCruiseBut;

	private CruiseSailing cruise;

	private boolean update;

	// =============================== Methods ==============================

	public void initialize() {
		cruise = ViewLogic.adminCruisesScreenController.cruise;
		pane.setStyle("-fx-background-image: url(\"/rsc/cruise-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// set ShipCombo
		shipCombo.getItems().setAll(ViewLogic.controller.getAllShips());

		// for update
		if (cruise != null) {
			IDTextField.setText(cruise.getCruiseID());
			shipCombo.getSelectionModel().select(new CruiseShip(cruise.getCruiseShipID()));
			LocalDateTime leavingdt = cruise.getLeavingTime().toLocalDateTime();
			LocalDateTime returndt = cruise.getReturnTime().toLocalDateTime();
			leavingDatePicker.setValue(leavingdt.toLocalDate());
			leavingTimePicker.setValue(leavingdt.toLocalTime());
			returnDatePicker.setValue(returndt.toLocalDate());
			returnTimePicker.setValue(returndt.toLocalTime());
			update = true;
		}
		else{
			setAutoID();
			cruise = new CruiseSailing(IDTextField.getText());
		}


	}

	private void setAutoID() {
		IDTextField.setText(ViewLogic.controller.autoIncrementCruiseID()+"");
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveCruise() {
		CruiseShip s = shipCombo.getValue();
		if (s != null) {
			if (leavingDatePicker.getValue() != null) {
				if (leavingTimePicker.getValue() != null) {
					if (returnDatePicker.getValue() != null) {
						if (returnTimePicker.getValue() != null) {
							Timestamp leaving = Timestamp.valueOf(LocalDateTime.of(leavingDatePicker.getValue(), leavingTimePicker.getValue()));
							Timestamp returning = Timestamp.valueOf(LocalDateTime.of(returnDatePicker.getValue(), returnTimePicker.getValue()));
							if (leaving.after(new Timestamp(System.currentTimeMillis()))) {
								if (returning.after(new Timestamp(System.currentTimeMillis()))) {
									if (leaving.before(returning)) {
										CruiseSailing cs = new CruiseSailing(IDTextField.getText(), s.getCruiseShipID(), leaving, returning);
										if (!ViewLogic.controller.cruiseSailOverlappingDates(cs)) {
										if (update) {
											ViewLogic.controller.updateCruise(cs);
											errorLabel.setText("Cruise updated successfully.");
										}
										else if (!update && ViewLogic.controller.insertCruise(cs)) {
											errorLabel.setText("Cruise added successfully. Add Another?");
											setAutoID();
											shipCombo.getSelectionModel().clearSelection();
											leavingDatePicker.setValue(null);
											leavingTimePicker.setValue(null);
											returnDatePicker.setValue(null);
											returnTimePicker.setValue(null);
										}
										else
											errorLabel.setText("Error occurred.");
											ViewLogic.adminCruisesScreenController.setCruiseTable();
										} else 
											errorLabel.setText("Ship is already in use during these dates. Please Choose Different dates.");
									} else
										errorLabel.setText("Leaving date must be before return date.");
								} else
									errorLabel.setText("Return date must be after today.");
							} else
								errorLabel.setText("Leaving date must be after today.");
						} else
							errorLabel.setText("Please select a return time.");
					} else
						errorLabel.setText("Please select a return date.");
				} else
					errorLabel.setText("Please select a leaving time.");
			} else
				errorLabel.setText("Please select a leaving date.");

		} else
			errorLabel.setText("Please select a ship.");
	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveCruise();
	}
}
