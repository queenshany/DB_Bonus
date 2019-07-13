package view;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.CruiseSailing;
import model.Port;
import model.SailTo;
public class AdminCruisesScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXButton homeBut;

	@FXML
	private JFXButton logoutBut;

	@FXML
	private JFXButton counPortsBut;

	@FXML
	private JFXButton shipRoomBut;

	@FXML
	private JFXButton sailCruiseBut;

	@FXML
	private JFXButton customersBut;

	@FXML
	private JFXButton cruiseOrderBut;

	@FXML
	private JFXButton dashboardBut;

	@FXML
	private VBox pane;

	@FXML
	private TableView<CruiseSailing> cruisesTable;

	@FXML
	private TableColumn<CruiseSailing, String> cruiseIDColumnC;

	@FXML
	private TableColumn<CruiseSailing, String> shipColumnC;

	@FXML
	private TableColumn<CruiseSailing, ?> leavingTimeColumnC; //TODO

	@FXML
	private TableColumn<CruiseSailing, ?> returnTmeColumnC; //TODO

	@FXML
	private Label errorCruiseLabel;

	@FXML
	private JFXButton addCruiseBut;

	@FXML
	private JFXButton delCruiseBut;

	@FXML
	private JFXButton updateCruiseBut;

	@FXML
	private TableView<SailTo> sailToTable;

	@FXML
	private TableColumn<SailTo, String> cruiseColumnST;

	@FXML
	private TableColumn<SailTo, String> countryColumnST;

	@FXML
	private TableColumn<SailTo, String> portColumnST;

	@FXML
	private TableColumn<SailTo, ?> arrivalTimeColumnST; //TODO

	@FXML
	private TableColumn<SailTo, ?> leavingTimeColumnST; //TODO

	@FXML
	private Label errorSTLabel;

	@FXML
	private JFXButton addSailToBut;

	@FXML
	private JFXButton delSailToBut;

	@FXML
	private JFXButton updateSailToBut;

	protected ArrayList<CruiseSailing> cruises;

	protected CruiseSailing cruise;

	// =============================== Methods ==============================

	public void initialize() {
		ViewLogic.adminCruisesScreenController = this;

		pane.setStyle("-fx-background-image: url(\"/rsc/cruise-st-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		//errorCruiseLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
		//errorSTLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");

		// setting ST table
		cruiseColumnST.setCellValueFactory(new PropertyValueFactory<>("sailingID")); // According to variable name
		countryColumnST.setCellValueFactory(new PropertyValueFactory<>("countryName")); // Same here
		portColumnST.setCellValueFactory(new PropertyValueFactory<>("portName")); // Same here
		arrivalTimeColumnST.setCellValueFactory(new PropertyValueFactory<>("arrivalTime")); // Same here
		leavingTimeColumnST.setCellValueFactory(new PropertyValueFactory<>("leavingTime")); // Same here

		// setting cruise table
		cruiseIDColumnC.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipColumnC.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		leavingTimeColumnC.setCellValueFactory(new PropertyValueFactory<>("leavingTime")); // Same here
		returnTmeColumnC.setCellValueFactory(new PropertyValueFactory<>("returnTime")); // Same here
		setCruiseTable();
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	protected void setCruiseTable() {
		cruises = ViewLogic.controller.getAllCruise();
		ObservableList<CruiseSailing> cs = FXCollections.observableArrayList(cruises);
		cruisesTable.setItems(cs);
		cruisesTable.refresh();
	}

	protected void setSTtable() {
		CruiseSailing cs = cruisesTable.getSelectionModel().getSelectedItem();
		if (cs != null) {
			ArrayList<SailTo> sailTos = ViewLogic.controller.getAllSailTo(cs.getCruiseID());
			ObservableList<SailTo> st = FXCollections.observableArrayList(sailTos);
			sailToTable.setItems(st);
		}
		else
			sailToTable.getItems().clear();

		sailToTable.refresh();
	}

	//TODO
	@FXML
	private void addCruise() {
		cruise = null;
		ViewLogic.newCruiseManagementWindow();
	}

	@FXML
	private void deleteCruise() {
		CruiseSailing cs = cruisesTable.getSelectionModel().getSelectedItem();
		if (cs == null)
			errorCruiseLabel.setText("Please select a cruise to delete.");
		else {
			ViewLogic.controller.removeCruise(cs);
			setCruiseTable();
			errorCruiseLabel.setText("Cruise deleted successfully.");
		}
	}

	@FXML
	private void updateCruise() {
		cruise = cruisesTable.getSelectionModel().getSelectedItem();
		if (cruise == null)
			errorCruiseLabel.setText("Please select a cruise to update.");
		else
			ViewLogic.newCruiseManagementWindow();
	}

	//TODO
	@FXML
	private void addSailTo() {

	}

	@FXML
	private void deleteSailTo() {
		SailTo st = sailToTable.getSelectionModel().getSelectedItem();
		if (st == null)
			errorCruiseLabel.setText("Please select a sail to destination to delete.");
		else {
			ViewLogic.controller.removeSailTo(st);
			setSTtable();
			errorCruiseLabel.setText("Sail to destination deleted successfully.");
		}
	}

	//TODO
	@FXML
	private void updateSailTo() {

	}

	// ========================== Menu Action Listeners ==========================

	@FXML
	private void homeMainOnAction() {
		closeWindow();
		ViewLogic.newAdminMainWindow();
	}

	@FXML
	private void logoutOnAction() {
		closeWindow();
		ViewLogic.newLoginWindow();
	}

	@FXML
	private void countriesPortsOnAction() {
		closeWindow();
		ViewLogic.newAdminPortCountryWindow();
	}

	@FXML
	private void shipsRoomsOnAction() {
		closeWindow();
		ViewLogic.newAdminShipsRoomsWindow();
	}

	@FXML
	private void cruisesOnAction() {
		closeWindow();
		ViewLogic.newAdminCruisesSTWindow();
	}

	@FXML
	private void customersOnAction() {
		closeWindow();
		ViewLogic.newAdminCustomersWindow();
	}

	//TODO
	@FXML
	private void cruiseOrdersOnAction() {

	}

	@FXML
	private void dashboardOnAction() {
		closeWindow();
		ViewLogic.newAdminDashWindow();
	}
}
