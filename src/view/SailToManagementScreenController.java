package view;

import java.sql.Date;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Port;
import model.SailTo;
public class SailToManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private StackPane pane;

	@FXML
	private JFXTextField IDTextField;

	@FXML
	private JFXComboBox<Port> portCombo;

	@FXML
	private JFXDatePicker leavingDatePicker;

	@FXML
	private JFXDatePicker arrivalDatePicker;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton saveSailToBut;

	private SailTo sailto;

	private boolean update;

	// =============================== Methods ==============================

	public void initialize() {
		sailto = ViewLogic.adminCruisesScreenController.sailto;
		pane.setStyle("-fx-background-image: url(\"/rsc/sailto-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// set portCombo
		portCombo.getItems().setAll(ViewLogic.controller.getAllPorts());

		// for new
		if (sailto.getSailingID() != null)
			IDTextField.setText(sailto.getSailingID());
		// for update
		if (sailto.getCountryName() != null && sailto.getCountryName() != null && sailto.getLeavingTime() != null && sailto.getArrivalTime() != null) {
			portCombo.getSelectionModel().select(new Port(sailto.getCountryName(), sailto.getPortName()));
			portCombo.setDisable(true);
			LocalDate leavingd = sailto.getLeavingTime().toLocalDate();
			LocalDate arrivald = sailto.getArrivalTime().toLocalDate();
			leavingDatePicker.setValue(leavingd);
			arrivalDatePicker.setValue(arrivald);
			update = true;
		}
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveSailTo() {
		Port p = portCombo.getValue();
		if (p != null) {
			if (arrivalDatePicker.getValue() != null) {
				if (arrivalDatePicker.getValue().isAfter(LocalDate.now()) || arrivalDatePicker.getValue().equals(LocalDate.now())) {
					Date arr = Date.valueOf(arrivalDatePicker.getValue());
					if (leavingDatePicker.getValue() != null) {
						if (leavingDatePicker.getValue().isAfter(LocalDate.now()) || leavingDatePicker.getValue().equals(LocalDate.now())) {
							if (leavingDatePicker.getValue().isAfter(arrivalDatePicker.getValue())) {
								Date leave = Date.valueOf(leavingDatePicker.getValue());
								if (!ViewLogic.controller.isOverlapDates(arr, leave, IDTextField.getText())) {
									sailto.setSailingID(IDTextField.getText());
									sailto.setPortName(p.getPortName());
									sailto.setCountryName(p.getCountryName());
									sailto.setArrivalTime(arr);
									sailto.setLeavingTime(leave);
									if (update) {
										ViewLogic.controller.updateSailTo(sailto);
										errorLabel.setText("Sail to destination updated successfully.");
									}
									else if (!update && ViewLogic.controller.insertSailTo(sailto)) {
										errorLabel.setText("Sail to destination added successfully. Add another?");
										portCombo.getSelectionModel().clearSelection();
										arrivalDatePicker.setValue(null);
										leavingDatePicker.setValue(null);
									} else if (!update)
										errorLabel.setText("Sail to destination already exists.");
									ViewLogic.adminCruisesScreenController.setSTtable();
								} else
									errorLabel.setText("Dates overlap in the cruise. Please select different dates.");
							} else
								errorLabel.setText("Leaving date must be after arrival date.");
						} else 
							errorLabel.setText("Leaving date must be after today.");
					} else
						errorLabel.setText("Leaving date must be after today.");
				} else
					errorLabel.setText("Arrival date must be after today.");
			} else
				errorLabel.setText("Please select an arrival date.");
		} else
			errorLabel.setText("Please select a port.");
	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveSailTo();
	}
}
