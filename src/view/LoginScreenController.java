package view;


import java.util.Date;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
public class LoginScreenController {

	// ============================== Variables =============================

	@FXML
	private VBox pane;

	@FXML
	private JFXTextField userTextField;

	@FXML
	private JFXPasswordField pwTextField;

	@FXML
	private Hyperlink accountHyper;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton loginBut;

	// =============================== Methods ==============================

	public void initialize() {
		pane.setStyle("-fx-background-image: url(\"/rsc/login-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		Label l = new Label("Login");
		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void createAccount() {
		closeWindow();
		ViewLogic.createAccountWindow();
	}

	/**
	 * If the user exists in the system, this method allows him to log in when he
	 * presses on the login button
	 */
	@FXML
	private void loginOnAction() {
		//errorLabel.setText("hello");
		String username = userTextField.getText();
		String pw = pwTextField.getText();

		if (username != null && !username.isEmpty()) {
			if (pw != null && !pw.isEmpty()) {
				if (username.equalsIgnoreCase("admin") && pw.equalsIgnoreCase("admin")) {
					// ViewLogic.currentUser = //TODO GET THE CURRENT PERSON 
					closeWindow();
					ViewLogic.newAdminMainWindow();
				}
				//TODO
				else if (username.equals("1") && pw.equals("1")) {
					closeWindow();
					ViewLogic.currentUser = new Person("111", "hello", "world", new Date(), "000000000", "hello@world.com", "1");
					ViewLogic.newCustomerMainWindow();
				}
				//				else if (false) { //TODO ALSO TO DO A CHECK THAT THE USER EXISTS
				//					errorLabel.setText("Username doesn't exist. Please create an account.");
				//				}
				//				else if (false) { //TODO USER EXISTS AND IS A CUSTOMER AND HIS DATA IS GOOD
				//					closeWindow();
				//					ViewLogic.newCustomerMainWindow();
				//				}
				else {
					errorLabel.setText("Incorrect username or password.");
				}
			}else{
				errorLabel.setText("Please Enter a password.");
			}
		}else{
			errorLabel.setText("Please Enter a username.");
		}
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
