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
public class AdminCruisesScreenController {

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
	    private TableView<?> cruisesTable;

	    @FXML
	    private TableColumn<?, ?> cruiseIDColumnC;

	    @FXML
	    private TableColumn<?, ?> shipColumnC;

	    @FXML
	    private TableColumn<?, ?> leavingTimeColumnC;

	    @FXML
	    private TableColumn<?, ?> returnTmeColumnC;

	    @FXML
	    private Label errorCruiseLabel;

	    @FXML
	    private JFXButton addCruiseBut;

	    @FXML
	    private JFXButton delCruiseBut;

	    @FXML
	    private JFXButton updateCruiseBut;

	    @FXML
	    private TableView<?> sailToTable;

	    @FXML
	    private TableColumn<?, ?> cruiseColumnST;

	    @FXML
	    private TableColumn<?, ?> countryColumnST;

	    @FXML
	    private TableColumn<?, ?> portColumnST;

	    @FXML
	    private TableColumn<?, ?> arrivalTimeColumnST;

	    @FXML
	    private TableColumn<?, ?> leavingTimeColumnST;

	    @FXML
	    private Label errorSTLabel;

	    @FXML
	    private JFXButton addSailToBut;

	    @FXML
	    private JFXButton delSailToBut;

	    @FXML
	    private JFXButton updateSailToBut;
	
	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/cruise-st-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		
		errorCruiseLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
		errorSTLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , white , 5 , 1.5 , 0 , 0 )");
//		Label l = new Label("Login");
//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	//TODO
	@FXML
	private void addCruise() {

	}
	
	//TODO
	@FXML
	private void deleteCruise() {
		
	}
	
	//TODO
	@FXML
	private void updateCruise() {
		
	}
	
	//TODO
	@FXML
	private void addSailTo() {
		
	}
	
	//TODO
	@FXML
	private void deleteSailTo() {
		
	}
	
	//TODO
	@FXML
	private void updateSailTo() {
		
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
