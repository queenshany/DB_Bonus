package view;

import java.sql.Date;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Person;
import utils.Consts;
import utils.E_Phone;
public class CustomerManagementScreenController {

	// ============================== Variables =============================


	@FXML
	private StackPane pane;

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXTextField IDTextField;

	@FXML
	private PasswordField pwTextField;

	@FXML
	private JFXTextField firstNameTextField;

	@FXML
	private JFXTextField surnameTextField;

	@FXML
	private JFXTextField emailTextField;

	@FXML
	private JFXDatePicker birthDatePicker;

	@FXML
	private JFXComboBox<E_Phone> phoneCombo;

	@FXML
	private JFXTextField phoneTextField;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton saveCustomerBut;

	private Person customer;

	private boolean update;

	// =============================== Methods ==============================

	public void initialize() {
		customer = ViewLogic.adminCustomersScreenController.customer;

		pane.setStyle("-fx-background-image: url(\"/rsc/customer-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");

		errorLabel.setStyle("-fx-effect: dropshadow( one-pass-box , #101d3d , 5 , 1.5 , 0 , 0 )");

		// set phone combo
		phoneCombo.getItems().setAll(E_Phone.values());

		// for update
		if (customer != null) {
			IDTextField.setText(customer.getPersonID());
			IDTextField.setEditable(false);
			pwTextField.setText(customer.getPass());
			firstNameTextField.setText(customer.getFirstName());
			surnameTextField.setText(customer.getSurName());
			emailTextField.setText(customer.getEmail());
			LocalDate bday = customer.getDateOfBirth().toLocalDate();
			birthDatePicker.setValue(bday);
			phoneCombo.getSelectionModel().select(E_Phone.getPhone(customer.getPhone().substring(0, 3)));
			phoneTextField.setText(customer.getPhone().substring(3));
			update = true;
		}
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void saveCustomer() {
		String id = IDTextField.getText();
		String pw = pwTextField.getText();
		String fname = firstNameTextField.getText();
		String lname = surnameTextField.getText();
		String email = emailTextField.getText();

		if (id != null) {
			if (Validation.validID(id)) {
				if (pw != null && pw != "") {
					if (pw.length() >= Consts.FOUR) {
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

																	if (update) {
																		ViewLogic.controller.updatePerson(p);
																		errorLabel.setText("Customer updated successfully.");
																	}
																	else if (!update && !ViewLogic.controller.doesPersonExist(p)) {
																		if (ViewLogic.controller.insertPerson(p))
																			errorLabel.setText("Customer added successfully.");
																		else
																			errorLabel.setText("Customer already exists.");
																	}
																	else
																		errorLabel.setText("Customer already exists.");
																	ViewLogic.adminCustomersScreenController.setPersonTable();																		
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
						errorLabel.setText("Password must contain at least " + Consts.FOUR + " characters.");
				} else
					errorLabel.setText("Please enter a password.");
			} else
				errorLabel.setText("Invalid ID.");
		} else
			errorLabel.setText("Please enter your ID.");

	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			saveCustomer();
	}
}
