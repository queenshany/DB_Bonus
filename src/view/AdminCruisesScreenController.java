package view;

import java.sql.Date;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.sun.jmx.snmp.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CruiseSailing;
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
	private TableColumn<CruiseSailing, Timestamp> leavingTimeColumnC;

	@FXML
	private TableColumn<CruiseSailing, Timestamp> returnTmeColumnC;

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
	private TableColumn<SailTo, Date> arrivalTimeColumnST;

	@FXML
	private TableColumn<SailTo, Date> leavingTimeColumnST;

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

	protected SailTo sailto;

	// =============================== Methods ==============================

	public void initialize() {
		ViewLogic.adminCruisesScreenController = this;

		pane.setStyle("-fx-background-image: url(\"/rsc/cruise-st-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorCruiseLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");
		errorSTLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

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

	@FXML
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
			setSTtable();
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

	@FXML
	private void addSailTo() {
		CruiseSailing cs = cruisesTable.getSelectionModel().getSelectedItem();
		if (cs == null)
			errorSTLabel.setText("Please select a cruise in order to add a sail to destination to it.");
		else {
			sailto = new SailTo(cs.getCruiseID());
			ViewLogic.newSailToManagementWindow();
		}
	}

	@FXML
	private void deleteSailTo() {
		SailTo st = sailToTable.getSelectionModel().getSelectedItem();
		if (st == null)
			errorSTLabel.setText("Please select a sail to destination to delete.");
		else {
			ViewLogic.controller.removeSailTo(st);
			setSTtable();
			errorSTLabel.setText("Sail to destination deleted successfully.");
		}
	}

	@FXML
	private void updateSailTo() {
		sailto = sailToTable.getSelectionModel().getSelectedItem();
		if (sailto == null)
			errorSTLabel.setText("Please select a sail to destination to update.");
		else
			ViewLogic.newSailToManagementWindow();
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

	@FXML
	private void cruiseOrdersOnAction() {
		closeWindow();
		ViewLogic.newAdminCruiseOrdersWindow();
	}

	@FXML
	private void dashboardOnAction() {
		closeWindow();
		ViewLogic.newAdminDashWindow();
	}
}
