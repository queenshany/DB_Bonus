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
	    private TableView<?> customersTable;

	    @FXML
	    private TableColumn<?, ?> IDColumn;

	    @FXML
	    private TableColumn<?, ?> fistNameColumn;

	    @FXML
	    private TableColumn<?, ?> surnameColumn;

	    @FXML
	    private TableColumn<?, ?> birthdateColumn;

	    @FXML
	    private TableColumn<?, ?> phoneColumn;

	    @FXML
	    private TableColumn<?, ?> emailColumn;

	    @FXML
	    private Label errorLabel;

	    @FXML
	    private JFXButton addCustomerBut;

	    @FXML
	    private JFXButton delCustomerBut;

	    @FXML
	    private JFXButton updateCustomerBut;
	
	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/customers-bg2.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		
		errorLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
//		Label l = new Label("Login");
//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	//TODO
	@FXML
	private void addCustomer() {

	}
	
	//TODO
	@FXML
	private void deleteCustomer() {
		
	}
	
	//TODO
	@FXML
	private void updateCustomer() {
		
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
