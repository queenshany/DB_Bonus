package view;

import java.sql.Date;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

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
import model.Person;
public class AdminCustomersScreenController {

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
	private TableView<Person> customersTable;

	@FXML
	private TableColumn<Person, String> IDColumn;

	@FXML
	private TableColumn<Person, String> firstNameColumn;

	@FXML
	private TableColumn<Person, String> surnameColumn;

	@FXML
	private TableColumn<Person, Date> birthdateColumn;

	@FXML
	private TableColumn<Person, String> phoneColumn;

	@FXML
	private TableColumn<Person, String> emailColumn;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton addCustomerBut;

	@FXML
	private JFXButton delCustomerBut;

	@FXML
	private JFXButton updateCustomerBut;

	protected ArrayList<Person> customers;

	protected Person customer;

	// =============================== Methods ==============================

	public void initialize() {

		ViewLogic.adminCustomersScreenController = this;

		pane.setStyle("-fx-background-image: url(\"/rsc/customers-bg2.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// setting customers table
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("personID")); // According to variable name
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName")); // Same here
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surName")); // Same here
		birthdateColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth")); // Same here
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone")); // Same here
		emailColumn.setCellValueFactory(new PropertyValueFactory<>("email")); // Same here
		setPersonTable();
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	protected void setPersonTable() {
		customers = ViewLogic.controller.getAllCustomers();
		ObservableList<Person> c = FXCollections.observableArrayList(customers);
		customersTable.setItems(c);
		customersTable.refresh();
	}

	@FXML
	private void addCustomer() {
		customer = null;
		ViewLogic.newCustomerManagementWindow();
	}

	@FXML
	private void deleteCustomer() {
		Person c = customersTable.getSelectionModel().getSelectedItem();
		if (c == null)
			errorLabel.setText("Please select a customer to delete.");
		else {
			ViewLogic.controller.removePerson(c);
			setPersonTable();
			errorLabel.setText("Customer deleted successfully.");
		}
	}

	@FXML
	private void updateCustomer() {
		customer = customersTable.getSelectionModel().getSelectedItem();
		if (customer == null)
			errorLabel.setText("Please select a customer to update.");
		else
			ViewLogic.newCustomerManagementWindow();
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
