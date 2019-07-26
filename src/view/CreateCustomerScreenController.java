package view;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Person;
import utils.Consts;
import utils.E_Phone;
public class CreateCustomerScreenController {

	// ============================== Variables =============================


	@FXML
	private StackPane mainPane;

	@FXML
	private VBox pane;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXTextField userTextField;

	@FXML
	private JFXPasswordField pwTextField;

	@FXML
	private JFXPasswordField pwConTextField;

	@FXML
	private JFXTextField fnameTextField;

	@FXML
	private JFXTextField surnameTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXDatePicker birthDatePicker;

	@FXML
	private JFXTextField phoneTextField;

	@FXML
	private JFXButton registerBut;

	@FXML
	private JFXButton cancelBut;

	@FXML
	private JFXComboBox<E_Phone> phoneCombo;

	// =============================== Methods ==============================

	public void initialize() {
		mainPane.setStyle("-fx-background-image: url(\"/rsc/create-customer-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		Label reg = new Label("Register");
		Label can = new Label("Cancel");

		reg.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #305e00 , 4 , 1 , 0 , 0 )");
		can.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #305e00 , 4 , 1 , 0 , 0 )");

		registerBut.setGraphic(reg);
		cancelBut.setGraphic(can);

		phoneCombo.getItems().setAll(E_Phone.values());
	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	/**
	 * If the user exists in the system, this method allows him to log in when he
	 * presses on the login button
	 */
	@FXML
	private void regOnAction() {
		String id = userTextField.getText();
		String pw = pwTextField.getText();
		String pwc = pwConTextField.getText();
		String fname = fnameTextField.getText();
		String lname = surnameTextField.getText();
		String email = emailTextField.getText();

		if (id != null) {
			if (Validation.validID(id)) {
				if (!ViewLogic.controller.doesPersonExist(new Person(id))) {
					if (pw != null && pw != "") {
						if (pw.length() >= Consts.FOUR) {
							if (pw.equals(pwc)) {
								if (fname != null && !fname.equals("")) {
									if (Validation.validName(fname)) {
										if (lname != null && !lname.equals("")) {
											if (Validation.validName(lname)) {
												if (email != null) {
													if (Validation.validEmailAddress(email)) {
														if (birthDatePicker.getValue() != null) {
															if (birthDatePicker.getValue().isBefore(LocalDate.now())) {
																Date bday = Date.valueOf(birthDatePicker.getValue());
																if (phoneCombo.getSelectionModel().getSelectedItem() != null) {
																	if (phoneTextField.getText() != null && !phoneTextField.getText().equals("")) {
																		if (Validation.validPhone(phoneTextField.getText())) {
																			String phone = phoneCombo.getSelectionModel().getSelectedItem() + phoneTextField.getText();
																			Person p = new Person(id, fname, lname, bday, phone, email, pw);
																			if (ViewLogic.controller.insertPerson(p)) {
																				closeWindow();
																				ViewLogic.newLoginWindow();
																				Alert alert = new Alert(AlertType.INFORMATION);
																				alert.setTitle("Account Created Successfully!");
																				alert.setHeaderText("Account Created Successfully!");
																				alert.setContentText("Your username is: " + p.getPersonID());
																				alert.showAndWait();
																			} else
																				errorLabel.setText("Error occurred.");
																		} else
																			errorLabel.setText("Invalid phone number.");
																	} else
																		errorLabel.setText("Please enter your phone number.");
																} else
																	errorLabel.setText("Please select an area code.");
															} else
																errorLabel.setText("Invalid birthdate.");
														} else 
															errorLabel.setText("Please enter your birthdate.");
													} else
														errorLabel.setText("Invalid email.");
												} else
													errorLabel.setText("Please enter your email.");
											} else
												errorLabel.setText("Invalid surname.");
										} else
											errorLabel.setText("Please enter your surname.");
									} else
										errorLabel.setText("Invalid first name.");
								} else
									errorLabel.setText("Please enter your first name.");
							} else
								errorLabel.setText("Passwords don't match.");
						} else
							errorLabel.setText("Password must contain at least " + Consts.FOUR + " characters."); 
					} else
						errorLabel.setText("Please enter a password.");
				} else
					errorLabel.setText("Account already exists.");
			} else
				errorLabel.setText("Invalid ID.");
		} else
			errorLabel.setText("Please enter your ID.");
	}

	/**
	 * this method enables logging in pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			regOnAction();
	}

	@FXML
	private void cancelOnAction() {
		Alert alert = new Alert(AlertType.WARNING);

		ButtonType buttonTypeYes;
		ButtonType buttonTypeNo;

		buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
		buttonTypeNo = new ButtonType("No", ButtonData.NO);

		alert.setHeaderText("Are you sure you want to cancel registration?");

		alert.setContentText("");

		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

		alert.setTitle("Cancel? :(");

		Optional<ButtonType> answer = alert.showAndWait();

		if (answer.get().getButtonData() == ButtonData.YES) {
			closeWindow();
			ViewLogic.newLoginWindow();
		}
	}
}
