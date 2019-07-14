package view;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import control.ControllerLogic;
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
import model.Port;
public class AdminCountriesPortsScreenController {

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
	private HBox pane;

	@FXML
	private JFXListView<Country> countryList;

	@FXML
	private Label errorCounLabel;

	@FXML
	private JFXButton addCountryBut;

	@FXML
	private JFXButton delCountryBut;

	@FXML
	private JFXButton updateCountryBut;

	@FXML
	private TableView<Port> portsTable;

	@FXML
	private TableColumn<Port, String> portColumn;

	@FXML
	private TableColumn<Port, Country> countryColumn;

	@FXML
	private Label errorPortLabel;

	@FXML
	private JFXButton addPortBut;

	@FXML
	private JFXButton delPortBut;

	@FXML
	private JFXButton updatePortBut;

	private ArrayList<Country> countries = new ArrayList<>();

	private ArrayList<Port> ports = new ArrayList<>();

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/port-country-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		ViewLogic.adminCountriesPortsScreenController = this;

		// setting country list
		setCountryList();

		// setting port table
		countryColumn.setCellValueFactory(new PropertyValueFactory<>("countryName")); // According to variable name
		portColumn.setCellValueFactory(new PropertyValueFactory<>("portName")); // Same here
		setPortTable();

	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	@FXML
	private void addCountry() {
		ViewLogic.newCountryManagementWindow();
	}

	@FXML
	private void deleteCountry() {
		Country c = countryList.getSelectionModel().getSelectedItem();
		if (c == null)
			errorCounLabel.setText("Please select a country to delete.");
		else {
			ViewLogic.controller.removeCountry(c);
			setCountryList();
			setPortTable();
			errorCounLabel.setText("Country deleted successfully.");
		}
	}

	@FXML
	private void addPort() {
		ViewLogic.newPortManagementWindow();
	}

	@FXML
	private void deletePort() {
		Port p = portsTable.getSelectionModel().getSelectedItem();
		if (p == null)
			errorPortLabel.setText("Please select a port to delete.");
		else {
			ViewLogic.controller.removePort(p);
			setPortTable();
			errorPortLabel.setText("Port deleted successfully.");
		}
	}

	protected void setCountryList() {
		countries = ViewLogic.controller.getAllCountries();
		countryList.getItems().setAll(countries);
		countryList.refresh();
	}

	protected void setPortTable() {
		ports = ViewLogic.controller.getAllPorts();
		ObservableList<Port> p = FXCollections.observableArrayList(ports);
		portsTable.setItems(p);
		portsTable.refresh();
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
