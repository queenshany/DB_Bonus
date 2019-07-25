package view;

import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.CruiseOrder;
public class CustomerViewOrdersScreenController {
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
	private TableView<CruiseOrder> ordersTable;

	@FXML
	private TableColumn<CruiseOrder, String> cruiseColumn;

	@FXML
	private TableColumn<CruiseOrder, String> shipColumn;

	@FXML
	private TableColumn<CruiseOrder, String> roomNumColumn;

	private ArrayList<CruiseOrder> orders;
	
	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/view-orders-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		cruiseColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseID")); // According to variable name
		shipColumn.setCellValueFactory(new PropertyValueFactory<>("cruiseShipID")); // Same here
		roomNumColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber")); // Same here
		setOrdersTable();
		
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}
	
	private void setOrdersTable() {
		orders = ViewLogic.controller.getAllCruiseOrderByCustomerID(ViewLogic.currentUser);
		ObservableList<CruiseOrder> ors = FXCollections.observableArrayList(orders);
		ordersTable.setItems(ors);
		ordersTable.refresh();
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
