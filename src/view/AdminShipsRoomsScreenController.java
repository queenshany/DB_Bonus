package view;

import java.sql.Date;
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
import model.CruiseShip;
import model.Port;
import model.Room;
import model.SailTo;
public class AdminShipsRoomsScreenController {

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
	private TableView<CruiseShip> shipsTable;

	@FXML
	private TableColumn<CruiseShip, String> shipIDColumnS;

	@FXML
	private TableColumn<CruiseShip, String> nameColumnS;

	@FXML
	private TableColumn<CruiseShip, Date> manuDateColumnS;

	@FXML
	private TableColumn<CruiseShip, Integer> maxCapacityColumnS;

	@FXML
	private TableColumn<CruiseShip, Integer> maxPeopleColumnS;

	@FXML
	private Label errorShipLabel;

	@FXML
	private JFXButton addShipBut;

	@FXML
	private JFXButton delShipBut;

	@FXML
	private JFXButton updateShipBut;

	@FXML
	private TableView<Room> roomsTable;

	@FXML
	private TableColumn<Room, String> shipColumnR;

	@FXML
	private TableColumn<Room, Integer> roomNumColumnR;

	@FXML
	private TableColumn<Room, Integer> bedsAmountColumnR;

	@FXML
	private TableColumn<Room, String> roomTypeColumnR;

	@FXML
	private TableColumn<Room, Integer> priceColumnR;

	@FXML
	private Label errorRoomLabel;

	@FXML
	private JFXButton addRoomBut;

	@FXML
	private JFXButton delRoomBut;

	@FXML
	private JFXButton updateRoomBut;

	protected ArrayList<CruiseShip> ships;

	protected CruiseShip ship;

	protected Room room;

	// =============================== Methods ==============================

	public void initialize() {

		ViewLogic.adminShipsRoomsScreenController = this;

		pane.setStyle("-fx-background-image: url(\"/rsc/ship-room-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorRoomLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");
		errorShipLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// setting room table
		shipColumnR.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // According to variable name
		roomNumColumnR.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); // Same here
		bedsAmountColumnR.setCellValueFactory(new PropertyValueFactory<>("bedsAmount")); // Same here
		roomTypeColumnR.setCellValueFactory(new PropertyValueFactory<>("roomType")); // Same here
		priceColumnR.setCellValueFactory(new PropertyValueFactory<>("price")); // Same here

		// setting ship table
		shipIDColumnS.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // According to variable name
		nameColumnS.setCellValueFactory(new PropertyValueFactory<>("shipName")); // Same here
		manuDateColumnS.setCellValueFactory(new PropertyValueFactory<>("manufacturingDate")); // Same here
		maxCapacityColumnS.setCellValueFactory(new PropertyValueFactory<>("maxCapacity")); // Same here
		maxPeopleColumnS.setCellValueFactory(new PropertyValueFactory<>("maxNumberOfPeople")); // Same here
		setShipTable();
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	protected void setShipTable() {
		ships = ViewLogic.controller.getAllShips();
		ObservableList<CruiseShip> cs = FXCollections.observableArrayList(ships);
		shipsTable.setItems(cs);
		shipsTable.refresh();
	}

	@FXML
	protected void setRoomTable() {
		CruiseShip s = shipsTable.getSelectionModel().getSelectedItem();
		if (s != null) {
			ArrayList<Room> rooms = ViewLogic.controller.getAllRooms(s.getCruiseShipID());
			ObservableList<Room> rs = FXCollections.observableArrayList(rooms);
			roomsTable.setItems(rs);
		}
		else
			roomsTable.getItems().clear();

		roomsTable.refresh();
	}

	@FXML
	private void addShip() {
		ship = null;
		ViewLogic.newShipManagementWindow();
	}

	@FXML
	private void deleteShip() {
		CruiseShip s = shipsTable.getSelectionModel().getSelectedItem();
		if (s == null)
			errorShipLabel.setText("Please select a ship to delete.");
		else {
			ViewLogic.controller.removeShip(s);
			setShipTable();
			setRoomTable();
			errorShipLabel.setText("Ship deleted successfully.");
		}
	}

	@FXML
	private void updateShip() {
		ship = shipsTable.getSelectionModel().getSelectedItem();
		if (ship == null)
			errorShipLabel.setText("Please select a ship to update.");
		else
			ViewLogic.newShipManagementWindow();
	}

	@FXML
	private void addRoom() {
		CruiseShip s = shipsTable.getSelectionModel().getSelectedItem();
		if (s == null)
			errorRoomLabel.setText("Please select a ship in order to add a room to it.");
		else {
			room = new Room(s.getCruiseShipID());
			ViewLogic.newRoomManagementWindow();
		}
	}

	@FXML
	private void deleteRoom() {
		Room r = roomsTable.getSelectionModel().getSelectedItem();
		if (r == null)
			errorRoomLabel.setText("Please select a room to delete.");
		else {
			ViewLogic.controller.removeRoom(r);
			setRoomTable();
			errorRoomLabel.setText("Room deleted successfully.");
		}
	}

	@FXML
	private void updateRoom() {
		room = roomsTable.getSelectionModel().getSelectedItem();
		if (room == null)
			errorRoomLabel.setText("Please select a room to update.");
		else
			ViewLogic.newRoomManagementWindow();
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
