package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
	
	// =============================== Methods ==============================

	public void initialize() {
		mainPane.setStyle("-fx-background-image: url(\"/rsc/create-customer-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		Label l = new Label("Register");
		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #305e00 , 4 , 1 , 0 , 0 )");
		registerBut.setGraphic(l);
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
		errorLabel.setText("hello");
	}

	/**
	 * this method enables logging in pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			regOnAction();
	}
}
