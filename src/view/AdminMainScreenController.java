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
		pane.setStyle("-fx-background-image: url(\"/rsc/admin-bg.png\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
//		Label l = new Label("Login");
//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ========================== Menu Action Listeners ==========================

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
