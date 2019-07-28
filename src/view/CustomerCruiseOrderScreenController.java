package view;

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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CruiseOrder;
import model.CruiseSailing;
import model.Room;
public class CustomerCruiseOrderScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXButton homeBut;

	@FXML
	private JFXButton logoutBut;

	@FXML
	private JFXButton editDetailsBut;

	@FXML
	private JFXButton cruiseOrderBut;

	@FXML
	private JFXButton viewOrdersBut;

	@FXML
	private JFXButton dashboardBut;

	@FXML
	private VBox pane;

	@FXML
	private JFXComboBox<CruiseSailing> cruiseCombo;

	@FXML
	private JFXComboBox<Room> roomCombo;

	@FXML
	private Label errorAddOrderLabel;

	@FXML
	private JFXButton addOrderBut;

	@FXML
	private TableView<CruiseOrder> ordersTable;

	@FXML
	private TableColumn<CruiseOrder, String> cruiseColumn;

	@FXML
	private TableColumn<CruiseOrder, String> shipColumn;

	@FXML
	private TableColumn<CruiseOrder, String> roomNumColumn;

	@FXML
	private Label errorDelOrderLabel;

	@FXML
	private JFXButton delOrderBut;

	private ArrayList<CruiseOrder> orders;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/cruise-order-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorDelOrderLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");
		errorAddOrderLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		cruiseCombo.getItems().setAll(ViewLogic.controller.getAllFutureCruiseSailing());

		cruiseColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		roomNumColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); // Same here
		setFutureOrdersTable();
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	@FXML
	private void addOrder() {
		CruiseSailing cs = cruiseCombo.getSelectionModel().getSelectedItem();
		Room r = roomCombo.getSelectionModel().getSelectedItem();

		if (cs != null) {
			if (r != null) {
				if (ViewLogic.controller.insertCruiseOrder(new CruiseOrder(cs.getCruiseID(), cs.getCruiseShipID(), Integer.toString(r.getRoomNumber()), ViewLogic.currentUser.getPersonID()))) {
					setFutureOrdersTable();
					cruiseCombo.getSelectionModel().clearSelection();
					setRoomCombo();
					errorAddOrderLabel.setText("Order added successfully. Add another?");
				} else
					errorAddOrderLabel.setText("Error occurred.");
			} else
				errorAddOrderLabel.setText("Please select a room.");
		} else
			errorAddOrderLabel.setText("Please select a cruise.");

	}

	@FXML
	private void deleteOrder() {
		CruiseOrder co = ordersTable.getSelectionModel().getSelectedItem();
		if (co == null)
			errorDelOrderLabel.setText("Please select an order to delete.");
		else {
			ViewLogic.controller.removeCruiseOrder(co);
			setFutureOrdersTable();
			errorDelOrderLabel.setText("Order deleted successfully.");
		}
	}

	private void setFutureOrdersTable() {
		orders = ViewLogic.controller.getFutureCruiseOrderByCustomerID(ViewLogic.currentUser);
		ObservableList<CruiseOrder> co = FXCollections.observableArrayList(orders);
		ordersTable.setItems(co);
		ordersTable.refresh();
	}

	@FXML
	private void setRoomCombo() {
		CruiseSailing cs = cruiseCombo.getSelectionModel().getSelectedItem();
		if (cs == null) {
			roomCombo.getItems().clear();
			roomCombo.setDisable(true);
		}
		else {
			roomCombo.setDisable(false);
			roomCombo.getItems().setAll(ViewLogic.controller.getVacantRoomsByCruiseID(Integer.parseInt(cs.getCruiseID())));
		}
	}

	// ========================== Menu Action Listeners ==========================

	@FXML
	private void homeMainOnAction() {
		closeWindow();
		ViewLogic.newCustomerMainWindow();
	}

	@FXML
	private void logoutOnAction() {
		closeWindow();
		ViewLogic.currentUser = null;
		ViewLogic.newLoginWindow();
	}

	@FXML
	private void editDetailsOnAction() {
		ViewLogic.newCustomerManagementWindow();
	}

	@FXML
	private void cruiseOrderOnAction() {
		closeWindow();
		ViewLogic.newCustomerCruiseOrderWindow();
	}

	@FXML
	private void viewOrdersOnAction() {
		closeWindow();
		ViewLogic.newCustomerViewOrdersWindow();
	}

	@FXML
	private void dashboardOnAction() {
		closeWindow();
		ViewLogic.newCustomerDashWindow();
	}
}
