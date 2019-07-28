package view;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
public class AdminMainScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private JFXButton counPortsBut;

	@FXML
	private JFXButton shipRoomBut;

	@FXML
	private JFXButton customersBut;

	@FXML
	private JFXButton cruiseOrderBut;

	@FXML
	private JFXButton dashboardBut;

	@FXML
	private JFXButton sailCruiseBut;

	// =============================== Methods ==============================

	public void initialize() {
		System.out.println(ViewLogic.currentUser == null ? "Admin" : ViewLogic.currentUser);
		pane.setStyle("-fx-background-image: url(\"/rsc/admin-bg.png\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ========================== Menu Action Listeners ==========================

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
