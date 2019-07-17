package view;

import java.sql.Date;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.jmx.snmp.Timestamp;

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
import model.CruiseOrder;
import model.CruiseSailing;
import model.Port;
import model.SailTo;
public class AdminCruiseOrdersScreenController {

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
	private TableView<CruiseOrder> futureOrdersTable;

	@FXML
	private TableColumn<CruiseOrder, String> cruiseColumnF;

	@FXML
	private TableColumn<CruiseOrder, String> shipColumnF;

	@FXML
	private TableColumn<CruiseOrder, String> roomNumColumnF;

	@FXML
	private TableColumn<CruiseOrder, String> personColumnF;

	@FXML
	private JFXButton addOrderBut;

	@FXML
	private JFXButton delOrderBut;

	@FXML
	private Label errorLabel;

	@FXML
	private TableView<CruiseOrder> pastOrdersTable;

	@FXML
	private TableColumn<CruiseOrder, String> cruiseColumnP;

	@FXML
	private TableColumn<CruiseOrder, String> shipColumnP;

	@FXML
	private TableColumn<CruiseOrder, String> roomNumColumnP;

	@FXML
	private TableColumn<CruiseOrder, String> personColumnP;

	protected ArrayList<CruiseOrder> futureOrders;

	// =============================== Methods ==============================

	public void initialize() {

		pane.setStyle("-fx-background-image: url(\"/rsc/admin-cruise-order-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// setting past table
		cruiseColumnP.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipColumnP.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		roomNumColumnP.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); // Same here
		personColumnP.setCellValueFactory(new PropertyValueFactory<>("personID")); // Same here
		setPastTable();

		// setting future table
		cruiseColumnF.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipColumnF.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		roomNumColumnF.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); // Same here
		personColumnF.setCellValueFactory(new PropertyValueFactory<>("personID")); // Same here
		setFutureTable();
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	protected void setFutureTable() {
		futureOrders = ViewLogic.controller.getFutureCO();
		ObservableList<CruiseOrder> co = FXCollections.observableArrayList(futureOrders);
		futureOrdersTable.setItems(co);
		futureOrdersTable.refresh();
	}

	private void setPastTable() {
		ArrayList<CruiseOrder> pastOrders = ViewLogic.controller.getFutureCO(); //TODO
		ObservableList<CruiseOrder> co = FXCollections.observableArrayList(pastOrders);
		pastOrdersTable.setItems(co);
		pastOrdersTable.refresh();
	}

	@FXML
	private void addOrder() {
		ViewLogic.newCOManagementWindow();
	}

	@FXML
	private void deleteOrder() {
		CruiseOrder co = futureOrdersTable.getSelectionModel().getSelectedItem();
		if (co == null)
			errorLabel.setText("Please select an order to delete.");
		else {
			ViewLogic.controller.removeCruiseOrder(co);
			setFutureTable();
			errorLabel.setText("Order deleted successfully.");
		}
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
