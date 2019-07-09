package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Country;
import model.Port;
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
	private TableView<?> shipsTable;

	@FXML
	private TableColumn<?, ?> shipIDColumnS;

	@FXML
	private TableColumn<?, ?> nameColumnS;

	@FXML
	private TableColumn<?, ?> manuDateColumnS;

	@FXML
	private TableColumn<?, ?> maxCapacityColumnS;

	@FXML
	private TableColumn<?, ?> maxPeopleColumnS;

	@FXML
	private Label errorShipLabel;

	@FXML
	private JFXButton addShipBut;

	@FXML
	private JFXButton delShipBut;

	@FXML
	private JFXButton updateShipBut;

	@FXML
	private TableView<?> roomsTable;

	@FXML
	private TableColumn<?, ?> shipColumnR;

	@FXML
	private TableColumn<?, ?> roomNumColumnR;

	@FXML
	private TableColumn<?, ?> bedsAmountColumnR;

	@FXML
	private TableColumn<?, ?> roomTypeColumnR;

	@FXML
	private TableColumn<?, ?> priceColumnR;

	@FXML
	private Label errorRoomLabel;

	@FXML
	private JFXButton addRoomBut;

	@FXML
	private JFXButton delRoomBut;

	@FXML
	private JFXButton updateRoomBut;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/ship-room-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorRoomLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
		errorShipLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
		//		Label l = new Label("Login");
		//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
		//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	//TODO
	@FXML
	private void addShip() {

	}

	//TODO
	@FXML
	private void deleteShip() {

	}

	//TODO
	@FXML
	private void updateShip() {

	}

	//TODO
	@FXML
	private void addRoom() {

	}

	//TODO
	@FXML
	private void deleteRoom() {

	}

	//TODO
	@FXML
	private void updateRoom() {

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
