package view;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.CruiseSailing;
import model.CruiseShip;
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
	private JFXButton saveCruiseBut;

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

		// for update
		if (sailto.getSailingID() != null)
			IDTextField.setText(sailto.getSailingID());
		//		else
		//			IDTextField.setText(""); 
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
	private void saveCruise() {
		Port p = portCombo.getValue();
		if (p != null) {
			if (arrivalDatePicker.getValue() != null) {
				if (arrivalDatePicker.getValue().isAfter(LocalDate.now())) {
					Date arr = Date.valueOf(arrivalDatePicker.getValue());
					if (leavingDatePicker.getValue() != null) {
						if (leavingDatePicker.getValue().isAfter(LocalDate.now())) {
							if (leavingDatePicker.getValue().isAfter(arrivalDatePicker.getValue())) {
								Date leave = Date.valueOf(leavingDatePicker.getValue());
								try {
									sailto.setSailingID(IDTextField.getText());
									sailto.setPortName(p.getPortName());
									sailto.setCountryName(p.getCountryName());
									sailto.setArrivalTime(arr);
									sailto.setLeavingTime(leave);
									if (update) {
										ViewLogic.controller.updateSailTo(sailto);
										errorLabel.setText("Sail to destination updated successfully.");
									}
									else if (!update && ViewLogic.controller.insertSailTo(sailto))
										errorLabel.setText("Sail to destination added successfully. Add another?");
									else if (!update)
										errorLabel.setText("Sail to destination already exists.");
									ViewLogic.adminCruisesScreenController.setSTtable();
								} catch(Exception e) {
									errorLabel.setText("Error occured.");
								}
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
			saveCruise();
	}
}
