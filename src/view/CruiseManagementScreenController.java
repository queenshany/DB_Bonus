package view;

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

		}
		//		else TODO ADD AUTOMATIC ID
		//			IDTextField.setText(ViewLogic.controller.);

	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveCruise() {
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
			saveCruise();
	}
}
