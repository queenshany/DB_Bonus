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

	// ========================== Action Listeners ==========================

	//TODO
	@FXML
	private void createAccount() {
		//ViewLogic.createAccountWindow();
	}

	/**
	 * If the user exists in the system, this method allows him to log in when he
	 * presses on the login button
	 */
	@FXML
	private void loginOnAction() {
		//TODO
		//		String username = userField.getText();
		//		String pw = pwField.getText();
		//
		//		ViewLogic.currentUserType = ViewLogic.sysData.validateUser(username, pw);
		//
		//		if (ViewLogic.currentUserType == null) {
		//			System.out.println("User Doesn't exist!");
		//
		//		
		//
		//			Validation.alert("Login Error!", "Invalid Credentials!");
		//		}
		//
		//		else {
		//			ViewLogic.currentUserID = username;
		//			System.out.println(ViewLogic.currentUserType + " ID: " + ViewLogic.currentUserID + " has logged in!");
		//			Sound.playLoginSound();
		//			
		//			switch (ViewLogic.currentUserType) {
		//			case ADMIN:
		//				closeWindow();
		//				ViewLogic.newAdminWindow();
		//				break;
		//
		//			case COACH:
		//				closeWindow();
		//				ViewLogic.newCoachWindow();
		//				break;
		//
		//			case RECEPTIONIST:
		//				closeWindow();
		//				ViewLogic.newRecepWindow();
		//				break;
		//
		//			case CUSTOMER:
		//				closeWindow();
		//				ViewLogic.newCusWindow();
		//				break;
		//			}
		//		}
	}

	/**
	 * this method enables logging in pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			loginOnAction();
	}
}
