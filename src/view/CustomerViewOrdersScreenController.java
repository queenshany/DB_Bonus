package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
	private TableView<?> ordersTable;

	@FXML
	private TableColumn<?, ?> cruiseColumn;

	@FXML
	private TableColumn<?, ?> shipColumn;

	@FXML
	private TableColumn<?, ?> roomNumColumn;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/view-orders-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		//		Label l = new Label("Login");
		//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
		//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
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

	//TODO
	@FXML
	private void editDetailsOnAction() {
		
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
