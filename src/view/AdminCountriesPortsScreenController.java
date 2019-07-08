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
public class AdminCountriesPortsScreenController {

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
	    private HBox pane;

	    @FXML
	    private JFXListView<Country> countryList;

	    @FXML
	    private Label errorCounLabel;

	    @FXML
	    private JFXButton addCountryBut;

	    @FXML
	    private JFXButton delCountryBut;

	    @FXML
	    private JFXButton updateCountryBut;

	    @FXML
	    private TableView<Port> portsTable;

	    @FXML
	    private TableColumn<Port, String> portColumn;

	    @FXML
	    private TableColumn<Port, Country> countryColumn;

	    @FXML
	    private Label errorPortLabel;

	    @FXML
	    private JFXButton addPortBut;

	    @FXML
	    private JFXButton delPortBut;

	    @FXML
	    private JFXButton updatePortBut;
	
	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/port-country-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
//		Label l = new Label("Login");
//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	//TODO
	@FXML
	private void addCountry() {
		
	}
	
	//TODO
	@FXML
	private void deleteCountry() {
		
	}
	
	//TODO
	@FXML
	private void updateCountry() {
		
	}
	
	//TODO
	@FXML
	private void addPort() {
		
	}
	
	//TODO
	@FXML
	private void deletePort() {
		
	}
	
	//TODO
	@FXML
	private void updatePort() {
		
	}
	
	// ========================== Menu Action Listeners ==========================

	//TODO
	@FXML
	private void homeMainOnAction() {
		
	}

	//TODO
	@FXML
	private void logoutOnAction() {
		
	}

	//TODO
	@FXML
	private void countriesPortsOnAction() {
		
	}
	
	//TODO
	@FXML
	private void shipsRoomsOnAction() {
		
	}
	
	//TODO
	@FXML
	private void cruisesOnAction() {
		
	}
	
	//TODO
	@FXML
	private void customersOnAction() {
		
	}
	
	//TODO
	@FXML
	private void cruiseOrdersOnAction() {
		
	}
	
	//TODO
	@FXML
	private void dashboardOnAction() {
		
	}
}
