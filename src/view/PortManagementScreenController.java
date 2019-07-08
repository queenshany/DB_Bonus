package view;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
public class PortManagementScreenController {

	// ============================== Variables =============================

	@FXML
	private AnchorPane mainPane;

	@FXML
	private JFXComboBox<Country> countryCombo;

	@FXML
	private JFXTextField portTextField;

	@FXML
	private Label errorLabel;

	@FXML
	private JFXButton savePortBut;

	// =============================== Methods ==============================

	public void initialize() {
		mainPane.setStyle("-fx-background-image: url(\"/rsc/port-bg.jpg\");"
				+ "-fx-background-repeat: no-repeat; -fx-background-size: stretch;");
		errorLabel.setStyle("-fx-text-fill: red; -fx-effect: dropshadow( one-pass-box , black , 5 , 1.5 , 0 , 0 )");
		//		Label l = new Label("Login");
		//		l.setStyle("-fx-text-fill: white; -fx-effect: dropshadow( one-pass-box , #014a74 , 4 , 0.5 , 0 , 0 )");
		//		loginBut.setGraphic(l);
	}

	protected void closeWindow() {
		((Stage) mainPane.getScene().getWindow()).close();
	}

	// ========================== Action Listeners ==========================

	//TODO
	@FXML
	private void savePort() {
		errorLabel.setText("hello");

	}

	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			savePort();
	}
}
