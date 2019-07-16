package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class CustomerMainScreenController {

	// ============================== Variables =============================

	  @FXML
	    private AnchorPane pane;

	    @FXML
	    private JFXButton cruiseOrderBut;

	    @FXML
	    private JFXButton editDetailsBut;

	    @FXML
	    private JFXButton viewOrdersBut;

	    @FXML
	    private JFXButton dashboardBut;

	    @FXML
	    private JFXButton logoutBut;
	
	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/customer-bg.png\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		System.out.println("Welcome "+ ViewLogic.currentUser);
//		Label l = new Label("Login");
//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ========================== Menu Action Listeners ==========================

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
