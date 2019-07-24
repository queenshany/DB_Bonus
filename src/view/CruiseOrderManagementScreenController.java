package view;

import java.sql.Date;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.CruiseOrder;
import model.CruiseSailing;
import model.Person;
import model.Room;
public class CruiseOrderManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

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
	private JFXComboBox<CruiseSailing> cruiseCombo;

	@FXML
	private TableView<Room> roomsTable;

	@FXML
	private TableColumn<Room, String> shipColumn;

	@FXML
	private TableColumn<Room, Integer> roomNumColumn;

	@FXML
	private TableColumn<Room, Integer> bedsAmountColumn;

	@FXML
	private TableColumn<Room, String> roomTypeColumn;

	@FXML
	private TableColumn<Room, Integer> priceColumn;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton saveCruiseOrderBut;

	// =============================== Methods ==============================

	public void initialize() {
		mainPane.setStyle("-fx-background-image: url(\"/rsc/new-co-bg.jpg\");"
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

		// set cruiseCombo
		cruiseCombo.getItems().setAll(ViewLogic.controller.getAllFutureCruiseSailing());

		// setting room table
		shipColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // According to variable name
		roomNumColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); // Same here
		bedsAmountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsAmount")); // Same here
		roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType")); // Same here
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("price")); // Same here

	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	protected void setPersonTable() {
		ArrayList<Person> customers = ViewLogic.controller.getAllCustomers();
		ObservableList<Person> c = FXCollections.observableArrayList(customers);
		customersTable.setItems(c);
		// customersTable.refresh();
	}

	@FXML
	protected void setRoomTable() {
		CruiseSailing s = cruiseCombo.getSelectionModel().getSelectedItem();
		if (s != null) {
			ArrayList<Room> rooms = ViewLogic.controller.getAllRooms(s.getCruiseShipID()); //TODO FREE ROOMS
			ObservableList<Room> rs = FXCollections.observableArrayList(rooms);
			roomsTable.setItems(rs);
		}
		else
			roomsTable.getItems().clear();

		roomsTable.refresh();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveCruiseOrder() {
		Person c = customersTable.getSelectionModel().getSelectedItem();
		CruiseSailing cs = cruiseCombo.getSelectionModel().getSelectedItem();
		Room r = roomsTable.getSelectionModel().getSelectedItem();

		if (c != null) {
			if (cs != null) {
				if (r != null) {
					if (ViewLogic.controller.insertCruiseOrder(new CruiseOrder(cs.getCruiseID(), cs.getCruiseShipID(), Integer.toString(r.getRoomNumber()), c.getPersonID()))) {
						errorLabel.setText("Order added successfully. Add another?");
						customersTable.getSelectionModel().clearSelection();
						cruiseCombo.getSelectionModel().clearSelection();
						roomsTable.getSelectionModel().clearSelection();
						setRoomTable();
						ViewLogic.adminCruiseOrdersScreenController.setFutureTable();
					} else
						errorLabel.setText("Error occurred.");
				} else
					errorLabel.setText("Please choose a room.");
			} else
				errorLabel.setText("Please choose a cruise.");
		} else
			errorLabel.setText("Please choose a customer.");
	}
}
